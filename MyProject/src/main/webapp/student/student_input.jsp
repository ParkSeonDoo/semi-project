<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>student_input</title>
<script defer src="./js/student.js"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>   
<main>
	<div class='student_input'>
	<form name='student_input_frm' method='post'>
	<h2>학생정보 입력</h2>   
		<div class="table-outer" >
       	<table class='table_abc'>
        	<tr>
				<th>
               		<span class='serial'>학번</span>
              	</th>
              	<td>
               		<input type='text' name='serial' size="11" maxlength="10"/>
          		</td>
          	</tr>
          	<tr class='mid'>      
             	<th>                        
              		<span class='grade'>학년</span>
             	</th>
             	<td>
              		<input type="text" name='grade' size="5"  maxlength='2'/>
				</td>
				<th>
              		<span class='ban'>반</span>
              	</th>
              	<td>
              		<input type='text' name='ban' size="5"  maxlength='1'/>
				</td>
          	</tr>
          	<tr>
          		<th >이름</th>         
				<td>
					<input type='text' name='name' size="15"  maxlength='20'/>
				</td>         
             	<th>성별</th>
             	<td>
	              	<label><input type='radio' name='gender' value='m' />남자</label>
	              	<label><input type='radio' name='gender' value='f' />여자</label>
             	</td>
          	</tr>
          	<tr>                     
             	<th >연락처</th>
             	<td><input type ='text' size="18" name='phone' /></td>
             	<th>이메일</th>
             	<td><input type ='text' size="14"  name='email' /></td>   
          	<tr>
             	<th>주소</th>
               	<td colspan="2">            
	                <input type='text' name='address' size="30" />
	                <input type='text' name='address2' size="30" />
               </td>
          	</tr>
      	</table>
		<div class="studentinput_btnbox">
         	<input type='reset' value='취소' class="listbtn">
            <input type='button' value='저장' name="save_btn">
         </div>
      </div>
         <input type='hidden' name='findStr' value="${pageVo.findStr}"/>
		 <input type='hidden' name='nowPage' value="${pageVo.nowPage}"/>
   </form>
   </div>
<script type="text/javascript">
	$(".listbtn").on("click",function(){
		location.href = "student.do?url=student_list.jsp";
	});
</script>
</main>
</body>
</html>