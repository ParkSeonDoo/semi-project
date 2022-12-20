<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="student.StudentVo"%>
<%@page import="java.util.List"%>
<%@page import="student.StudentDao"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>student/student_list.jsp</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script defer src='js/student.js'></script>
</head>
<body>
	<div class="studentlist_main">
		<h2>학생정보 리스트</h2>
		<form class="studentlist_frm" action="" name="studentlist_frm" method="post">
			<div class="studentlist_search">
				<div class="studentlist_st">
					<span>학년</span>
					<select name="search_grade" >
							<option value="" ${(grade eq '')? 'selected':''}>선택</option>
							<option value="1" ${(grade eq '1')? 'selected':''}>1</option>
							<option value="2" ${(grade eq '2')? 'selected':''}>2</option>
							<option value="3" ${(grade eq '3')? 'selected':''}>3</option>
					</select>
				</div>
				<div class="studentlist_ban">
					<span>반</span>
					<select name="search_ban">
							<option value="" ${(ban eq '')? 'selected':''}>선택</option>
							<option value="1" ${(ban eq '1')? 'selected':''}>1</option>
							<option value="2" ${(ban eq '2')? 'selected':''}>2</option>
							<option value="3" ${(ban eq '3')? 'selected':''}>3</option>
							<option value="4" ${(ban eq '4')? 'selected':''}>4</option>
							<option value="5" ${(ban eq '5')? 'selected':''}>5</option>
					</select>
				</div>
				<div class="studentlist_se">
					<input type='text' name='findStr' value="${pageVo.findStr }" onkeyup="enterkey_search()"/>
					<input type='button' value='조회' id='loadbtn'/>
					<input type='button' value='입력' name='insertbtn'/>
					<input type='hidden' name='nowPage' value="${pageVo.nowPage}"/>
					<input type='hidden' name='search_status' id="search_status" value="search"/>					
				</div>
			</div>		
		<table class="studentlist_table">
			<tr>
				<th>No</th>
				<th>학년</th>
				<th>반</th>
				<th>학번</th>
				<th>이름</th>
				<th>성별</th>
				<th>연락처</th>
				<th>주소</th>
				<th>이메일</th>
			</tr>
		  <c:forEach var ='v' items="${list}" varStatus='status'>
		  <tr class="studentlist_modify"  onclick="view('${v.serial}')">
            <td>${status.count }</td>
            <td>${v.grade }</td>
            <td>${v.ban }</td>
            <td>${v.serial }</td>
            <td>${v.name }</td>
            <td>${v.gender }</td>
            <td>${v.phone }</td>
            <td>${v.address }</td>
            <td>${v.email}</td>
         </tr>			
		</c:forEach>
		</table>
		<div class="studentlist_ch">
			<c:if test="${pageVo.startPage>1 }">
			<input type='button' value='처음' onclick='movePage(1)'/>
			<input type='button' value='이전' onclick='movePage(${pageVo.startPage-1})'/>
			</c:if>
			<c:forEach var='i' begin='${pageVo.startPage }' end='${pageVo.endPage }'>
			<input type='button' value='${i }' onclick='movePage(${i})'/>
			</c:forEach>
			<c:if test="${pageVo.totPage > pageVo.endPage }">
			<input type='button' value='다음' onclick='movePage(${pageVo.endPage+1})'/>
			<input type='button' value='맨끝' onclick='movePage(${pageVo.totPage})'/>	
			</c:if>
		</div>
		</form>	
	</div>
</body>
</html>