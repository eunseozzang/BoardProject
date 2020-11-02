<%@page import="org.apache.tomcat.util.http.Cookies"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="${cp }/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${cp }/css/signin.css" rel="stylesheet">
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="${cp }/js/js.cookie-2.2.1.min.js"></script>
    
    <script type="text/javascript">
    
    function getCookieValue(cookieName) {
		var cookie = document.cookie.split("; ");
		var cookie2;
		var value = "";
			
		for(var i=0;i<cookie.length;i++) {
			cookie2 = cookie[i].split("=");
			
			if(cookie2[0]==cookieName) {
				value = cookie2[1];
			}
		}
		return value;
	}

   	function setCookie(cookieName, cookieValue, expires){

//    	"USERNM=brown; path=/; expires=Wed, 07 Oct 2020 00:38:35 GMT;";
//		이 문자열을 바꿔줘야 하는것 !

   		//1. 현재 날짜 구하기
   		var today = new Date();
   		//2. 현재 날짜 + 기간
   		today.setDate(today.getDate() + expires);
		document.cookie = cookieName + "=" + cookieValue + "; path=/; expires=" + today.toGMTString();
		console.log(document.cookie);
   	}

	//해당쿠키의 expires 속성을 과거날짜로 변경 (deleteCookie)
	function deleteCookie(cookieName){
		setCookie(cookieName,"",-1);
	}

	$(function(){
		if(getCookieValue("REMEMBERME") == "Y"){
			$('#remember').prop("checked", true);

			var user = getCookieValue("USERNM");

			$('#inputEmail').val(user);
		}

		$('button').on('click',function(){
			if($('#remember').prop("checked")==true){
				user = $('#inputEmail').val();
				setCookie("USERNM",user,3);
				setCookie("REMEMBERME","Y",3);
			} else {
				deleteCookie("USERNM");
				deleteCookie("REMEMBERME");
			}
			$('form').submit();
		})
	})
	
   	
    </script>
  </head>
  <body>
    <div class="container">
      <form class="form-signin" action="${cp }/login" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="text" name="userId" id="userId" class="form-control" placeholder="Id" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me" id="remember"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="button">Sign in</button>
      </form>

    </div> <!-- /container -->
  </body>
</html>
