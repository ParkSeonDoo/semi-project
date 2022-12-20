<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp/main</title>
<link rel="stylesheet" href="./css/index.css">
<link rel="stylesheet" href="./css/student_list.css">
<link rel='stylesheet' href='./css/student_input.css'>
<link rel='stylesheet' href='./css/student_modify.css'>
<link rel="stylesheet" href="./css/score_list.css">
<link rel="stylesheet" href="./css/score_modify.css">
</head>
<body>
<%
   String inc = "temp.jsp";
   if(request.getParameter("inc")!=null){
      inc = request.getParameter("inc");
   }
%>
   <div class="main">
	   <header class="index_header">
	      <div class="container">
	         <div class="logo">
	            <a href="student.do?main=1">
	               <img src="./image/logo.png">
	               <span>나무고등학교</span>
	            </a>
	         </div>
	         <div class="menu">
	            <ul>
	               <li><a href="student.do?url=student_list.jsp">학생관리</a></li>
	               <li><a href="score.do?url=score_list.jsp">성적관리</a></li>
	            </ul>
	         </div>
	            <div class="login_top">
	               <span id="black">${sessionId } 선생님 반갑습니다.</span>
	               <a href="login.jsp">로그아웃</a>
	            </div>
     		</div>
	    </header>
	    <section class="section1">
	    <jsp:include page= "<%=inc%>"/>
		</section>
   	</div>
</body>
</html>   