<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="shortcut icon" href="images/icon.png">

    <title>Log in - C&amp;J Twitter</title>

    <!-- Bootstrap core CSS -->
    <link href="./css/library/bootstrap.min.css" rel="stylesheet">
	<link href="./css/library/bootstrap-theme.css" rel="stylesheet">
	
    <!-- Custom styles for this template -->
    <link href="./css/sign.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body onload = signIn()>

    <div class="container">
	
		<%-- Include header --%>
		<%@ include file="header.html" %>			
	
		
		<!-- Login form -->
	<div id="text-error"></div>
      <form class="form-signin" method="get" action="javascript:(function(){return;})()" onsubmit="javascript:connexion(this)" >
        <h2 class="form-signin-heading">Please log in</h2>
        <input type="text" class="form-control" placeholder="Username" name="login" required autofocus>
        <input type="password" class="form-control" placeholder="Password" name="password" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		<button class="btn btn-lg btn-default btn-block" type="reset">Cancel</button>
		<a href="signup.jsp" class="signup">Not registered yet ! Sign up</a>
      </form>
	<!--<div class="mastfoot">
            <div class="inner">
              <p>&copy; Twitter by Comte & Jankovic, 2014</p>
            </div>
    </div>-->
    </div> <!-- /container -->
	

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	
    <script type="text/javascript" src="./js/welcome.js"></script>
    <script type="text/javascript" src="./js/rechercheCommentaires.js"></script>
	<script type="text/javascript" src="./js/error.js"></script>
	
	
  </body>
</html>
