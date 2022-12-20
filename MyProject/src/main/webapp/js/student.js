/**
 * 저장
 */

var frm_login = document.login_frm;
if(frm_login !=null){
   frm_login.login_btn.onclick=function(){
      frm_login.action=
         'student.do?work=login';
         frm_login.submit();
   }
}

var student_input_frm =document.student_input_frm;
      if(student_input_frm !=null){
      student_input_frm.save_btn.onclick=function(){
		student_input_frm.action=
        'result.jsp?work=saveR';
        student_input_frm.submit();
      }
       
}
	function movePage(nowPage){
		frmSearch.nowPage.value=nowPage;
		frmSearch.action = 'student.do?work=load';	
		frmSearch.submit();
	}
var frmSearch = document.studentlist_frm;
if(frmSearch !=null){
	frmSearch.insertbtn.addEventListener('click',function(){
		frmSearch.action= 'student.do?work=save';
		frmSearch.submit();
	});
}

function view(serial){
	frmSearch.action = 'student.do?work=view&serial=' + serial;
		frmSearch.submit();
}

var modifybtn = document.querySelector('#modifybtn')
if(modifybtn !=null){
	var frm =document.student_modify_frm;
	modifybtn.onclick=function(){
		frm.action = 'result.jsp?work=modify';
		frm.submit();
	}
}
var deletebtn = document.querySelector('#deletebtn');
if(deletebtn !=null){
	var frm = document.student_modify_frm;
	deletebtn.onclick=function(){
		frm.action = 'result.jsp?work=delete';
		frm.submit();
	}
}
var frmSearch = document.studentlist_frm;
if(frmSearch !=null){
	frmSearch.loadbtn.addEventListener('click',function(){
		frmSearch.action= 'student.do?work=save';
		frmSearch.submit();
		});	
	frmSearch.loadbtn.onclick=function(){
	frmSearch.action= 'student.do?work=load';	 	
	frmSearch.nowPage.value=1;
	frmSearch.submit();		
	}
	
	}
	
var gradeSelect = documet.getElementById("grade");
   function changeGrade(){
	alert(value);
   var selectValue = gradeSelect.options[grade.selectedIndex].value;
      
      
   }
function enterkey_search() { 
   if (frmSearch!=null && window.event.keyCode == 13) { 
    frmSearch.action=
        frmSearch.action= 'student.do?work=load';       
      frmSearch.nowPage.value=1;
      frmSearch.submit();
    } 
}	
function enterkey() { 
   if (frm_login !=null && window.event.keyCode == 13) { 
    frm_login.action=
         'student.do?work=login';
         frm_login.submit();
    } 
}	
	
	
	
