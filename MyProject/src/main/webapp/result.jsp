<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result.jsp</title>
</head>
<body>
<jsp:useBean id="pageVo" class="student.Page"/>
<jsp:setProperty property="*" name="pageVo"/>

<jsp:useBean id="sVo" class='student.StudentVo'/>
<jsp:setProperty property="*" name="sVo"/>



<%
   request.setAttribute("sVo", sVo);
   request.setAttribute("pageVo", pageVo);

%>

<jsp:forward page="student.do"/>

</body>
</html>