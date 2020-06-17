<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>Table V01</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/board/vendor/bootstrap/css/bootstrap.min.css">

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/board/fonts/font-awesome-4.7.0/css/font-awesome.min.css">

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/board/vendor/animate/animate.css">

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/board/vendor/select2/select2.min.css">

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/board/vendor/perfect-scrollbar/perfect-scrollbar.css">

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/board/css/util.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/board/css/contract.css">

</head>
<body>

<section class="content">
		<div class="limiter">
			<div class="container-table100">
				<div class="form-inline form-group " style="width: 90%;">
					<select class="form-control btn btn-danger" id="searchType"
						name="searchType" style="width: 150px;" class="">
						<option value="tcw"
							${pageMaker.cri.searchType eq 'tcw' ? 'selected':'' }>전체</option>
						<option value="t"
							${pageMaker.cri.searchType eq 't' ? 'selected':'' }>유형</option>
						<option value="c"
							${pageMaker.cri.searchType eq 'c' ? 'selected':'' }>기간</option>
						<option value="w"
							${pageMaker.cri.searchType eq 'w' ? 'selected':'' }>구분</option>
						<option value="tc"
							${pageMaker.cri.searchType eq 'tc' ? 'selected':'' }>유형+구분</option>
					</select> &nbsp;&nbsp;
					<div style="border: 1px solid black; width: 40%;">
						<input type="text" class="form-control" name="keyword"
							placeholder="검색어를 입력해 주세요" />
					</div>
					&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn btn-danger" id="searchBtn"
						onclick="searchList_go(1);" data-card-widget="search"
						style="width: 100px;">
						<i class="fa fa-fw fa-search"></i>
					</button>
					&nbsp;&nbsp;&nbsp;
					<div class="wrap-table100" style="padding-top: 15px;">
						<div class="table100">
							<table>
								<tr class="table100-head">
									<th class="column1">No</th>
									<th class="column2">계약자 코드</th>
									<th class="column3">계약 유형</th>
									<th class="column4">계약 금액</th>
									<th class="column5">계약 기간</th>
									<th class="column6">계약 시작 일자</th>
									<th class="column7">계약 만기 일자</th>
									<th class="column8">계약 구분</th>
								</tr>
								<c:if test="${empty constractList }">
									<tr>
										<td colspan="8"><STRONG>해당 내용이 없습니다.</STRONG></td>
									</tr>
								</c:if>
								<c:forEach items="${constractList }" var="constract">								
									<tr style="font-size: 0.85em;">
										<td style="text-align: center; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
										<a href="javascript:OpenWindow('contractDetail${pageMaker.makeQuery(pageMaker.cri.page) }&constract_code=${constract.constract_code}','상세보기',600,700);">
											<span class="col-sm-12">${constract.constract_code }										
											</span>
										
										</a>
										</td>
										<td>${constract.member_code}</td>
										<td>${constract.constract_kind}</td>
										<td>${constract.constract_amount}</td>
										<td>${constract.constract_term}</td>
										<td><fmt:formatDate
												value="${constract.constract_start_date }"
												pattern="yyyy-MM-dd" /></td>
										<td><fmt:formatDate
												value="${constract.constract_last_date }"
												pattern="yyyy-MM-dd" /></td>
										<td>${constract.constract_type}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<div>
							 <nav aria-label="member list Navigation">
								<ul class="pagination justify-content-center m-0">
									<li class="page-item">
										<a class="page-link" href="javascript:searchList_go(1);">
										<ion-icon name="arrow-undo-outline"></ion-icon>										
										</a>
									</li>
									<li class="page-item">
										<a class="page-link" href="javascript:searchList_go(
										${pageMaker.prev ? pageMaker.startPage-1 : 1});">
										<ion-icon name="caret-back-outline"></ion-icon>
										</a>
									</li>
									<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">						
									<li class="page-item 
										<c:out value="${pageMaker.cri.page == pageNum?'active':''}"/>">
										<a class="page-link" href="javascript:searchList_go(${pageNum});" >${pageNum }
										</a>
									</li>
									</c:forEach>
									<li class="page-item">
										<a class="page-link" href="javascript:searchList_go(
										${pageMaker.next ? pageMaker.endPage+1 : pageMaker.cri.page});">
										<ion-icon name="caret-forward-outline"></ion-icon>
										</a>
									</li>
									<li class="page-item">
										<a class="page-link" href="javascript:searchList_go(
											${pageMaker.realEndPage} );">
											<ion-icon name="arrow-redo-outline"></ion-icon>
											</a>
									</li>	
								</ul>
							</nav>   	
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<form id="jobForm">
		<input type="hidden" name="page" value="${pageMaker.cri.page }" />
		<input type="hidden" name="perPageNum" value="${pageMaker.cri.perPageNum }"/>
		<input type="hidden" name="searchType" value="${pageMaker.cri.searchType }"/>
		<input type="hidden" name="keyword" value="${pageMaker.cri.keyword }"/>
	</form>	

<script>

	
function searchList_go(page){
	
	var jobForm=$('#jobForm');
	jobForm.find("[name='page']").val(page);
	jobForm.find("[name='searchType']").val($('select[name="searchType"]').val());
	jobForm.find("[name='keyword']").val($('input[name="keyword"]').val());
	jobForm.attr("action","<%=request.getContextPath()%>/contract_payment/contract_management/owner/test").attr("method","post");
	jobForm.submit();
}	
</script>

	<!-- bootstrap icons -->
	<script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script>

		<!--===============================================================================================-->	
	<script src="<%=request.getContextPath()%>/resources/board/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="<%=request.getContextPath()%>/resources/board/vendor/bootstrap/js/popper.js"></script>
	<script src="<%=request.getContextPath()%>/resources/board/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="<%=request.getContextPath()%>/resources/board/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="<%=request.getContextPath()%>/resources/board/js/main.js"></script>

</body>
</html>