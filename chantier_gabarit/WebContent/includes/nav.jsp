<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script type="text/javascript" src="../js/global.js"></script>
<link rel="stylesheet" type="text/css" href="../../res/css/style_menu.css" />
<link rel="stylesheet" type="text/css" href="res/css/style_menu.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>menu</title>
</head>
		<ul class="menu_deroulant">
			<li >	
				<a href="#index.jsp"  title="accueil">Home</a>
				<ul>
					<li ><a href="#">Qui sommes nous?</a></li>												
					<li><a href="#">Histoire</a></li>
					<li><a href="#">Partenariat</a></li>
				</ul>
			</li>
			<li >	
				<a href="#vente.jsp"  title="">Catalogue</a>
				<ul>
					<li ><a href="#">Sous menu 1</a></li>												
					<li><a href="#">Sous menu 2</a></li>
					<li><a href="#">Sous menu 3</a></li>
				</ul>
			</li>		
			<li >	
				<a href="#connexion.jsp"  title="">Votre compte</a>
				<ul>
					<li ><a href="#panier.jsp">Panier</a></li>												
					<li><a href="#inscription.jsp">Inscription</a></li>
					<li><a href="#commande.jsp">Commande</a></li>
				</ul>
			</li>
			<li >	
				<a href="#contact.jsp"  title="">Contact</a>
				<ul>
					<li ><a href="#">Sous menu 1</a></li>												
					<li><a href="#">Sous menu 2</a></li>
					<li><a href="#">Sous menu 3</a></li>
				</ul>
			</li>
			
		</ul>
