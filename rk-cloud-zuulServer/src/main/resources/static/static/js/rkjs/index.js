define(['jquery','jquerySparkline','ChartJs'], function($) {
'use strict';
	// function to initiate Sparkline
	var sparkResize;
	$(window).resize(function(e) {
		clearTimeout(sparkResize);
		sparkResize = setTimeout(sparklineHandler, 500);
	});
	var sparklineHandler = function() {

		$(".sparkline-1 span").sparkline([300, 523, 982, 811, 1300, 1125, 1487], {
			type: "bar",
			barColor: "#D43F3A",
			barWidth: "5",
			height: "24",
			tooltipFormat: '<span style="color: {{color}}">&#9679;</span> {{offset:names}}: {{value}}',
			tooltipValueLookups: {
				names: {
					0: 'Sunday',
					1: 'Monday',
					2: 'Tuesday',
					3: 'Wednesday',
					4: 'Thursday',
					5: 'Friday',
					6: 'Saturday'

				}
			}
		});
		$(".sparkline-2 span").sparkline([400, 650, 886, 443, 502, 412, 353], {
			type: "bar",
			barColor: "#5CB85C",
			barWidth: "5",
			height: "24",
			tooltipFormat: '<span style="color: {{color}}">&#9679;</span> {{offset:names}}: {{value}}',
			tooltipValueLookups: {
				names: {
					0: 'Sunday',
					1: 'Monday',
					2: 'Tuesday',
					3: 'Wednesday',
					4: 'Thursday',
					5: 'Friday',
					6: 'Saturday'

				}
			}
		});
		$(".sparkline-3 span").sparkline([4879, 6567, 5022, 8890, 9234, 7128, 4811], {
			type: "bar",
			barColor: "#46B8DA",
			barWidth: "5",
			height: "24",
			tooltipFormat: '<span style="color: {{color}}">&#9679;</span> {{offset:names}}: {{value}}',
			tooltipValueLookups: {
				names: {
					0: 'Sunday',
					1: 'Monday',
					2: 'Tuesday',
					3: 'Wednesday',
					4: 'Thursday',
					5: 'Friday',
					6: 'Saturday'

				}
			}
		});
		$(".sparkline-4 span").sparkline([1122, 1735, 559, 2534, 1600, 2860, 1345, 1987, 2675, 457, 3965, 3765], {
			type: "line",
			lineColor: '#8e8e93',
			width: "80%",
			height: "47",
			fillColor: "",
			spotRadius: 4,
			lineWidth: 1,
			resize: true,
			spotColor: '#ffffff',
			minSpotColor: '#D9534F',
			maxSpotColor: '#5CB85C',
			highlightSpotColor: '#CE4641',
			highlightLineColor: '#c2c2c5',
			tooltipFormat: '<span style="color: {{color}}">&#9679;</span> {{offset:names}}: {{y:val}}',
			tooltipValueLookups: {
				names: {
					0: 'January',
					1: 'February',
					2: 'March',
					3: 'April',
					4: 'May',
					5: 'June',
					6: 'July',
					7: 'August',
					8: 'September',
					9: 'October',
					10: 'November',
					11: 'December'

				}
			}
		});
		$(".sparkline-5 span").sparkline([422, 1335, 1059, 2235, 1300, 1860, 1126, 1387, 1675, 1357, 2165, 1765], {
			type: "line",
			lineColor: '#8e8e93',
			width: "80%",
			height: "47",
			fillColor: "",
			spotRadius: 4,
			lineWidth: 1,
			resize: true,
			spotColor: '#ffffff',
			minSpotColor: '#D9534F',
			maxSpotColor: '#5CB85C',
			highlightSpotColor: '#CE4641',
			highlightLineColor: '#c2c2c5',
			tooltipFormat: '<span style="color: {{color}}">&#9679;</span> {{offset:names}}: {{y:val}}',
			tooltipValueLookups: {
				names: {
					0: 'January',
					1: 'February',
					2: 'March',
					3: 'April',
					4: 'May',
					5: 'June',
					6: 'July',
					7: 'August',
					8: 'September',
					9: 'October',
					10: 'November',
					11: 'December'

				}
			}
		});
	};
	return{
		init:function() {
			sparklineHandler();
		}
	}
});
