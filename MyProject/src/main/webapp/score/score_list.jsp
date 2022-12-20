<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>score/score_list.jsp</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script defer src='js/score.js'></script>
</head>
<body>
	<div class="scorelist_main">
		<h2>학생성적 리스트</h2>
		<form class="scorelist_frm" action="" name="scorelist_frm" method="post" >
			<div class="scorelist_quickmenu">
				<label>중간<input type="radio" name='test' checked value="a" ${(radiocheck eq 'a')? 'checked':''} onclick="gettest(this.value)"></label>
				<label>기말<input type="radio" name='test' value="b" ${(radiocheck eq 'b')? 'checked':''} onclick="gettest(this.value)"></label>
				<div class="scorelist_st">
					<span>학년</span>
					<select name='grade' onchange="changeGrade()">
						<option value="" selected disable></option>
						<option value='1'>1</option>
						<option value='2'>2</option>
						<option value='3'>3</option>
					</select>
				</div>
				<div class="scorelist_ban">
					<span>반</span>
					<select name='ban' onchange="changeBan()">
						<option value="" selected disable></option>
						<option value='1'>1</option>
						<option value='2'>2</option>
						<option value='3'>3</option>
						<option value='4'>4</option>
						<option value='5'>5</option>
					</select>
				</div>
				<div class="scorelist_se">
				<input type='search' name='findStr' value="${pageVo.findStr }"/>
				<input type= 'button' value='조회' name='loadbtn'/>
				<input type='hidden' name='nowPage' value="${pageVo.nowPage}"/>
				</div>
			</div>
		<table class="scorelist_table">
			<tr>
				<th>학년</th>
				<th>학번</th>
				<th>반</th>
				<th>이름</th>
				<th>국어</th>
				<th>영어</th>
				<th>수학</th>
				<th>사회</th>
				<th>과학</th>
				<th>평균</th>
				<th>등수</th>
			</tr>
			<c:forEach var ='v' items="${list}" varStatus='i'>
			<tr class="scorelist_modify" onclick="view('${v.serial}')">
				<td>${v.grade }</td>
				<td>${v.serial }</td>
				<td>${v.ban }</td>
				<td>${v.name }</td>
				<td><fmt:formatNumber type="number" value="${v.kor }"/></td>
				<td><fmt:formatNumber type="number" value="${v.eng }"/></td>
				<td><fmt:formatNumber type="number" value="${v.math }"/></td>
				<td><fmt:formatNumber type="number" value="${v.kor }"/></td>
				<td><fmt:formatNumber type="number" value="${v.his }"/></td>
				<td>${v.avg }</td>
				<td>${(pageVo.nowPage-1)*pageVo.listSize +i.count}</td>
			</tr>
			<input type="hidden" name='test2' value="${v.test }"/>
			</c:forEach>					
		</table>
		</form>
		<div class="scorelist_ch">
			<c:if test = '${pageVo.startPage>1 }'>
		<input type='button' name='page' value='처음' onclick='movePage(1)'/>
		<input type='button' name='page' value='이전' onclick='movePage(${pageVo.startPage-1})'/>
		</c:if>
	
		<c:forEach var='i' begin='${pageVo.startPage }' end='${pageVo.endPage}'>
		<input type='button' name='page' value='${i }' onclick='movePage(${i })'/>
		</c:forEach>
	
		<c:if test = "${pageVo.totPage > pageVo.endPage }">
		<input type='button' name='page' value='다음' onclick='movePage(${pageVo.endPage+1})'></input>
		<input type='button' name='page' value='맨끝' onclick='movePage(${pageVo.totPage})'></input>
   		</c:if>
		</div>
	</div>
</body>
</html>