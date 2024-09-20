
/*
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣤⣤⣤⣤⣶⣦⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⡿⠛⠉⠙⠛⠛⠛⠛⠻⢿⣿⣷⣤⡀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⠋⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠈⢻⣿⣿⡄⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⣸⣿⡏⠀⠀⠀⣠⣶⣾⣿⣿⣿⠿⠿⠿⢿⣿⣿⣿⣄⠀⠀⠀           봉 차트
⠀⠀⠀⠀⠀⠀⠀⣿⣿⠁⠀⠀⢰⣿⣿⣯⠁⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣷⡄⠀
⠀⠀⣀⣤⣴⣶⣶⣿⡟⠀⠀⠀⢸⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⠀
⠀⢰⣿⡟⠋⠉⣹⣿⡇⠀⠀⠀⠘⣿⣿⣿⣿⣷⣦⣤⣤⣤⣶⣶⣶⣶⣿⣿⣿⠀
⠀⢸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠀
⠀⣸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠉⠻⠿⣿⣿⣿⣿⡿⠿⠿⠛⢻⣿⡇⠀⠀
⠀⣿⣿⠁⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣧⠀⠀
⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀⠀
⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀⠀
⠀⢿⣿⡆⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀
⠀⠸⣿⣧⡀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠃⠀⠀
⠀⠀⠛⢿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⣰⣿⣿⣷⣶⣶⣶⣶⠶⠀⢠⣿⣿⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⣽⣿⡏⠁⠀⠀⢸⣿⡇⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⢹⣿⡆⠀⠀⠀⣸⣿⠇⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⢿⣿⣦⣄⣀⣠⣴⣿⣿⠁⠀⠈⠻⣿⣿⣿⣿⡿⠏⠀⠀⠀⠀           bar chart
⠀⠀⠀⠀⠀⠀⠀⠈⠛⠻⠿⠿⠿⠿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀

*/
// ▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰ BAR CHART ▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰

const basic_labels_bar = [
  "1월",
  "2월",
  "3월",
  "4월",
  "5월",
  "6월",
  "7월",
  "8월",
  "9월",
  "10월",
  "11월",
  "12월",
];

const data_bar = {
  labels: basic_labels_bar,
  datasets: [
    {
      label: "진행중",
      backgroundColor: "rgba(75, 192, 192, 0.2)",
      borderColor: "rgb(75, 192, 192)",
      data: [1, 10, 5, 2, 10, 13, 10, 12, 8, 1, 5, 6],
      borderWidth: 1,
    },
    {
      label: "지연",
      backgroundColor: "rgba(255, 205, 86, 0.2)",
      borderColor: "rgb(255, 205, 86)",
      data: [1, 1, 2, 2, 1, 3, 2, 3, 5, 3, 2, 1],
      borderWidth: 1,
    },
    {
      label: "완료",
      backgroundColor: "rgba(54, 162, 235, 0.2)",
      borderColor: "rgb(54, 162, 235)",
      data: [0, 0, 0, 0, 0, 0, 3, 0, 0, 1, 2, 3],
      borderWidth: 1,
    },
    {
      label: "파기요청",
      backgroundColor: "rgba(255, 99, 132, 0.2)",
      borderColor: "rgb(255, 99, 132)",
      data: [2, 2, 2, 2, 2, 1, 0, 0, 0, 1, 0, 3],
      borderWidth: 1,
    },
  ],
};

const config_bar = {
  type: "bar",
  data: data_bar,
  options: {
    scales: {
      y: {
        stacked: true,
      },
      x: {
        stacked: true,
      },
    },
  },
};

// bar chart
// const custom_barChart = new Chart(
//   document.getElementById("custom_barChart"),
//   config_bar
// );


//▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰ DONUT CHART ▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰
const data_doughnut = {
labels: [
 '진행',
 '완료',
 '대기'
],
datasets: [{
 label: 'My First Dataset',
 data: [3, 5, 3],
 backgroundColor: [
   '#3CB043',
   'rgb(54, 162, 235)',
   'rgb(255, 205, 86)'
 ],
 hoverOffset: 4,
 options: {legend : {position : 'right'}}
}]
};

const config_doughnut = {
type: 'doughnut',
data: data_doughnut,
};
// const custom_doughnutChart = new Chart(
// document.getElementById('custom_doughnutChart'),
// config_doughnut
// );


//▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰ ISSEUE BAR CHART ▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰
const labels_issueBar = [
  "1월",
  "2월",
  "3월",
  "4월",
  "5월",
  "6월",
  "7월",
  "8월",
  "9월",
  "10월",
  "11월",
  "12월",
];

const data_issueBar = {
  labels: labels_issueBar,
  datasets: [
    {
      label: "진행중",
      backgroundColor: "rgba(75, 192, 192, 0.2)",
      borderColor: "rgb(75, 192, 192)",
      data: [1, 10, 5, 2, 10, 13, 10, 12, 8, 1, 5, 6],
      borderWidth: 1,
    },
    {
      label: "지연",
      backgroundColor: "rgba(255, 205, 86, 0.2)",
      borderColor: "rgb(255, 205, 86)",
      data: [1, 1, 2, 2, 1, 3, 2, 3, 5, 3, 2, 1],
      borderWidth: 1,
    },
  ],
};

const config_issueBar = {
  type: "bar",
  data: data_issueBar,
  options: {
    scales: {
      y: {
        stacked: true,
      },
      x: {
        stacked: true,
      },
    },
  },
};

// const custom_issueChart = new Chart(
//   document.getElementById("custom_issueBarChart"),
//   config_issueBar
// );

//▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰ project member achievement BAR CHART ▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰

$(function () {
  if(window.location.pathname === "/app/project/main"){
    get_member_achievement();
  }
  if(window.location.pathname === "/app/collabo/main"){
	  get_cproj_member_achievement();
  }
  
});

function get_member_achievement() {
  let search = location.search;
  let params = new URLSearchParams(search);
  let getData = params.get('projNo');

  $.ajax({
    type: "post",
    url: "/toMemberAchievementChart.do",
    data: {"projNo" : getData},
    dataType: "json",
    success: function (userList) {
        
    let member_achievement_config = {
      type: 'scatter',
      data: {
        labels: [],
        datasets: [
          {
            type: 'bar',
            label: '총 업무량',
            data: [],
            borderColor: 'rgb(201, 203, 207)',
            backgroundColor: 'rgba(201, 203, 207, 0.2)',
            borderWidth: 1,
            maxBarThickness: 100,
          }, {
            type: 'line',
            label: '진척도',
            data: [],
            fill: false,
            borderColor: 'rgb(54, 162, 235)',
            backgroundColor: 'transparent',
          }, {
            type: 'line',
            label: '미배정',
            data: [],
            fill: false,
            borderColor: 'rgba(255, 99, 132)',
            backgroundColor: 'transparent',
          }, {
            type: 'line',
            label: '진행중',
            data: [],
            fill: false,
            borderColor: 'rgba(75, 192, 192)',
            backgroundColor: 'transparent',
          }, {
            type: 'line',
            label: '지연중',
            data: [],
            fill: false,
            borderColor: 'rgba(255, 205, 86)',
            backgroundColor: 'transparent',
          }
        ]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    };

      userList.forEach((e, i) => {
        member_achievement_config.data.labels[i] = e.nickname;
        member_achievement_config.data.datasets[0].data[i] = e.taskCount;
        member_achievement_config.data.datasets[1].data[i] = e.completeTaskCount;
        member_achievement_config.data.datasets[2].data[i] = e.beforeTaskCount;
        member_achievement_config.data.datasets[3].data[i] = e.ongoingTaskCount;
        member_achievement_config.data.datasets[4].data[i] = e.delayTaskCount;
      });

      const member_achievement_chart = new Chart(
        document.getElementById("member_achievement_chart"),
        member_achievement_config
      );
    },
    error: function (err) {
			console.log("toMemberAchievementChart() err status : " + err.status);
		}
  });
}
function get_cproj_member_achievement() {
	let search = location.search;
	let params = new URLSearchParams(search);
	let getData = params.get('cprojNo');
	console.log(getData);
	
	$.ajax({
		type: "post",
		url: "/toCMemberAchievementChart.do",
		data: {"cprojNo" : getData},
		dataType: "json",
		success: function (userList) {
			
			console.log("cproj_chart success");
			
			let member_achievement_config = {
					type: 'scatter',
					data: {
						labels: [],
						datasets: [
							{
								type: 'bar',
								label: '총 업무량',
								data: [],
								borderColor: 'rgb(201, 203, 207)',
								backgroundColor: 'rgba(201, 203, 207, 0.2)',
								borderWidth: 1,
								maxBarThickness: 100,
							}, {
								type: 'line',
								label: '진척도',
								data: [],
								fill: false,
								borderColor: 'rgb(54, 162, 235)',
								backgroundColor: 'transparent',
							}, {
								type: 'line',
								label: '미배정',
								data: [],
								fill: false,
								borderColor: 'rgba(255, 99, 132)',
								backgroundColor: 'transparent',
							}, {
								type: 'line',
								label: '진행중',
								data: [],
								fill: false,
								borderColor: 'rgba(75, 192, 192)',
								backgroundColor: 'transparent',
							}, {
								type: 'line',
								label: '지연중',
								data: [],
								fill: false,
								borderColor: 'rgba(255, 205, 86)',
								backgroundColor: 'transparent',
							}
							]
					},
					options: {
						scales: {
							y: {
								beginAtZero: true
							}
						}
					}
			};
			
			userList.forEach((e, i) => {
				member_achievement_config.data.labels[i] = e.nickname;
				member_achievement_config.data.datasets[0].data[i] = e.taskCount;
				member_achievement_config.data.datasets[1].data[i] = e.completeTaskCount;
				member_achievement_config.data.datasets[2].data[i] = e.beforeTaskCount;
				member_achievement_config.data.datasets[3].data[i] = e.ongoingTaskCount;
				member_achievement_config.data.datasets[4].data[i] = e.delayTaskCount;
			});
			
			const get_cproj_member_achievement = new Chart(
					document.getElementById("get_cproj_member_achievement"),
					member_achievement_config
			);
		},
		error: function (err) {
			console.log("toCMemberAchievementChart() err status : " + err.status);
		}
	});
}


// 월별 그래프 차트 출력 메서드 *(사용중지)
function monthTaskGraph() {
	var test_data_bar = {
		type: "bar",
		data: {
			labels: [
				"1월",
				"2월",
				"3월",
				"4월",
				"5월",
				"6월",
				"7월",
				"8월",
				"9월",
				"10월",
				"11월",
				"12월",
			],
			datasets: [
				{
					label: "진행중",
					backgroundColor: "rgba(75, 192, 192, 0.2)",
					borderColor: "rgb(75, 192, 192)",
					data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
					borderWidth: 1,
				},
				{
					label: "지연",
					backgroundColor: "rgba(255, 205, 86, 0.2)",
					borderColor: "rgb(255, 205, 86)",
					data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
					borderWidth: 1,
				},
				{
					label: "완료",
					backgroundColor: "rgba(54, 162, 235, 0.2)",
					borderColor: "rgb(54, 162, 235)",
					data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
					borderWidth: 1,
				},
				{
					label: "미배정",
					backgroundColor: "rgba(255, 99, 132, 0.2)",
					borderColor: "rgb(255, 99, 132)",
					data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
					borderWidth: 1,
				},
			],
		},
		options: {
			scales: {
				y: {
					stacked: true,
				},
				x: {
					stacked: true,
				},
			},
		},
	};

  let dataset = test_data_bar.data.datasets;
	let labels = test_data_bar.data.labels;
  let user_name = document.getElementsByClassName('user_nickname')[0].innerText;

  $.ajax({
    type: "post",
    url: "/getFormatTaskListByUserId.do",
    data: "data",
    success: function (res) {
      
    },
    error: function (err) {
			console.log("monthTaskGraph() err status : " + err.status);
		}
  });

  projectList.forEach(project => {
		let startMonth = (new Date(project.startdate).getMonth()) + 1;
		let endMonth = (new Date(project.enddate).getMonth()) + 1;
		let thisYear = new Date().getFullYear();
		let projEndYear = new Date(project.enddate).getFullYear();

		endMonth = (startMonth == endMonth ? endMonth + 1 : endMonth);
		
		if(projEndYear < thisYear) { return false; }
		
		// 마감 년도가 올해 년도보다 크다면 올해 월별 차트는 시작월부터 12월까지 출력. 
		let viewMonth = projEndYear > thisYear ? 12 : endMonth;
		if(projEndYear > thisYear){
			// 프로젝트 상태와 비교하기 위한 for문
			for (let i = 0; i < dataset.length; i++) {
				// 프로젝트 상태와 같다면 if문으로 이동
				if(project.status === dataset[i].label){
					// 1월부터 12월까지 for문을 돌린다.
					for (let j = startMonth - 1; j < labels.length; j++) {
						// 프로젝트 종료일까지 데이터 넣어주고, 나머진 0으로 처리
						if(j < viewMonth){
							dataset[i].data[j] += 1;
						}
					}
				}
			}
		} else { // 올해 안에 끝나는 프로젝트라면 startMonth ~ endMonth까지만 그래프 출력
				for (let i = 0; i < dataset.length; i++) {
					if(project.status === dataset[i].label){
						for (let j = startMonth - 1; j < endMonth; j++) {
							if(j < viewMonth){
								dataset[i].data[j] += 1;
							}
						}
					}
				}
		}
  });
	// canvas 드로잉
	const custom_monthTaskBarChart = new Chart(
		document.getElementById("custom_monthTaskBarChart"),
		test_data_bar
	);
};


