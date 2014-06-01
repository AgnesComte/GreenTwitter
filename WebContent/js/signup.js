function createuser(form){
	var lastname = form.lastname.value;
	var firstname = form.firstname.value;
	var login = form.login.value;
	var password = form.password.value;
	var passwordcheck = form.passwordcheck.value;
	
	var ok = verif_formulaire_createUser(lastname, firstname,login, password, passwordcheck);
    if (ok) {
            creation(lastname, firstname, login, password);
    }
}

function verif_formulaire_createUser(lastname, firstname, login, password, passwordcheck){
	
	if(password.length < 6){
		show_error_msg("6 caracteres are required for the password");
		return false;
	}
	if(password !== passwordcheck){
		show_error_msg("Password confirmed is not the same");
		return false;
	}
	return true;
}

function creation(lastname,firstname,login,password){

	$.ajax({
		type:"GET",
		url:"user/createUser",
		data:"lastname="+lastname+"&firstname="+firstname+"&login="+login+"&password="+password,
		dataType:"json",
		success:function(){
			location.href="signin.jsp";
		},
		error: function(jqXHR, textStatus, errorThrown) {
			show_error_msg(textStatus);
			alert("erreur de config" + textStatus);
		}
	});
}


