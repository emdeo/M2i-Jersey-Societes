function getSocietes()
{
	
	$.ajax(
			{
				  url:"http://10.115.2.176:8080/JAX_RS_TUTORIEL_JERSEY/rest/societes",
				  type:"get",
				  dataType:'json',
				  success:function(resultat,status)
				  {
					 //alert(JSON.stringify(resultat))		  
					  AfficheListeDesSocietes(resultat) 
				  }
			});	
}



$(document).ready(function()
{
	$("#tblEmployes").hide();
	
	
	    getSocietes();
	
	
	
	$("#tblSocietes").on("click",".select",function() {		
		$.ajax(
				{
				  url:"http://10.115.2.176:8080/JAX_RS_TUTORIEL_JERSEY/rest/societes/" + $(this).attr("data-idSociete") + "/employes",
				  type:"GET",
				  ContentType:'json',				
				  success:function(resultat,status) {
				//	alert(status)				  
				  AfficheListeDesEmployes(resultat)	  
							 
				  }
				}			
			);
	});
	
	
    $("#tblSocietes").on("click",".delete",function() {		
		let idsociete = $(this).attr("data-idSociete");		
		$.ajax(
				{
				  url:"http://10.115.2.176:8080/JAX_RS_TUTORIEL_JERSEY/rest/societes/"+idsociete,
				  type:"DELETE",
				  dataType:'json',
				  success:function(resultat,status) 
				  {
					  getSocietes();
				  }
				}			
			);
	});
	
	
	
	$("#cmdAjout").on("click",function() {
				
		let societe = new Object()
		societe._ID_Societe = $("#txtIDSociete").val() ;
		societe._Nom = $("#txtNom").val() ;
		societe._Activite = $("#txtActivite").val() ;
		societe._CA = $("#txtCA").val() ;
		societe.employees = [];
		
		//alert(JSON.stringify(societe));
		
		$.ajax(
			{
				  url:"http://10.115.2.176:8080/JAX_RS_TUTORIEL_JERSEY/rest/societes",
				  type:"POST",
				  contentType: 'application/json; charset=UTF-8',
				  dataType:'json',
				  data:JSON.stringify(societe),				
				  success:function(resultat,status)
				  {
					  //alert("Nombre de sociétés ajoutées : " + resultat)
					  getSocietes();
				  }				
			}			
		)		
	})
	
	
	
	$("#tblSocietes").on("click",".update",function() {
		
		let idSociete =  $(this).attr("data-idSociete");
		$("#cmdNouvellePersonne").attr("data-idSociete",idSociete);
				
		$.ajax(
				{
				  url:"http://10.115.2.176/JAX_RS_TUTORIEL_JERSEY/rest/societes/"+idSociete,
				  type:'Get',				 
				  contentType:"application/json",
				  success:function(resultats,status)
					  {
//					 	  $("#tblSocietesUpdateBody").html(resultats);
					      //alert("success : resultats = " + JSON.stringify(resultats))
					 	  AfficheSociete(resultats)
					 	   
					 	  // Récupération des Employés de la société
					 	  $.ajax(
					 		{
					 			 url:"http://10.115.2.176:8080/JAX_RS_TUTORIEL_JERSEY/rest/societes/"+idSociete+"/employes",
								  type:'Get',								 
								  contentType:"application/json",
								  success:function(tblRes,status)
								  {
									 // alert("Recuperation des employés")
									  AffichePersonnePourUpdate(tblRes)
//									  $("#tblPersonnesBodyUpdate").html(tblRes);
//									  $("#UpdateSocieteModal").modal('show');
								  }
					 		}	  
					 	  
					 	  )
					 	  	 	
					  }
				
				}
		
		);
	});
	

	
	
	

	
	
	
	
	
	 
  	$("#tblPersonnesUpdate tbody").on("click",".modifieUpdate",function(event) {
    
	  event.preventDefault();
	   let zones = $(this).parent().siblings().children();
	   
	  if ($(this).html() == "Modifier")
	  {
	  
			  for(i=1;i<6;i++)
			  {
				  zones[i].removeAttribute("readonly");
			  }
	  
			  $(this).html("Enregistrer");
	  
	  }
	  
	  else
	  {
		 if ($(this).html() == "Enregistrer")
		 {
			//Sauvegarde des données 
			 let idPers = $(this).attr("data-idPersonne");
			 let personne = {}
			 personne._ID_Personne = idPers; 
			 personne._Nom =  $("#txtNomPersonne"+idPers).val();
			 personne._Prenom =  $("#txtPrenomPersonne"+idPers).val();
			 personne._Poids =  $("#txtPoidsPersonne"+idPers).val();
			 personne._Taille =  $("#txtTaillePersonne"+idPers).val();
			 personne._Sexe =  $("#txtSexePersonne"+idPers).val();
			 personne._ID_Societe =  $("#txtIdSocietePersonne"+idPers).val();
			 
		     //alert(JSON.stringify(personne))
			
			 $.ajax(
				{
				   url:"http://10.115.2.176:8080/JAX_RS_TUTORIEL_JERSEY/rest/personnes",
				   type:'Put',
				   data:JSON.stringify(personne),
				   contentType:"application/json",
				   success:function(retour,status)
				   {
					  // alert(retour)  
				   },
				   error:function(erreur)
				   {
					   alert(erreur)   
				   }
					
				}			 
			 );
			 
			 
			 
         for(i=1;i<6;i++)
			  {
				  zones[i].setAttribute("readonly","true");
			  }
			  
			  $(this).html("Modifier");
		 }			 
	  }
	  
  });
  
	$("#tblPersonnesUpdate tbody").on("click",".supprimeUpdate",function(event) 
			{
				event.preventDefault();
				
				// Recuperation de l'idPersonne 
				let idPersonne = $(this).attr("data-idPersonne");
				//alert(idPersonne)
				
				$.ajax(
					{
						url:"http://10.115.2.176:8080/JAX_RS_TUTORIEL_JERSEY/rest/personnes/"+idPersonne,
						type:'delete',
						success:function(res,status)
						{
							//alert("nb enregistrements supprimés = " + res)
						}
					}				
				)
				
				// Suppression de la ligne visuellement
				$(this).parent().parent().empty();
				
				
				
		    }); 
	
	
// Ajout d'un nouvel employé
	$("#cmdNouvellePersonne").on("click" , function(event) {
		  // Récupération du Max ID_Personne 
		event.preventDefault();
		   $.ajax(
				{
				  url:"http://10.115.2.176:8080/JAX_RS_TUTORIEL_JERSEY/rest/personnes/IdMax",
				  type:'Get',				 
				  success:function(result,status) 
				  {
					  idPersonne = result ; ; 
					  let ligne = $("<tr></tr>");
					  ligne.append($("<td></td>").append($("<input></input>").attr("value",idPersonne).attr("readonly","readonly").attr("id","txtIdPersonneAjout").addClass("form-control")));
					  ligne.append($("<td></td>").append($("<input></input>").attr("id","txtNomAjout").addClass("form-control")));
					  ligne.append($("<td></td>").append($("<input></input>").attr("id","txtPrenomAjout").addClass("form-control")));
					  ligne.append($("<td></td>").append($("<input></input>").attr("id","txtPoidsAjout").addClass("form-control")));
					  ligne.append($("<td></td>").append($("<input></input>").attr("id","txtTailleAjout").addClass("form-control")));
					  ligne.append($("<td></td>").append($("<input></input>").attr("id","txtSexeAjout").addClass("form-control")));
					  ligne.append($("<td></td>").append($("<input></input>").attr("value",$("#cmdNouvellePersonne").attr("data-idSociete")).attr("id","txtIdSocieteAjout").attr("readonly","readonly").addClass("form-control")));
					  						  
					  ligne.append($("<td></td>").append($("<button></button>").
				      attr("data-idPersonne",idPersonne).text("Enregistrer").addClass("btn btn-link Create")))
					  
					  ligne.append($("<td></td>").append($("<button></button>").
				      attr("data-idPersonne",idPersonne).text("Supprimer").addClass("btn btn-link supprimeUpdate").css("style","visibility:hidden")))
				
			      $("#tblPersonnesBodyUpdate").append(ligne);
					
					  
				  }
					
				}   
		   
		   
		   
		   
		   
		   
		   );
		
		 
		 
		  

			  
	  });
	
	$("#tblPersonnesUpdate tbody").on("click",".Create",function(event) {
		 event.preventDefault();
		 //alert("création en cours")
		 
		 let personne = {}
		 let idpers = $("#txtIdPersonneAjout").val(); 
		 personne._ID_Personne =  $("#txtIdPersonneAjout").val(); 
		 personne._Nom =  $("#txtNomAjout").val();
		 personne._Prenom =  $("#txtPrenomAjout").val();
		 personne._Poids =  $("#txtPoidsAjout").val();
		 personne._Taille =  $("#txtTailleAjout").val();
		 personne._Sexe =  $("#txtSexeAjout").val();
		 personne._ID_Societe =  $("#txtIdSocieteAjout").val();
		

		 
		 //alert(JSON.stringify(personne))
		 $.ajax(
		      {
		    	 url:"http://10.115.2.176/JAX_RS_TUTORIEL_JERSEY/rest/personnes",
		    	 type:'Post',
		    	 contentType:"application/json",
		    	 data:JSON.stringify(personne),
		    	 success:function(result,status){
		    		 //alert(status)
		    	 }
		    	  
		      }	 
		 
		 
		 )
		 
		 
		  let zones = $(this).parent().siblings().children();
		  
		   for(i=0;i<6;i++)
		  {
			  zones[i].setAttribute("readonly","true");
		  }
		  
		   
				  zones[1].setAttribute("id","txtNomPersonne"+idpers);
				  zones[2].setAttribute("id","txtPrenomPersonne"+idpers);
				  zones[3].setAttribute("id","txtPoidsPersonne"+idpers);
				  zones[4].setAttribute("id","txtTaillePersonne"+idpers);
				  zones[5].setAttribute("id","txtSexePersonne"+idpers);
				  zones[6].setAttribute("id","txtIdSocietePersonne"+idpers);
		   
		  
		  $(this).html("Modifier");
		  $(this).removeClass("Create");
		  $(this).addClass("modifieUpdate");
		 			 
	});
	
	$("#cmdSocieteMAJ").on("click",function() {
		  
		  let societe = new Object()
			societe._ID_Societe = $("#txtIdSociete").val() ;
			societe._Nom = $("#txtNomUpdate").val() ;
			societe._Activite = $("#txtActiviteUpdate").val() ;
			societe._CA = $("#txtCAUpdate").val() ;
			societe.employees = [];  
		  
		    //alert(JSON.stringify(societe))
		  
		  $.ajax(
				{
				url:"http://10.115.2.176/JAX_RS_TUTORIEL_JERSEY/rest/societes",
				type:'PUT',
				 contentType:"application/json",
				 dataType:"json",
				data:JSON.stringify(societe),
				success:function() {
					$("#UpdateSocieteModal").modal('hide');
					getSocietes();
				}					
				}  
		  
		  
		  
		  )
		  
		  
		  
		  
	  });
	
	
});