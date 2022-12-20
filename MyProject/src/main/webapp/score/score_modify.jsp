<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Score_Input.jsp</title>
<script defer src='js/score.js'></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<div class="scorelist_main">
   <h2>학생 성적 수정</h2>
   <form method="post" name='score_modify_form'>
      <div class="scoreinput_table">
         <div class="scoreinput_topset">
            <table class="scoreinput_top">
               <tr>
                  <th>학년</th>
                  <td><input type="text" name="grade" id="grade" value="${scvo.grade }" readonly/></td>
                  <th>반</th>
                  <td><input type="text" name="ban" id="ban" value="${scvo.ban }" readonly/></td>
               </tr>
               <tr>
                  <th>학번</th>
                  <td><input type="text" name="serial" id="gradenum" value="${scvo.serial }" readonly/></td>
                  <th>이름</th>
                  <td><input type="text" name="name" id="stdname" value="${scvo.name }" readonly/></td>
               </tr>
            </table>
         </div>
         <div class="typebtn"> 
             <label><input type='radio' name='test' value='a' ${(scvo.test eq 'a')? 'checked':''} onclick="view2(${scvo.serial } )" />중간</label>
         	<label><input type='radio' name='test' value='b' ${(scvo.test eq 'b')? 'checked':''} onclick="view2(${scvo.serial } )" />기말</label>
         	<input type="hidden" value="" name="test2"/>
         </div>
         <div class="scoreinput_bottomset">
            <div class="scoreinput_title">
               <div>국어</div>
               <div>영어</div>
               <div>수학</div>
               <div>사회</div>
               <div>과학</div>
            </div>
            <div class="scoreinput_text">
               <div><input type="text" name="kor" id="kor" value="${scvo.kor }"/></div>
               <div><input type="text" name="eng" id="eng" value="${scvo.eng }"/></div>
               <div><input type="text" name="math" id="mat" value="${scvo.math }"/></div>
               <div><input type="text" name="his" id="his" value="${scvo.his }"/></div>
               <div><input type="text" name="sci" id="sci" value="${scvo.sci }"/></div>
            </div>
         </div>
         <div class="scoreinput_btn">
            <input type="button" value="수정" id='modifybtn'/>
            <input type="button" value="삭제" id='deletebtn'/>
            <input type='reset' value="목록" class="listbtn"/>
         </div>
      </div>
      	<input type='hidden' name='findStr' value="${pageVo.findStr}"/>
		<input type='hidden' name='nowPage' value="${pageVo.nowPage}"/>
   </form>
</div>   
   <script type="text/javascript">
	$(".listbtn").on("click",function(){
		location.href = "score.do?url=score_list.jsp";
	});
	</script>
</body>
</html>