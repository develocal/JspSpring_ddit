<%@page import="org.apache.velocity.runtime.directive.Foreach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

<%--     <meta charset="utf-8"> 


    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>INSPINIA | Calendar</title>
--%>


<link href="<%=request.getContextPath() %>/resources/css/schedule/css/bootstrap.min.css" rel="stylesheet">

<link href="<%=request.getContextPath() %>/resources/css/schedule/css/plugins/iCheck/custom.css" rel="stylesheet">

<link href="<%=request.getContextPath() %>/resources/css/schedule/css/plugins/fullcalendar/fullcalendar.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/resources/css/schedule/css/plugins/fullcalendar/fullcalendar.print.css" rel='stylesheet' media='print'>

<link href="<%=request.getContextPath() %>/resources/css/schedule/css/animate.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/resources/css/schedule/css/style.css" rel="stylesheet">

<style>
	#addScheduleBtn{
		float : right;
		margin-left : 10px;
	}
	
	.ibox-title{
		padding-right : 10px;
		padding-top :0px;
		padding-bottom :5px;
		
	}
	
	#addToDoListBtn {
		float : right;
		margin-right : 10px;
		margin-bottom : 20px;
		
	}
	
	#toDoListH5 {
		display : inline;
	}
	
	#xspan{
		float : right;
	}
	
	 #xspan:hover{
     	text-decoration:none;
     	color:#EBA094;
     	border:1px solid gray;
     }
     
     #sortCode{
     	float : right;
     	display : inline;
     	width : 130px;
     	margin-left : 10px;
     }
</style>

</head>

<body>

<%-- <div id="wrapper"> --%>

<%--<div id="page-wrapper" class="gray-bg">--%>

<div class="wrapper wrapper-content">
    <div class="row animated <%-- fadeInDown --%>">
        <%-- <p>${jsonList}</p> --%>
        <div class="col-lg-9">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5> </h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="#" class="dropdown-item">Config option 1</a>
                            </li>
                            <li><a href="#" class="dropdown-item">Config option 2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                	<button type="button" id="addScheduleBtn" class="btn btn-outline btn-danger" onclick="OpenWindow('<%=request.getContextPath()%>/mypage/schedule_management/addSchedule','','850','620');">일정 추가</button>
                    
                    <select name="schedule_sort_code" class="form-control" id="sortCode" onchange="changeScheduleList(this.value)">
                    	<option value="all" selected>전체 일정</option>
		            	<c:forEach items="${scheduleSortcodeList }" var="sortCodeList">
		            		<option value="${sortCodeList.schedule_sort_code }">${sortCodeList.schedule_sort_name}</option>
		            	</c:forEach>
	           		 </select>
                    
                    <div id="calendar"></div>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5 id="toDoListH5"><br/>To Do List</h5> 
                    <button type="button" id="addToDoListBtn" class="btn btn-outline btn-danger" onclick="OpenWindow('<%=request.getContextPath()%>/mypage/schedule_management/addToDoList','','850','620');">To Do List 추가</button>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="#" class="dropdown-item">Config option 1</a>
                            </li>
                            <li><a href="#" class="dropdown-item">Config option 2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div id='external-events'>
                        <p>달력으로 드래그해주세요.</p>
                        <!-- <div class='external-event navy-bg'>에어컨 필터 청소</div>
                        <div class='external-event navy-bg'>환기</div>
                        <div class='external-event navy-bg'>전등 점검</div>
                        <div class='external-event navy-bg'>컴퓨터 전원 점검</div>
                        <div class='external-event navy-bg'>빔프로젝터 점검</div> -->
                        
                        <c:forEach items="${allToDoList }" var="allToDoList">
                        <%-- ${allToDoList[n].schedule_name} --%>
                        	<%-- <form id="${allToDoList.schedule_name}_form" method="post"> --%>	<!--폼id에 한글을 써서 어케가져왕 못가져오는구나...ㅜ..자갸근데 이게 foreach문안에있어서 저 allToDoList를 하나씩 form으로 만들테니 여러개의 form이 생김 -->
	                        	<div onclick='remove_go("${allToDoList.schedule_code}")'>
	                        	<div class='external-event navy-bg'
	                        	data-event='
	                        	{
	                        	"schedule_name" : "${allToDoList.schedule_name}",
	                        	"schedule_code" : "${allToDoList.schedule_code}",
	                        	"schedule_sort_code" : "${allToDoList.schedule_sort_code}",
	                        	"schedule_contents" : "${allToDoList.schedule_contents}",
	                        	"schedule_start_date" : "${allToDoList.schedule_start_date}",
	                        	"schedule_end_date" : "${allToDoList.schedule_end_date}",
	                        	"schedule_start_time" : "${allToDoList.schedule_start_time}",
	                        	"schedule_end_time" : "${allToDoList.schedule_end_time}",
	                        	"schedule_istodolist" : "${allToDoList.schedule_istodolist}",
	                        	"schedule_isallday" : "${allToDoList.schedule_isallday}"
	                        	
	                        	}'>${allToDoList.schedule_name}</div>
	                        	</div>
	                        	<%-- <input type="hidden" name="schedule_name" value="${allToDoList.schedule_name}">
	                        	<input type="hidden" name="schedule_code" value="${allToDoList.schedule_code}">
	                        	<input type="hidden" name="schedule_sort_code" value="${allToDoList.schedule_sort_code}">
	                        	<input type="hidden" name="schedule_contents" value="${allToDoList.schedule_contents}">
	                        	<input type="hidden" name="schedule_start_date" value="${allToDoList.schedule_start_date}">
	                        	<input type="hidden" name="schedule_end_date" value="${allToDoList.schedule_end_date}">
	                        	<input type="hidden" name="schedule_start_time" value="${allToDoList.schedule_start_time}">
	                        	<input type="hidden" name="schedule_end_time" value="${allToDoList.schedule_end_time}">
	                        	<input type="hidden" name="schedule_istodolist" value="${allToDoList.schedule_istodolist}">
	                        	<input type="hidden" name="schedule_isallday" value="${allToDoList.schedule_isallday}">
	                        </form>	 --%>
                        </c:forEach>
                        	<input type="hidden" id="todoDropData"/>
                        
                           	<!-- <form id="testForm" method="post" >	폼id에 한글을 써서 어케가져왕 못가져오는구나...ㅜ..자갸근데 이게 foreach문안에있어서 저 allToDoList를 하나씩 form으로 만들테니 여러개의 form이 생김
	                        	<div class='external-event navy-bg' data-event='{ "title": "my event", "duration": "02:00" }'>>dqwdqw</div>
	                        	<input type="text" name="scheduleisallday" value="qweqwe">
	                        </form> -->
                        
                        <p class="m-t">
                            <input type='checkbox' id='drop-remove' class="i-checks icheckbox_square-grey" /> <label for='drop-remove'>드래그 후 목록에서 삭제</label>
                        </p>
                    </div>
                </div>
            </div>
            
        </div>
    </div>
</div>
<%-- </div>--%>
<%-- </div> --%>



<!-- Mainly scripts -->
<script src="<%=request.getContextPath() %>/resources/js/schedule/js/jquery-3.1.1.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/schedule/js/plugins/fullcalendar/moment.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/schedule/js/popper.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/schedule/js/bootstrap.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/schedule/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/schedule/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="<%=request.getContextPath() %>/resources/js/schedule/js/inspinia.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/schedule/js/plugins/pace/pace.min.js"></script>

<!-- jQuery UI  -->
<script src="<%=request.getContextPath() %>/resources/js/schedule/js/plugins/jquery-ui/jquery-ui.min.js"></script>

<!-- iCheck -->
<script src="<%=request.getContextPath() %>/resources/js/schedule/js/plugins/iCheck/icheck.min.js"></script>

<!-- Full Calendar -->
<script src="<%=request.getContextPath() %>/resources/js/schedule/js/plugins/fullcalendar/fullcalendar.min.js"></script>

<script>
function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight) {
	winleft = (screen.width - WinWidth) / 2;
	wintop = (screen.height - WinHeight) / 2;
	var win = window.open(UrlStr , WinTitle , "scrollbars=yes,width="+ WinWidth +", height="+ WinHeight +", top="+ wintop +", left=" + winleft + ", resizable=yes, status=yes"  );
    win.focus() ; 
}

function OpenWindowNoScr(UrlStr, WinTitle, WinWidth, WinHeight) {
	winleft = (screen.width - WinWidth) / 2;
	wintop = (screen.height - WinHeight) / 2;
	var win = window.open(UrlStr , WinTitle , "width="+ WinWidth +", height="+ WinHeight +", top="+ wintop +", left=" + winleft + ", resizable=yes, status=yes"  );
    win.focus() ; 
}

function changeScheduleList(sortCode){
	
	
	
	//document.href = "/mypage/schedule_management/superintendent?sortCode=" + sortCode;
	<%-- $.ajax({
    	type : 'POST',
    	//contentType: "application/json; charset=utf-8;",
    	data : {sortCode : sortCode},
    	dataType : "text",
    	url : '<%=request.getContextPath()%>/mypage/schedule_management/superintendent/test',
    	success : function(result){
    		alert("일정 출력 완료")
    	},
    	error : function(xhr){
    		alert("일정 출력 실패")
    		alert(xhr.status);
    	}
    	
    })  --%>
	
	
	// 분류에 맞게 달력에 띄우는 것.
	$('#calendar').fullCalendar("removeEvents");
	
	
	for(var i = 0; i < allScheduleList.length; i++){
		if(sortCode == "all"){
			$('#calendar').fullCalendar("renderEvent", allScheduleList[i]);
			continue;
		}
			
		if(allScheduleList[i].schedule_sort_code == sortCode){
			//newScheduleList.push(allScheduleList[i]);
			$('#calendar').fullCalendar("renderEvent", allScheduleList[i]);
		}
	}
		
	
	
	//$('#calendar').fullCalendar("renderEvents", newScheduleList);
	
	//$('#calendar').fullCalendar("refetchEvents")
	//$('#calendar').fullCalendar("updateEvents", newScheduleList); 
}

var allScheduleList= ${jsonList};

    $(document).ready(function() {
   	
    	
    	
    	
    	
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-grey',
                radioClass: 'iradio_square-grey'
            });

        /* initialize the external events
         -----------------------------------------------------------------*/


        $('#external-events div.external-event').each(function() {

            // store data so the calendar knows to render an event upon drop
            $(this).data('event', {
                title: $.trim($(this).text()), // use the element's text as the event title
                stick: true // maintain when user navigates (see docs on the renderEvent method)
            });

            // make the event draggable using jQuery UI
            $(this).draggable({
                zIndex: 1111999,
                revert: true,      // will cause the event to go back to its
                revertDuration: 0  //  original position after the drag
            });

        });


        /* initialize the calendar
         -----------------------------------------------------------------*/
         
         /* var date = new Date();
        var d = date.getDate();
        var m = date.getMonth();
        var y = date.getFullYear(); 
       date.formet('yyyy-mm-dd')  */
        $('#calendar').fullCalendar({
        	// 달력 높이
        	height: 600,
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay'
            },
            editable: true,
            durationEditable: true,
            droppable: true, // this allows things to be dropped onto the calendar
            eventDrop: function(event,dayDelta,minuteDelta,allDay,revertFunc) { 
            		//alert( event.title + " was moved " + dayDelta + " days and " + minuteDelta + " minutes." );
            		
            		if (!confirm("일정을 변경하시겠습니까?")) { 
            			revertFunc(); 
            		} 
            		//alert(event.end);
            		
            		event_schedule_istodolist=0,
    				event_allDay=0
            		if(event.schedule_istodolist=='1'){
            				event_schedule_istodolist= 1;
            		} else{
            			event_schedule_istodolist= 0;
            		}
            		
            		if(event.allDay==true){
        				event_allDay= 1;
        			} else{
        				event_allDay= 0;
        			}
            		
            		var form = {
    			    		schedule_code:event.schedule_code,
                    		schedule_sort_code:event.schedule_sort_code,
                    		schedule_name:event.title,
                    		schedule_contents:event.schedule_contents,
                    		schedule_start_date:event.start,
                    		schedule_end_date:event.end,
                    		schedule_start_time:event.schedule_start_time,
                    		schedule_end_time:event.schedule_end_time,
                    		schedule_istodolist:event_schedule_istodolist,
                    		schedule_isallday:event_allDay/* ,
                    		color:event.color	 */
    			    }
            		
                	$.ajax({
                    	type : 'POST',
                    	contentType: "application/json; charset=utf-8;",
                    	data : JSON.stringify(form),
                    	dataType : "json",
                    	url : '<%=request.getContextPath()%>/mypage/schedule_management/dragUpdate',
                    	success : function(result){
                    		alert("일정 수정이 완료되었습니다.");
                    	},
                    	error : function(){
                    		alert("서버상의 문제로 업데이트에 실패했습니다.\n 관리자에게 문의 바랍니다.\n")
                    	}
                    	
                    }) 
            },
            
            drop: function(date, jsEvent, ui,resourceId) {
                if ($('#drop-remove').is(':checked')) {
                    $(this).remove();
                    // 체크돼있으면 삭제도시켜야해.에이젝스로...데이터보내서...삭제시키자..
                }
                
            	// $(this).attr("data-event") : 이거를 쓰면!!!!!!  div의 내용이들어옵니다.
            	
            	//var testForm = document.getElementById('testForm');
            	//testForm.submit();
            	//console.log(date, jsEvent, ui,resourceId);
            	// 여기가 못한 ajax!!!!!!
            	// alert(date.format());
            	//alert($(this).text()); 
            	
            	/* formnametext = $(this).text()+'_form'; // == 전송될 form태그의 이름.
            	alert(formnametext);
            	 */
            	// null임
            	//formData = new FormData(document.getElementById(formnametext));// formData에 담는다.
            	//$formDataasdasd = document.getElementById('testForm');
            	//console.log(JSON.stringify(formData));
            	//console.log($(formData).serialize());
            	
            	
            	//var euntest = $formDataasdasd.serialize();
            	
            	//console.log(euntest);
            
            	
            	//console.log($(formData))
            	//alert(formData);
            	// db저장. form을 보내버릴것이다
            	// $(this).text() 랑 name이 일치하는 애를 보내야함.
            	
            	var formData = JSON.parse($(this).attr("data-event"));
            	formData.date = date.format();
            	formData.removechk = $('#drop-remove').is(':checked');
            	/* formData += '{"newDate" : date}'  */
            	 
            	console.log(formData);
            	
                $.ajax({
                	url : '<%=request.getContextPath()%>/mypage/schedule_management/todoDragUpdate',
                	type : 'POST',
                	/* data : JSON.stringify(formData),
                	dataType : "json", */
                	data :  formData,
                	dataType : "json",
                	success : function(result){
                		alert("일정이 등록되었습니다.");
                		//alert(data);
                	},
                	error : function(){
                		alert("서버상의 문제로 업데이트에 실패했습니다.\n 관리자에게 문의 바랍니다.\n")
                	}
                })
                
            },
            
            eventClick : /* fn_calEventClick */ // 이벤트 클릭 시
    			function(event){
    			
				var event_schedule_code = event.schedule_code;

    			OpenWindow('<%=request.getContextPath()%>/mypage/schedule_management/detailSchedule?schedule_code='+event_schedule_code+'  ','','850','620');
   			 },
            dateClick : /* fn_calDateClick, */ // 백그라운드 클릭시
            			function(){
            			alert("dateBackGroundClick");
            },
            events : ${jsonList}
            
            
        });

		//calendar.render(); 
    });
    
    function remove_go(schedule_code){
    	if (!confirm("To Do List를 삭제하시겠습니까?")) { 
    		revertFunc(); 
    	}
    	
    	location.href="<%=request.getContextPath()%>/mypage/schedule_management/removeToDoList?schedule_code="+schedule_code;
    	
    }
    		
</script>
</body>

</html>
