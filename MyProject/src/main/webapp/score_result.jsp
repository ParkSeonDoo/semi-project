<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>score_result.jsp</title>
</head>
<body>

<jsp:useBean id="scvo" class='score.ScoreVo'/>
<jsp:setProperty property="*" name="scvo"/>

<jsp:useBean id="pageVo" class="student.Page"/>
<jsp:setProperty property="*" name="pageVo"/>

<%
request.setAttribute("scvo", scvo);
request.setAttribute("pageVo", pageVo);
%>

<jsp:forward page="score.do?work=modify"/>


</body>
</html>