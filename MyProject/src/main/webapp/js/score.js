/**
 * score.js
 */
var score_list_frm = document.scorelist_frm;
var score_modify_form = document.score_modify_form;

/*view*/
function view(serial){
   score_list_frm.action = 'score.do?work=view&serial=' + serial;
   score_list_frm.submit();
}

function view2(serial){
   score_modify_form.action = 'score.do?work=view&serial=' + serial;
   score_modify_form.test2.value=score_modify_form.test.value;
   score_modify_form.submit();
}

/* 입력*/ 
var Inputbtn = document.querySelector('#Inputbtn');
if(Inputbtn != null){
var score_input_frm = document.score_input_frm;
Inputbtn.onclick = function(){
   score_Input_form.action = 'score_do?work=insert';
   score_Input_form.submit();
   }
}

/*수정*/
var modifybtn = document.querySelector('#modifybtn');
if(modifybtn != null){
var score_modify_form = document.score_modify_form;
modifybtn.onclick = function(){
	let kor_score= score_modify_form.kor;
	let eng_score= score_modify_form.eng;
	let math_score= score_modify_form.math;
	let his_score= score_modify_form.his;
	let sci_score= score_modify_form.sci;
	
	if(!Number.isInteger(Number(kor_score.value)) ||   
		kor_score.value <0 || 
		kor_score.value >100){
			alert("국어 점수에 0~100 사이의 정수를 입력해주세요.");
			kor_score.value="";
			kor_score.focus();
			
		}else if (!Number.isInteger(Number(eng_score.value)) ||
		eng_score.value <0 ||
		eng_score.value >100){
			alert("영어 점수에 0~100 사이의 정수를 입력해주세요.");
			eng_score.value="";
			eng_score.focus();
		}else if(!Number.isInteger(Number(math_score.value)) ||
		math_score.value <0 ||
		math_score.value >100){
			alert("수학 점수에 0~100 사이의 정수를 입력해주세요.");
			math_score.value="";
			math_score.focus();
		}else if(!Number.isInteger(Number(his_score.value)) ||
		his_score.value <0 ||
		his_score.value >100){
			alert("사회 점수에 0~100 사이의 정수를 입력해주세요.");
			his_score.value="";
			his_score.focus();
		}else if(!Number.isInteger(Number(sci_score.value)) ||
		sci_score.value <0 ||
		sci_score.value >100){
			alert("과학 점수에 0~100 사이의 정수를 입력해주세요.");
			sci_score.value="";
			sci_score.focus();
   }else {
	score_modify_form.action = 'score_result.jsp';
   	score_modify_form.submit();
}
}
}


/*삭제*/
var deletebtn = document.querySelector('#deletebtn');
if(deletebtn != null){
var score_modify_form = document.score_modify_form;
   deletebtn.onclick=function(){
   score_modify_form.action = 'score_result2.jsp';
   score_modify_form.submit();
   }
}




let gradeST;
gradeST = sessionStorage.getItem('gradeST');
if(gradeST !=null){
	let scoretlist_frm=document.scorelist_frm;
	for(tag of scorelist_frm.grade){
		if(tag.value ==gradeST){
			scorelist_frm.grade.value= gradeST;
		}
	}
}

let banST;
banST = sessionStorage.getItem('banST');
if(banST !=null){
	let scoretlist_frm=document.scorelist_frm;
	for(tag of scorelist_frm.ban){
		if(tag.value ==banST){
			scorelist_frm.ban.value= banST;
		}
	}
}	
	
var gradeSelect = document.getElementById("grade");
	function changeGrade(){
		let scorelist_frm=document.scorelist_frm;
		gradeItem=scorelist_frm.grade.options[scorelist_frm.grade.selectedIndex].value;
		sessionStorage.setItem('gradeST', gradeItem );
		
		scorelist_frm.action= 'score.do?work=load';	
		scorelist_frm.nowPage.value=1;
		scorelist_frm.submit();
	}
	
var banSelect = document.getElementById("ban");
	function changeBan(){
		let scoretlist_frm=document.scorelist_frm;
		banItem=scorelist_frm.ban.options[scorelist_frm.ban.selectedIndex].value;
		sessionStorage.setItem('banST', banItem );
		
		scorelist_frm.action= 'score.do?work=load';	 	
		scorelist_frm.value=1;
		scorelist_frm.submit();	
}

function gettest() {
		let scorelist_frm=document.scorelist_frm;
 		scorelist_frm.action= 'score.do?work=load';	 	
		scorelist_frm.nowPage.value=1;
		scorelist_frm.submit();	
		
}
	
/*페이지이동 */
function movePage(nowPage){
      frmSearch.nowPage.value=nowPage;
      frmSearch.action = 'score.do?work=load';   
      frmSearch.submit();
}

var frmSearch = document.scorelist_frm;
if(frmSearch != null){
   frmSearch.loadbtn.onclick=function(){
   frmSearch.action= 'score.do?work=load';       
   frmSearch.nowPage.value=1;
   frmSearch.submit();      
   }
}