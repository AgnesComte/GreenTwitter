function RechercheCommentaire(resultats, query, contacts_only, author, date) {
	this.resultats = resultats;
	this.author = author;
	this.query = (query == undefined || query === "") ? undefined : query;
	this.date = (date === undefined) ? new Date() : date;
	this.contacts_only = (contacts_only === undefined) ? 0 : contacts_only;
	environnement.recherche = this;
}

/* mise à jour de la page partie commentaire */ 
RechercheCommentaire.prototype.getHtml = function() {
	var s = "";
	if (this.query === undefined && environnement.actif === true) {
		s += '<h2>Let us knowzzzazaz!</h2>';
		s += '<div id="new_comment" class="form-group-new-comment">';
		s += '<form  method="get" action="javascript:(function(){return;})()" onsubmit="javascript:envoiCommentaire(comment)">';
		s += '<textarea name="comment" class="form-control" placeholder="Compose your tweet"></textarea>';
		s += '<button type="submit" class="btn btn-primary">Send</button>';
		s += '</form>';
		s += '</div>';
	}else if(environnement.actif === false){
		s+= '<h2>Connect you to tweet !</h2>'
	}else{
		s += '<span id ="recherche">' + this.query + '</span>';
	}
	s += '<h2 id="lastNews">Last news</h2>';
	s += '<div id ="list_comments">';

	jQuery.each(this.resultats, function(i, d) {
		s += d.getHtml();
	});
	s += "</div>";
	return s;
}


RechercheCommentaire.traiterReponseJSON = function(json) {
	// on serialise transformation du JSON en objets
	var rechercheCommentaire = RechercheCommentaire.revival(json);
	
	// autre méthode en String/Text -> parse(Stringlify, methode);

	// grace la serialisation on met en forme facilement les differents objets crées
	// grace aux méthodes getHtml
	if (rechercheCommentaire.error === undefined) {
		// retourne à l'ensemble de la boîte comments (bloc envoi et list commentaire)
		$("#comments").html(rechercheCommentaire.getHtml());
		$("#new_comment").show();
	} else {
		// show_error_msg-
		show_error_msg(rechercheCommentaire.erreur);
	}
}

// ici ajouter avec un traitement type text
// stringlify + parseJSON

// fonction avec un traitement type JSON
RechercheCommentaire.revival = function(element) {
	var commentArray = new Array();
	// No friends
	if (!element.result) {
		$("#lastNews").html("No friends");
		return new RechercheCommentaire(commentArray, "", element.friends,
				environnement.users[element.id_author], element.date);;
	}

	jQuery.each(element.result, function(i, d) {
		var commentUser = new User(d.author_id, d.author_username, d.contact);
		var comment = new Comment(d._id, commentUser, d.text, d.created_at,
				d.score);
		commentArray.push(comment);
	});

	return new RechercheCommentaire(commentArray, "", element.friends,
			environnement.users[element.id_author], element.date);
}

function rechercheGlobale(query) {
	var friends = $("#box-friends").is(':checked') ? "1" : "0";
	var query = query === undefined ? "" : query.value ;

	$.ajax({
		type : "GET",
		url : "comment/search",
		data : "key=" + environnement.key + "&query=" + query + "&friends="
				+ friends,
		// developpement different avec un type text pour les recherches
		// avantage ? le pourquoi du comment ?
		dataType : "json",
		success : RechercheCommentaire.traiterReponseJSON,
		error : function(jqXHR, textStatus, errorThrown) {
			show_error_msg(textStatus);
			console.log(jqXHR);
			console.log(errorThrown);
		}
	});
}
