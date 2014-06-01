function traiteReponseAjoutSupContact(rep) {
	if ((rep.error !== undefined) && (rep.error !== 0)) {
		show_error_msg(rep.error);
	} else {
		rechercheGlobale();
	}
}

function ajoutsup_contact(id, isAdd) {

	var urlServlet = isAdd ? "friend/add" : "friend/remove";

	$.ajax({
		type : "GET",
		url : urlServlet,
		data : "key=" + environnement.key + "&idTo=" + id,
		dataType : "json",
		success : traiteReponseAjoutSupContact,
		error : function(jqXHR, textStatus, errorThrown) {
			alert(textStatus);
		}
	});
}