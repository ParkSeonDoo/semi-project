<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>student_modify</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script defer src='js/student.js'></script>
</head>
<body>
<main>
	<form name='student_modify_frm' method='post'>
		<h2>학생정보 수정 </h2>	
		<div class="table-outer" >
			<table class='table_abc'>	
				<tr>
					<th>
						<span class='serial'>학번</span>
					</th>
					<td>
						<input type='text' name='serial' size=10  value='${vo.serial}' maxlength="10" readOnly style="border:none"/>
					</td>
				</tr>
				<tr>
					<th>
						<span class='grade'>학년</span>
					</th>
					<td>			
						<input type="text" name='grade' size=10  value='${vo.grade}' maxlength='2'style="border:none"/>
					</td>
					<th>
						<span class='ban'>반</span>
					</th>
					<td>
						<input type='text' name='ban' size=10  value='${vo.ban}' maxlength='1'style="border:none"/>
					</td>
				<tr>	
				<tr>					
				</tr>
				<tr>
					<th >이름</th>			
					<td><input type='text' name='name' size=10   value='${vo.name}' maxlength='20'style="border:none" /></td>			
					<th >성별</th>
					<td>
						<label><input type='radio' name='gender' value='m' ${(vo.gender eq 'm')? 'checked':''} />남자</label>
						<label><input type='radio' name='gender' value='f' ${(vo.gender eq 'f')? 'checked':''} />여자	</label>
					</td>
				</tr>
				<tr>							
					<th >연락처</th>
					<td><input type ='text'size="18" name='phone'  value='${vo.phone}' style="border:none"/></td>
					<th>이메일</th>
					<td><input type ='text'size="28"  name='email'  value ='${vo.email}' style="border:none"/></td>	
				<tr>
					<th>주소</th>
					<td colspan='2'>				
					<input type='text' name='address' size="28" value='${vo.address}' style="border:none"/>
					<input type='text' name='address2'size="28"  value='${vo.address2}' style="border:none"/>
					</td>
				</tr>
			</table>
			<div class="studentinput_btnbox">
				<input type='button' id='modifybtn' value='수정'>
				<input type='button' id='deletebtn' value='삭제'>
				<input type='button' name='listbtn' value= '목록' class="listbtn">		
			</div>
		</div>
		<input type='hidden' name='findStr' value="${pageVo.findStr }"/>
		<input type='hidden' name='nowPage' value="${pageVo.nowPage}"/>
	</form>
</main>
<script type="text/javascript">
	$(".listbtn").on("click",function(){
		location.href = "student.do?url=student_list.jsp";
	});
</script>
</body>
</html>