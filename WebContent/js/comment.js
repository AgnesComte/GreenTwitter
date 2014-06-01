function Comment(id, author, text, date, score){
    this.id = id ;
    this.author = author ; //objet User
    this.text = text ;
    this.date = (date == undefined) ? new Date() : date ;
    this.score = (score=== undefined) ? 0 : score ;
    
}

Comment.prototype.getHtml = function(){
 
    var s ='<div class="tweet-container">';
	s+='<span class="author">'+this.author.login+'</span>';
	s+='<span class="date">'+this.date+'</span>';
	s+='<span id="addFriends" class="img-span pull-right"></span>';
		
	// Verify tweet author different from request author
  //  if((environnement.actif !== false) && (environnement.userId !== this.author.id)) {
	 if((environnement.actif !== false) && (environnement.users[environnement.userId].id != this.author.id)) {
        if(!this.author.contact) {	
		    s+='<a href="#" class="imageContact" onClick="javascript:ajoutsup_contact('+this.author.id+', true)"><img src="./images/glyphicons_006_user_add.png"/></a>';
        } else {
         s+='<a href="#" class="imageContact" onClick="javascript:ajoutsup_contact('+this.author.id+', false)"><img src="./images/glyphicons_007_user_remove.png"/></a>';
        }
    }
    s+='<p class="text">'+this.text+'</p>';
    s+= "</div>";
    return s;
}

function envoiCommentaire(comment) {
	
	var text = comment.value
	
	$.ajax({
		type : "GET",
		url : "comment/add",
		data : "key=" + environnement.key + "&text="+ text,
		dataType : "json",
		success : traiteReponseEnvoiCommentaire,
		error : function(jqXHR, textStatus, errorThrown) {
			show_error_msg(textStatus);
			console.log(jqXHR);
			console.log(errorThrown);
		}
	});
}

function traiteReponseEnvoiCommentaire(rep){
	if (rep.error !== undefined) {
		show_error_msg(rep.error);
	} else {
		rechercheGlobale();
	}
	
}