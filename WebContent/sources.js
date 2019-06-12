$(document).ready(function() {
	
	$(".btnSelect").on("click", function(){
		
//		ID de la société sélectionnée
		let id = $(this).attr("data-idsociete")
		
		$.ajax({
			url : "http://localhost:8080/JerseySociete/rest/societes/" + id,
			type : "get",
			dataType : "json",
			success : function(res, state){
				alert(JSON.stringify(res))
			},
			error : function(request){
				alert("Erreur " + request.status)
			}
		})
	})
	
	$("#btnAjouterSociete").on("click", function(){
		$.ajax({
			url : "http://localhost:8080/JerseySociete/rest/societes/",
			method : "post",
			dataType : "json",
			success : function(res, state){
				alert(res)
			},
			error : function(request){
				alert("Erreur " + request.status)
			}
		})
	})
	
	$(".btnUpdate").on("click", function(){
		$.ajax({
			url : "http://localhost:8080/JerseySociete/rest/societes/",
			type : "put",
			success : function(res, state){
				alert(res)
			},
			error : function(request){
				alert("Erreur " + request.status)
			}
		})
	})
	
	$(".btnDelete").on("click", function(){
		$.ajax({
			url : "http://localhost:8080/JerseySociete/rest/societes/",
			method : "delete",
			success : function(res, state){
				alert(res)
			},
			error : function(request){
				alert("Erreur " + request.status)
			}
		})
	})

})