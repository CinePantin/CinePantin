<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
				<!-- Header -->
				<div id="entete">
					<div id="bloc_logo">
						<c:url value="/" var="urlHome" />
						<h1><a href="${urlHome}" title="Accueil de Ciné Pantin, livres sur le cinéma, DVD, objets de collection">Ciné Pantin</a></h1>
						<!--  <p>Emplacement logo</p> -->
						<!--  <a href="page1.jsp">LOGO<img src="" alt="" title="" /></a>-->
					</div>
					<div id="bloc_right_topnav">BLOC UTILISATEUR/emplacement moteur de recherche/<a class="onglet_hover" href="#compte.jsp">mon compte</a>/panier/déconnexion...etc</div>
				</div>
				<!-- End Header -->