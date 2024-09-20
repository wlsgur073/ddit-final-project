export default [
{
	project:[
				{
					column:
						[
							{ title: "프로젝트명" },
							{ title: "담당자" },
							{ title: "시작일" },
							{ title: "진행상태" }
							]
				},
				{
					content:
						[
							  [
							    '<a class="project-name" href="./my-project.html">남양(주) 사이트 유지보수<a> <i class="fa fa-globe fa-lg"></i>',
							    "mimi2022",
							    "2011-04-25",
							    '<i class="fa fa-spinner fa-pulse"></i> 진행중',
							  ],
							  [
							    '테슬라 자율주행 렌더링 <i class="fa fa-shield fa-lg"></i>',
							    "StoneEnergyTA",
							    "2021-08-21",
							    '<i class="fa fa-check-circle-o fa-lg"></i> 완료',
							  ],
							  [
							    '현대자동차 배터리 용접 <i class="fa fa-lock fa-lg"></i>',
							    "SpearWaterDA",
							    "2020-03-11",
							    '<i class="fa fa-spinner fa-pulse"></i> 진행중',
							  ],
							  [
							    '구글 자연어 DB 프로젝트 <i class="fa fa-shield fa-lg"></i>',
							    "BronzeRootUA",
							    "2021-01-07",
							    '<i class="fa fa fa-exclamation-triangle" style="color:red"></i> 파기요청',
							  ],
							  [
							    '하나은행 공식홈페이지 <i class="fa fa-globe fa-lg"></i>',
							    "EnergyWookAA",
							    "2020-01-11",
							    '<i class="fa fa-spinner fa-pulse"></i> 진행중',
							  ],
							  [
							    '대한항공 키오스크 보수 <i class="fa fa-lock fa-lg"></i>',
							    "RealHyeokPL",
							    "2021-01-11",
							    '<i class="fa fa-spinner fa-pulse"></i> 진행중',
							  ],
						]
				},
				{
					sort:[
						{ title: '진행상태' },
						{ title: '진행중' },
						{ title: "파기요청" },
						{ title: "완료" },
					  ]
				}
			]
	}
]