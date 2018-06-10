package org.rk.cloud.userservice.user.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.rk.cloud.userservice.user.user.dao.ICoreUserDao;
import org.rk.core.auth.util.SecurityUtil;
import org.rk.core.common.constant.RkConst;
import org.rk.core.common.util.RKAlert;
import org.rk.core.common.util.RKAssert;
import org.rk.core.common.util.RKDateUtil;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.common.util.RkStrUtil;
import org.rk.core.common.util.encrypt.MD5Util;
import org.rk.core.jdbc.dao.util.ParamMap;
import org.rk.core.pubServer.service.ModelService;
import org.rk.core.user.user.CoreUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreUserService")
public class CoreUserService extends ModelService<CoreUser> implements ICoreUserService{
	
	private ICoreUserDao modelDao;
	@Resource(name="CoreUserDao")
	public void setCoreUserDao(ICoreUserDao modelDao) {
		super.setDbDao(modelDao);
		this.modelDao=modelDao;
	}
	@Transactional
	@Override
	public boolean deleteById(Long id){
		return modelDao.deleteById(id);
	}
	@Transactional
	@Override
	public CoreUser selectUserByUserName(String userName){
		RKAssert.hasText(userName, "用户名不能为空");
		List<ParamMap> paramMapList=new ArrayList<ParamMap>();
		ParamMap pm=new ParamMap("userName", userName);
		paramMapList.add(pm);
		try {
			return modelDao.queryForEntity(CoreUser.class, paramMapList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Transactional
	@Override
	public CoreUser selectUserByEmail(String email){
		RKAssert.hasText(email, "邮箱不能为空");
		List<ParamMap> paramMapList=new ArrayList<ParamMap>();
		ParamMap pm=new ParamMap("email", email);
		paramMapList.add(pm);
		try {
			return modelDao.queryForEntity(CoreUser.class, paramMapList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public Long registerUser(CoreUser coreUser) {
		// 对密码加密
		// 生成一个随机32位盐值
		String salt = RkStrUtil.generateWord(32);
		coreUser.setSalt(salt);
		coreUser.setPassword(MD5Util.encrypt(coreUser.getUserName(), coreUser.getPassword(), salt));
		if(!RkStrUtil.hasText(coreUser.getIsActive())){
			coreUser.setIsActive(RkConst.yesno.no);// 默认未激活
		}
		CoreUser userNameIsExit = selectUserByUserName(coreUser.getUserName());
		if (!RkObjectUtil.isEmpty(userNameIsExit)) {
			RKAlert.Error("该用户名已存在！");
		}
		if(RkStrUtil.hasText(coreUser.getEmail())){
			CoreUser emailIsExit = selectUserByEmail(coreUser.getEmail());
			if (!RkObjectUtil.isEmpty(emailIsExit)) {
				RKAlert.Error("该邮箱已经存在！");
			}
		}
		if(!RkStrUtil.hasText(coreUser.getUserType())){
			coreUser.setUserType(RkConst.userType.user);
		}
		coreUser.setCreateTime(RKDateUtil.getCurrentTimestamp());
		if(RkStrUtil.hasText(coreUser.getCreator())){
			coreUser.setCreator(coreUser.getUserName());
		}
		Long id=super.insertModel(coreUser);
		return id;
	}
	
	@Override
	@Transactional
	public String login(String username, String password) {
		CoreUser coreUser = selectUserByUserName(username);
		if (RkObjectUtil.isEmpty(coreUser)) {
			RKAlert.Error("未知账户");
		}
		password = MD5Util.encrypt(username, password, coreUser.getSalt());// 加密密码
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		System.out.println(	"为了验证登录用户而封装的token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
		// 获取当前的Subject
		Subject subject = SecurityUtil.getSubject();
		try {
			System.out.println("对用户[" + username + "]进行登录验证..验证开始");
			subject.login(token);
			System.out.println("对用户[" + username + "]进行登录验证..验证通过");
		} catch (UnknownAccountException uae) {
			RKAlert.Error("未知账户");
		} catch (IncorrectCredentialsException ice) {
			ice.printStackTrace();
			RKAlert.Error("密码不正确");
		} catch (LockedAccountException lae) {
			RKAlert.Error("账户已锁定");
		} catch (ExcessiveAttemptsException eae) {
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
			RKAlert.Error("用户名或密码错误次数过多");
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
			ae.printStackTrace();
			RKAlert.Error("用户名或密码不正确");
		}
		// 验证是否登录成功
		if (SecurityUtil.isAuthenticated()) {
			System.out.println("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
		} else {
			token.clear();
		}
		//super.publisher.publishEvent(log);
		return "登陆成功！";
	}
}
