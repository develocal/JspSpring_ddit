<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<script>
	var message = "${member.member_code}:${member.member_name} 을\n";
	message+="수정했습니다.\n";
	/* message+="임직원 명단으로 이동합니다.\n 아무키나 누르세요."; */
	alert(message);
	window.opener.location.reload();
	<%-- window.opener.location.href="<%=request.getContextPath()%>/employee/list"; --%>
	location.href= "<%=request.getContextPath()%>/member/detailResident?member_code=${member.member_code}";
	
	</script>