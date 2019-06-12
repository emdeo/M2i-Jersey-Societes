$(document).ready(function() {
	
	var lstSocietes = "?"
	GenererTableauSociete(lstSocietes)
	
	$(".btnSelect").on("click", function(){
		$.ajax({
			url : "rest/societes",
			type : "get",
			dataType : "text",
			success : function(res, state){
				alert(res)
			},
			error : function(request){
				alert("Erreur " + request.status)
			}
		})
	})
	
	$("#btnAjouterSociete").on("click", function(){
		$.ajax({
			url : "rest/societes",
			method : "post",
			dataType : "application/json",
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
			url : "rest/societes",
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
			url : "rest/societes",
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