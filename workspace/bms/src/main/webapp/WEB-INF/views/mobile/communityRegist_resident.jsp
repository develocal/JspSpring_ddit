<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <!-- Bootstrap core CSS -->
  <link href="<%=request.getContextPath()%>/resources/mobile/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="<%=request.getContextPath()%>/resources/mobile/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/mobile/vendor/simple-line-icons/css/simple-line-icons.css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Muli" rel="stylesheet">
  
   <!-- alert -->
  <link href="<%=request.getContextPath()%>/resources/mobile/css/jquery.modal.css" type="text/css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/resources/mobile/css/jquery.modal.theme-xenon.css" type="text/css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/resources/mobile/css/jquery.modal.theme-atlant.css" type="text/css" rel="stylesheet">

  <!-- Plugin CSS -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/mobile/device-mockups/device-mockups.min.css">

  <!-- Custom styles for this template -->
  <link href="<%=request.getContextPath()%>/resources/mobile/css/new-age.min.css" rel="stylesheet">

</head>

	<body id="page-top">
	
	  <!-- Navigation -->
	  <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
	    <div class="container">
	    	<a class="navbar-brand js-scroll-trigger" style="color:black; display:inline;" href="javascript:location.href='<%=request.getContextPath()%>/community/mobileList';"><ion-icon name="arrow-round-back" style="font-size: 30px; padding-right:20px" /></a>
	    	<span style="font-weight: bold; font-size:20px;">소통게시판 등록</span>
	    </div>
	  </nav>
	
	<header class="contact" style="height: 708px;">
		  <section class="cta" style="padding-bottom: 0px; padding-top: 0px;">
		    <div class="cta-content">
		     <div class="container h-120">
			    <div class="row align-items-center h-120">
			        <div class="col-6 mx-auto">
			            <div class="jumbotron" style="height: 652px;margin-top: 90px;width: 332px;margin-left: -80;">
							<form>
							  <div class="form-group">
							    <label for="exampleFormControlInput1">제목</label>
							    <input id="title" type="text" class="form-control" value="" style="background-color:white;">
							  </div>
							  <div class="form-group" id="writer">
							    <label for="exampleFormControlInput1">작성자</label>
							    <input type="text" class="form-control" value="${loginUser.member_name }" style="background-color:white;" readonly>
							  </div>
							  <div class="form-group">
							    <label for="exampleFormControlTextarea1">내용</label>
							    <textarea id="content" class="form-control" id="exampleFormControlTextarea1" rows="13" style="background-color:white; padding-top:0px;"></textarea>
							  </div>
	  						  <div class="form-group" id="commits" style="display:inline;">
							    <input type="button" onclick="goReg();" class="btn btn-primary" class="form-control" value="등록하기" style="width:130px; margin-left:15px;" />
							  </div>
							  <div class="form-group" id="exits" style="display:inline;">
							    <input type="button" onclick="goExit();" class="btn btn-secondary"class="form-control" value="취소하기" style="width:130px;" />
							  </div>
							  
							  <input type="hidden" id="member_code" value="${loginUser.member_code}"/>
							  
							</form>
			            </div>
			        </div>
			    </div>
			 </div>
			</div>
		    <div class="overlay"></div>
		  </section>
		  
	  </header>
	
	</body>
	
	  <!-- Bootstrap core JavaScript -->
  <script src="<%=request.getContextPath()%>/resources/mobile/vendor/jquery/jquery.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/mobile/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Plugin JavaScript -->
  <script src="<%=request.getContextPath()%>/resources/mobile/vendor/jquery-easing/jquery.easing.min.js"></script>
  
  <!-- alert -->
  <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/mobile/js/jquery.modal.js"></script>

  <!-- Custom scripts for this template -->
  <script src="<%=request.getContextPath()%>/resources/mobile/js/new-age.min.js"></script>
  
  <script src="https://unpkg.com/ionicons@4.5.10-0/dist/ionicons.js"></script>
  
  <script>
  		function goReg(){
  			modal({
  	  			type: 'confirm',
  	  			title : '소통게시판 등록',
  	  			text : '해당 내용으로 게시판을 등록 하시겠습니까?',
  	  			callback : function(result){
  	  				if(result){
  			  	  		goRegist();		
  	  				}
  	  			}
  	  			
  	  		});	
  		}
  		
  		function goExit(){
  			modal({
  	  			type: 'confirm',
  	  			title : '정말 취소 하시겠습니까?',
  	  			text : '작성 중이시던 게시판은 저장되지 않습니다.',
  	  			callback : function(result){
  	  				if(result){
  			  	  		location.href="<%=request.getContextPath()%>/community/mobileList";						
  	  				}
  	  			}
  	  			
  	  		});	
  		}
  		
  		function goRegist(){
  			var community_title = $('#title').val();
  			var community_contents = $('#content').val();
  			var member_code = $('#member_code').val();
  			
  			if(community_title.length == 0 || community_contents.length == 0){
  				modal({
  	  	  			type: 'alert',
  	  	  			title : '해당 항목 확인',
  	  	  			text : '게시글 제목, 게시글 내용을 입력 하여 주세요.'
  	  	  		});
  				return false;
  			}
  			
  			$.ajax({
  				url : '<%=request.getContextPath()%>/community/mobileRegistAdd',
  				data : {
  					'community_title' : community_title,
  					'community_contents' : community_contents,
  					'member_code' : member_code
  				},
  				type : 'get',
  				dataType : 'text',
  				success : function(){
  	  				modal({
  	  	  	  			type: 'alert',
  	  	  	  			title : '소통게시판 등록',
  	  	  	  			text : '게시판이 정상적으로 등록 되었습니다.'
  	  	  	  		});
  	  				
	  	  			setTimeout(function(){
	  	  				location.href="<%=request.getContextPath()%>/community/mobileList";
	  	  			}, 1300);	
  	  			
  				},
  				error: function(){
  	  				modal({
  	  	  	  			type: 'alert',
  	  	  	  			title : '서버 오류 발생',
  	  	  	  			text : '서버오류로 인해 건으사항 등록이 취소 되었습니다.'
  	  	  	  		});
  				}
  				
  			});
  			
  		}
  </script>
	
</html>
