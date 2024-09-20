<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page trimDirectiveWhitespaces="true" %>
  <body>


	 <div class="right_col" role="main">
        <!-- 내 작업 바디 시작 -->
        <div class="x_panel">

          <div class="x_title">
			<h2>
				<a href="#home"><i class="fa fa-user fa-2x"></i></a> </i> 통합검색
			</h2>

			<div class="clearfix"></div>
          </div>

          <div class="x_content">

				
        




			<div class="col-md-6 col-sm-6  ">
		                <h3><i class="fa fa-search"></i>&nbsp;프로젝트 검색 결과</h3>
		
		                <div id="projectTable"></div>
		
		              </div>
		
		
		
		              <div class="col-md-6 col-sm-6  ">
		                <h3><i class="fa fa-search"></i>&nbsp;닉네임 검색 결과</h3>
		                  <div id="example">
		                    <div id="NickName"></div>
		           </div>
		     </div>




              <div class="col-md-6 col-sm-6  ">
                <h3><i class="fa fa-trophy "></i>&nbsp;추천 프로젝트</h3>
                <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel"
                  data-interval="false">
                  <ol class="carousel-indicators" >
                    <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active" style="background-color: black"></li>
                    <li data-target="#carouselExampleCaptions" data-slide-to="1" style="background-color: black"></li>
                    <li data-target="#carouselExampleCaptions" data-slide-to="2" style="background-color: black"></li>
                  </ol>
                  <div class="carousel-inner">
                  
                   <c:forEach items="${popProj }" var="proj" varStatus="statuse"> 
                  
                   	<c:if test="${statuse.count eq 1}">
                   
                   
                   
                   
                    <div class="carousel-item active">
                    
                    	  <div class="project-list card" style="height: 230px; padding: 5px;">
				    															
							      <div class="card-body project-list">
							         <div class="user-block mt-3 mb-3">
				                      <h2>
				                        <div class="projSearchTitle"><a href="/app/project/main?projNo=${proj.projNo}" class="project-list-a searchProjectTitle2">&nbsp;&nbsp;${proj.title }</a></div> <span class="badge badge-success float-right">${proj.status }</span>
				                      </h2>
				                    </div>
				                    <!-- /.user-block -->
				                    <div class="col-sm-7" style="height: 100px;"><p class="searchIntro"><c:out  value='${proj.intro.replaceAll("\\\<.*?\\\>","").replace("&nbsp;"," ")}' /></p></div> 
				                    <div class="col-sm-5 text-right">
				                      
				                        <span style="background:rgb(239,239,239); width:150px;">기간 : ${proj.startdate.substring(0,10) }~${proj.enddate.substring(0,10) }</span>
				                    
				                    
				                      <p >담당자 : ${proj.plId}</p>
				                       <img src="/user/getPicture.do?picture=${proj.picture}" alt="" class="img-circle img-fluid projImg">
				                      
				                      
				                    </div>
				                     <div class="col-sm-8 text-truncate"></div>
				                    <div class="col-sm-4 text-right">
				                    </div>
				                    
				                    <br><br><br>
				                    <div class="col-sm-8 row d-flex justify-content-evenly">
				                       
				                        <div class="ml-3 d-flex align-items-center">
				                        	<h4 class="mb-0">
				                      
					                      		<c:forEach items="${proj.tagName }" var="tagName" varStatus="status"> 
					                      
							                          <span class="badge badge-success"  style="cursor : pointer;" onclick="changeTagNo('${tagName}');">${tagName}</span>
							                      
						                      
					                      
					                      		</c:forEach>
				                      
				                        	</h4>
				                        
				                        </div>
				                        
				                    </div>
				                    <div class="col-sm-4 ml-3 text-right" style="height:50px">
				                        <ul class="list-inline" style="display: inline-flex">
				                      
				                          
				                          
				                        </ul>
				                    </div>
				                    
				                    
				                    
				                  </div>
             				   </div>
                    	
                    
                    

                      <div class="carousel-caption d-none d-md-block">
                      </div>
                    </div>
                    
                    </c:if>
                    
                    <c:if test="${statuse.count ne 1}">
                   
                    	 <div class="carousel-item">
                    
                    	  <div class="project-list card" style="height: 230px; padding: 5px;">
				    															
							      <div class="card-body project-list">
							         <div class="user-block mt-3 mb-3">
				                   		<h2>
				                        <div class="projSearchTitle"><a href="/app/project/main?projNo=${proj.projNo}" class="project-list-a">&nbsp;&nbsp;${proj.title }</a></div> <span class="badge badge-success float-right">${proj.status }</span>
				                      </h2>
				                    </div>
				                    <!-- /.user-block -->
				                    <div class="col-sm-7" style="height: 100px;"><p class="searchIntro"><c:out  value='${proj.intro.replaceAll("\\\<.*?\\\>","").replace("&nbsp;"," ")}' /></p></div>


				                    <div class="col-sm-5 text-right">
				                      
				                        <span style="background:rgb(239,239,239); width:150px;">기간 : ${proj.startdate.substring(0,10) }~${proj.enddate.substring(0,10) }</span>
				                    
				                    <%--   <p>
				                        <span class="ml-3 rounded rounded-2" style="background:rgb(239,239,239);">갱신일 : ${proj.startdate.substring(0,10) }</span>
				                      </p> --%>
				                      <p >담당자 : ${proj.plId}</p>
				                       <img src="/user/getPicture.do?picture=${proj.picture}" alt="" class="img-circle img-fluid projImg">
				                      
				                      
				                    </div>
				                     <div class="col-sm-8 text-truncate"></div>
				                    <div class="col-sm-4 text-right">
				                    </div>
				                    
				                    <br><br><br>
				                    <div class="col-sm-8 row d-flex justify-content-evenly">
				                        <div class="ml-3 d-flex align-items-center">
				                        	<h4 class="mb-0">
				                      
					                      		<c:forEach items="${proj.tagName }" var="tagName" varStatus="status"> 
					                      
							                          <span class="badge badge-success" style="cursor : pointer;" onclick="changeTagNo('${tagName}');">${tagName}</span>
							                      
						                      
					                      
					                      		</c:forEach>
				                      
				                        	</h4>
				                        
				                        </div>
				                        
				                    </div>
				                    <div class="col-sm-4 ml-3 text-right" style="height:50px">
				                        <ul class="list-inline" style="display: inline-flex">
				                      
				                          
				                          
				                        </ul>
				                    </div>
				                    
				                    
				                    
				                  </div>
             				   </div>
                    	
                    
                    

                      <div class="carousel-caption d-none d-md-block">
                      </div>
                    </div>
                    
                    </c:if>
                    
                    
                    
                 </c:forEach>   
                    
                    
                  </div>
                  
                  
                  <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                  </a>
                  <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                  </a>
                </div>
                
                
              </div>




              <div class="col-md-6 col-sm-6  ">
                <h3><i class="fa fa-trophy "></i>&nbsp;추천인</h3>
                <div id="carouselExampleCaptionsHuman" class="carousel slide" data-ride="carousel"
                  data-interval="false" >
                  <ol class="carousel-indicators" style="margin-bottom: 35px;">
                  
   
                    <li data-target="#carouselExampleCaptionsHuman" data-slide-to="0" class="active" style="background-color: black;"></li>
                    <li data-target="#carouselExampleCaptionsHuman" data-slide-to="1" style="background-color: black;"></li>
                    <li data-target="#carouselExampleCaptionsHuman" data-slide-to="2" style="background-color: black;"></li>
                  </ol>
                  
                  <div class="carousel-inner">
                  
                  
                  <c:forEach items="${popUser }" var="pUser" varStatus="status"> 
                  
       
					
						<c:if test="${status.count eq 1}">


										
						        <div class="carousel-item active">
			                      <div class="well profile_view d-block w-100" style="height: 230px; background-color: white;">
			                        <div class="col-sm-12">
			                        
			                          <h4 onclick="goProfile('${pUser.userId }')" style="cursor: pointer;">
				                        <a href="/user/profile?userId=${pUser.userId }" class="project-list-a">&nbsp;&nbsp;${pUser.nickname }</a> 
				                      </h4>
			                        

			                          <div class="left col-md-7 col-sm-7">
			                            <h2>${pUser.userId }</h2>
			                            <p class="searchUserIntro">${pUser.intro } </p>
			                            <ul class="list-unstyled">
			
			                            </ul>
			                          </div>
			                          <div class="right col-md-5 col-sm-5 text-center">
			                            <img src="/user/getPicture.do?picture=${pUser.picture}" alt="" class="img-circle img-fluid totalSearchImg">
			                          </div>
			                        </div>
			                        <div class=" row">
			                          <div class=" col-sm-6 ">
			
			                            
			                          </div>
			
			                        </div>
			                      </div>
			
			                      <div class="carousel-caption d-none d-md-block">
			                      </div>
			                    </div>


						</c:if>
						
						
						<c:if test="${status.count ne 1}">
						
								
			                     <div class="carousel-item ">
			                      <div class="well profile_view d-block w-100" style="height: 230px; background-color: white;">
			                        <div class="col-sm-12">
			                        
			                           <h4 onclick="goProfile('${pUser.userId }')" style="cursor: pointer;">
				                        <a href="/user/profile?userId=${pUser.userId }" class="project-list-a">&nbsp;&nbsp;${pUser.nickname }</a> 
				                      </h4>
			                        
			                          
			                          
			                          
			                          <div class="left col-md-7 col-sm-7">
			                            <h2>${pUser.userId }</h2>
			                            <p class="searchUserIntro">${pUser.intro } </p>
			                            <ul class="list-unstyled">
			
			                            </ul>
			                          </div>
			                          <div class="right col-md-5 col-sm-5 text-center">
			                            <img src="/user/getPicture.do?picture=${pUser.picture}" alt="" class="img-circle img-fluid totalSearchImg">
			                          </div>
			                        </div>
			                        <div class=" row">
			                          <div class=" col-sm-6 ">
			
			                          
			                          </div>
			
			                        </div>
			                      </div>
			
			                      <div class="carousel-caption d-none d-md-block">
			                      </div>
			                    </div>

						
						</c:if>
					

                   </c:forEach>
                    

                    
                  </div>
                  <a class="carousel-control-prev" href="#carouselExampleCaptionsHuman" role="button" data-slide="prev" >
                    <span class="carousel-control-prev-icon" aria-hidden="true" style="margin-bottom: 19.5px">
                    </span>
                    <span class="sr-only">Previous</span>
                  </a>
                  <a class="carousel-control-next" href="#carouselExampleCaptionsHuman" role="button" data-slide="next" >
                    <span class="carousel-control-next-icon" aria-hidden="true" style="margin-bottom: 19.5px" ></span>
                    <span class="sr-only">Next</span>
                  </a>
                </div>
              </div>



    		</div>

          </div>
          <!-- x_content 끝 -->

        </div>
        <!-- 내 작업 바디 끝 -->
      </div>
      <!-- /page content -->







<script>




		function goProfile(userId){
			
			
			location.href='/user/profile?userId='+userId;

			
		}
function selectSearchId(userId){
	

	location.href='/user/profile?userId='+userId;

	
}

function selectSearchProject(projNo){
	

	location.href='/app/project/main?projNo='+projNo;

	
	
}

function selectSearchColProject(projNo){
	
	
	location.href='/app/collabo/main?cprojNo='+projNo;
	
}


function selectSearchNickName(nickname){
	
	

	
	$.ajax({
		url : "/search/changeIdByNickName",
		type : 'POST',
		data : {"nickname":nickname},
		success : function(data) {

			location.href='/user/profile?userId='+data;

		},
		
		
		
		error : function(error) {
			alert("닉네임이 존재하지 않습니다!");
		}
	});
}


function changeTagNo(tagName){
	

	$.ajax({
		url : "/search/changeTagTitleByTagNo",
		type : 'POST',
		data : {"tagName":tagName},
		success : function(data) {
			
			
			location.href='/app/tag?tagNo='+data;
			
			
		},
		
		
		
		error : function(error) {
			alert("닉네임이 존재하지 않습니다!");
		}
	});
}



window.addEventListener('load', function(){
	
	
	
	var totalSearchInput = document.getElementById('totalSearchInput');
	totalSearchInput.value = '${resultData}';
	
	
	
	
		var searchBaseUrlSeok="/search/"
			
          var nickNameDataSource = new kendo.data.DataSource({
                 transport: {
                     read: {
                    
                         url: searchBaseUrlSeok + "readUser?result=${resultData}",
                         dataType: "json"
                     },
                  
                     parameterMap: function (options, operation) {
                        if (operation !== "read" && options.models) {
                            // return { models: kendo.stringify(options.models) };
                        	 return JSON.stringify(options);                         
                        }
                     }
                     
                 },
                 batch: true,
                 pageSize: 8,
                
                 
                 schema: {
                     model: {
                         id: "nickName",
                         fields: {
                        	 nickname: { editable: false, type: "String" },
                             userId: { editable: true, type: "String"},
                             picture: { editable: true, type: "String"}
    	                    
                         }
                     }
                 }
             });

          
          var projectDataSource = new kendo.data.DataSource({
              transport: {
                  read: {
                 
                      url: searchBaseUrlSeok + "readProject?result=${resultData}",
                      dataType: "json"
                  },
               
                  parameterMap: function (options, operation) {
                     if (operation !== "read" && options.models) {
                         // return { models: kendo.stringify(options.models) };
                     	 return JSON.stringify(options);                         
                     }
                  }
                  
              },
              batch: true,
              pageSize: 5,
              schema: {
                  model: {
                      id: "projNo",
                      fields: {
                    	  projNo: { editable: false, type: "String" },
                     	  title: { editable: false, type: "String" },
                     	  plId: { editable: true, type: "String"},
                     	  plIdSec: { editable: true, type: "String"},
                     	  status: { editable: true, type: "String"}
 	                   
                      }
                  }
              }
          });
          
          

          
         $("#NickName").kendoGrid({
             dataSource: nickNameDataSource,
             pageable: true,
             width:540,
             height: 400,
             
             noRecords: {
     			template: function(e) {
     				return "<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;일치하는 닉네임이 존재하지 않습니다</h2>";
     			}
     		},
     		messages: {
                commands: {
                    search: "검색"
                }
            }, 
     		toolbar: ["search"],
            search: {
                fields: [
                    { name: "nickname", operator: "contains" },
                    { name: "userId", operator: "contains" }
                 
                ]
            },
          
     		
             
             columns: [
                
		            	 {
			                 field: "nickname",
			                 title: "닉네임",
			                 template: "<span class='text-truncate' style='cursor : pointer;' onclick=selectSearchId('#=userId #')> <img onclick=selectSearchId('#=userId #') src='/user/getPicture.do?picture=#:picture #' alt='' class='img-circle img-fluid projImg'> #:nickname #</span>",
			                 width: 100,
			                 encoded: false
			             },
		            	 
			             {
			                 field: "userId",
			                 title: "이메일 주소",
			                 width: 100,
			                 encoded: false
			             }
		             
		             
		            

                 
                 ]
            
         });
		
         
         
         $("#projectTable").kendoGrid({
             dataSource: projectDataSource,
             pageable: true,
             width:540,
             height: 400,
             
             noRecords: {
      			template: function(e) {
      				return "<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;일치하는 프로젝트가 존재하지 않습니다</h2>";
      			}
      		},
             
      		messages: {
                commands: {
                    search: "검색"
                }
            },
      		toolbar: ["search"],
            search: {
                fields: [
                    { name: "plId", operator: "contains" },
                    { name: "title", operator: "contains" }
                 
                ]
            },

      		
             columns: [
      	 
            	 /* <span class='searchProjName'  style='cursor : pointer;' onclick=selectSearchProject('#= projNo #')> #: title #</span> */
            	 {
	                 field: "projNo",
	                 title: "프로젝트 이름",
	                 template: "#if (plIdSec !=null) {# <div class='projNameSearchDiv'><span  class='badge badge-success'>콜라보</span> <span class='searchProjName'  style='cursor : pointer;' onclick=selectSearchColProject('#=projNo#')> &nbsp;&nbsp;#: title #</span></div>  #} else {# <div class='projNameSearchDiv'><span  class='badge badge-success'>프로젝트</span>  <span class='searchProjName'  style='cursor : pointer;' onclick=selectSearchProject('#= projNo #')> #: title #</span></div> #}#",
	                 width: 140,
	                 encoded: false
	             },
            	 
	             {
	                 field: "plId",
	                 title: "담당자",						
	                 template:  "#if (plIdSec ==null) {#<div class='projectPLIdDiv'> <span class='projectPLId spanPL' style='cursor : pointer;' onclick=selectSearchNickName('#=plId#')> #: plId #</span></div> #} else {# <div class='projectPLIdDiv'><span class='projectPLId spanPL' style='cursor : pointer;' onclick=selectSearchNickName('#=plId#')> #: plId # </span><span style='cursor : pointer;' class='projectPLId' onclick=selectSearchNickName('#=plIdSec#')>,#=plIdSec#</span></div> #}#",
	                 width: 120,
	                 encoded: false
	             },
	             
	             
	             {
	                 field: "status",
	                 title: "진행상태",
	                 width: 70,
	                 encoded: false
	             }
	             
	             
	             
	             
	            /*  template :  " #if (plIdSec ==null) {# #='프로젝트'# #} else {# #='콜라보'# #} #", */
                 
                 ]
            
         });
         
  
      
         
        
         
        /*  #if (plIdSec ==null) {# <span  class='badge badge-success'>콜라보</span> #} # <span class='searchProjName'  style='cursor : pointer;' onclick=selectSearchProject('#= projNo #')> #: title #</span> */
		
});
	
	
</script>
<body>
