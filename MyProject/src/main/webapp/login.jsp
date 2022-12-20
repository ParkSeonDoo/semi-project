<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
<link rel='stylesheet' href='css/login.css'>
<script defer src='js/student.js'></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<script src="https://unpkg.com/youtube-background@1.0.14/jquery.youtube-background.min.js"></script>
<script>
  $(document).ready(function() {
    $('[data-vbg]').youtube_background();
  });
</script>
<div style="position: fixed !important; width: 100vw; height: 100%; left: 0; bottom: 0;">
<div data-vbg-autoplay="true" data-vbg="https://youtu.be/nDuCD1D92HY"></div>
</div>
<div class='login_frm'>
   <form name='login_frm' method='post' >
      <span class='logo'>
         <img style= "border-radius: 30px;" src='image/logo.png' height = 180px;/>
      </span>
      <div class='id'>
         <div>ID</div>
         <input type='text' name='id' size = '23' required autocomplete='off'
               pattern = '^[a-zA-Z]+[\w]+$'/>
      </div>
   	
      <div class = 'pwd'>
         <div>PASSWORD</div>
         <input type='password' name='pwd' size = '23' required onkeyup="enterkey()"/>
      </div>   
      <div>
         <input type='button' name='login_btn' value='로그인' class='login_btn'/>
         <input type='hidden' name='findStr' value="1"/>
       <input type='hidden' name='nowPage' value="1"/>
      </div>      
   </form>
</div>
</body>
</html>