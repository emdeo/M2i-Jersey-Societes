<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- Importer les classes java -->
<%@page import="adelium.dao.*"%>
<%@page import="adelium.metier.*"%>
<%@page import="adelium.servlet.*"%>
<%@page import="adelium.html.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<!-- Scripts JQuery (Ajax), Popper et BootStrap -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- Bootstrap boutons avec ic�nes -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- Custom CSS -->
<link rel="stylesheet" href="./index.css">

<!-- Custom JS -->
<script src="./sources.js"></script>

</head>
<body>

	<%
		// R�initialiser les tables 'Societe' et 'Personne'

		DAO_Societe daos = new DAO_Societe();
		daos.Truncate();
		daos.Instanciate();

		DAO_Personne daop = new DAO_Personne();
		daop.Truncate();
		daop.Instanciate();
	%>

	<div class="container align-items-center">
		<br>
		<h3>Table Soci�t�</h3>
		<br>

		<!-- Afficher la table "Societe" de la BDD "dp_formation" -->
		<table class="table">
			<thead>
				<tr>
					<th>ID Soci�t�</th>
					<th>Nom</th>
					<th>CA</th>
					<th>Activit�</th>
					<th>Employ�s</th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody id="tbodySociete">

				<%
					// Afficher le tableau de soci�t�s
					out.println(HTMLDynamique.TableauSocietes());
				%>

			</tbody>
		</table>



		<!-- COLLAPSE : Ajouter une soci�t� -->
		<button type='button' class='btn btn-outline-primary'
			id='btnAjouterSociete' data-toggle='collapse'
			data-target='#collapseAjouterSociete' aria-expanded='false'
			aria-controls='collapseExample'>Ajouter une soci�t�</button>
		<div class="collapse" id="collapseAjouterSociete"></div>



		<!-- COLLAPSE : Modifier une soci�t� -->
		<div class="collapse" id="collapseModifierSociete"></div>



		<!-- Tableau d'employ�s -->
		<div id="tblEmployes"></div>



		<!-- MODAL : ajouter ou modifier un employ� -->
		<%
			out.println(HTMLDynamique.GenererModalEmploye());
		%>



	</div>
</body>
</html>