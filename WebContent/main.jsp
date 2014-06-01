<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="shortcut icon" href="images/icon.png">
    

    <title> C&J Twitter!</title>

    <!-- Bootstrap core CSS -->
    <link href="./css/library/bootstrap.min.css" rel="stylesheet">
	
	<!-- Bootstrap theme -->
    <link href="./css/library/bootstrap-theme.min.css" rel="stylesheet">
	<link href="./css/library/bootswatch.min.css" rel="stylesheet">
	<link href="./css/library/theme.css" rel="stylesheet">
   

    <!-- Custom styles for this template -->
    <link href="./css/main.css" rel="stylesheet">
	

	

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
  </head>

  <body onload="go();">

    <div class="site-wrapper">

      <div class="site-wrapper-inner">

		<%-- Include header --%>
		<%@ include file="header.html" %>
		
        <div class="main-container">

			<div class="center-container">
				
				<div class="row">
					<div class="stats">
					
					<!--<h1>Statistics</h1>-->
					
				
					</div>
					<div class="comments">
					<h1 id="hello_login">Hello!</h1>
				<!--<h2 id="letus">Let us know!</h2>-->
					<div id="text-error"></div>	
					<div id="comments">
						<div id="new_comment" class="form-group-new-comment">					
								<form method="get" action="javascript:(function(){return;})()" onsubmit="javascript:envoiCommentaire(comment)">
									<textarea class="form-control" placeholder="Compose your tweet" name="comment"></textarea>
									<!--<input type="textarea" rows="4" cols="50" placeholder="Compose your tweet" id="new_comment">-->
									<button type="submit" class="btn btn-primary">Send</button>
									<!--<input type="submit">-->
								</form>						
						</div>
						<h2>Last news</h2>
						<div id="list_comments" class="list-comments">
							<div class="tweet-container">
								<span class="author">Georges Brassens</span> 
								<span class="date">15h34</span>
								<span id="addFriends" class="img-span pull-right">
									<img src="./images/glyphicons_006_user_add.png"/>
								</span>
								<p class="text">This is my first tweet!</p>
							</div>
							<div class="tweet-container">
								<span class="author">Jacques Brel</span> 
								<span class="date">15h56</span>
								<p class="text">Hey guys!</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			
          <div class="mastfoot">
            <div class="inner">
              <p>&copy; Twitter by Comte & Jankovic, 2014</p>
            </div>
          </div>

        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="./js/main.js"></script>
	<script type="text/javascript" src="./js/comment.js"></script>
	<script type="text/javascript" src="./js/contacts.js"></script>
	<script type="text/javascript" src="./js/user.js"></script>
	<script type="text/javascript" src="./js/error.js"></script>
	<script type="text/javascript" src="./js/rechercheCommentaires.js"></script>
	<!--<script src="./js/jquery-1.11.0.min.js"></script>-->
    <script type="text/javascript">
		function go(){
		 	<% String id = request.getParameter("id");
			   String login = request.getParameter("login");
		       String key = request.getParameter("key");
			   
			if((id!=null)&&(login!=null)&&(key!=null)){
							out.println("main('"+id+"','"+login+"','"+key+"');");
				
			}else{
				out.println("main();");
			}
			%>
       
		};
	</script>
	
  </body>
</html>
