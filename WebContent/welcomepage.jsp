
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="images/icon.png">
    

    <title>Welcome to C&amp;J Twitter!</title>

    <!-- Bootstrap core CSS -->
    <link href="./css/library/bootstrap.min.css" rel="stylesheet">
	
	<!-- Bootstrap theme -->
    <link href="./css/library/bootstrap-theme.min.css" rel="stylesheet">
    <link href="./css/library/theme.css" rel="stylesheet">
    <link href="./css/library/bootswatch.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./css/welcomepage.css" rel="stylesheet">
	
	
	

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
  </head>

  <body onload = demarrage()  >
  
  <div class="site-wrapper">

      <div class="site-wrapper-inner">

		<%-- Include header --%>
		<%@ include file="header.html" %>

        <div class="cover-container">


          <div class="inner cover">
            <h1 class="cover-heading">Welcome to C&amp;J Twitter.</h1>
            <p class="lead">This is our project.</p>
            <p class="lead">
              <a href="./main.jsp" class="btn btn-lg btn-default">Get started.</a>
            </p>
          </div>

          <div class="mastfoot">
            <div class="inner">
              <p>&copy; Twitter by Comte &amp; Jankovic, 2014</p>
            </div>
          </div>

        </div>

      </div>

    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="./js/welcome.js"></script>
	<script type="text/javascript" src="./js/error.js"></script>
  </body>
</html>
