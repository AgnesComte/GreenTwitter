function show_error_msg(message){
	var html='<div id="text-error">'+message+"</div>";
	if(html.length===0){
		$("#text-error").html(html);
		$("#text-error").css("background-color","#fbf0ee;");
		$("#text-error").scroll();
	}else{
		$("#text-error").replaceWith(html);
		$("#text-error").css("background-color","#fbf0ee;");
		$("#text-error").scroll();
	}
}
