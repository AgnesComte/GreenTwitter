function connexion(form) {
	var login = form.login.value;
	var password = form.password.value;
	connect(login, password);
}

function connect(login, password) {
	$.ajax({
		type : "GET",
		url : "user/login",
		data : "login=" + login + "&password=" + password,
		dataType : "json",
		success : traiteResponseConnection,
		error : function(jqXHR, textStatus, errorThrown) {
			show_error_msg(textStatus);
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});

}

function traiteResponseConnection(rep) {
	if (rep.error !== undefined) {
		show_error_msg(rep.error);
	} else {
		location.href = "main.jsp?id=" + rep.id + "&login=" + rep.login
				+ "&key=" + rep.key;
	}
}

function disconnect() {
	$.ajax({
		type : "GET",
		url : "user/logout",
		data : "key=" + environnement.key,
		dataType : "json",
		success : traiteReponseDisconnect,
		error : function(jqXHR, textStatus, errorThrown) {
			show_error_msg(textStatus);
		}
	});
	environnement.actif = false;
}

function traiteReponseDisconnect(rep) {
	if (rep.error !== undefined) {
		show_error_msg(rep.error);
		location.href = "signin.jsp";
		

	} else {
		location.href = "welcomepage.jsp";
	}
}

/**
 * This method hide or show the loginLi or logoutLi div
 */
function updateDivConnection() {
	var checkbox = '<input id="box-friends" name="box-friends" type="checkbox"/> Only Friends';

	if (typeof environnement == "undefined" || !environnement.actif
			|| environnement.actif === undefined
			|| environnement.actif === false) {
		$("#signUpLi").show();
		$("#loginLi").show();
		$("#logoutLi").hide();
//		$("#letus").hide();
//		$("#new_comment").hide();
	} else {
		$("#signUpLi").hide();
		$("#loginLi").hide();
		$("#logoutLi").show();
//		$("#letus").show();
//		$("#new_comment").show();

		if ($("#box-friends-label").length !== 0) {
			$("#box-friends-label").html(checkbox);
			$("#box-friends").on("click", function(){
				rechercheGlobale();
			})
		}
		if ($("#hello_login").length !== 0) {
			$("#hello_login").text(environnement.users[environnement.userId].login);
		}
	}
}
