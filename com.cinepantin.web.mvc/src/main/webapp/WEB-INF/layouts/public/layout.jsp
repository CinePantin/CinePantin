<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><%@ 
page contentType="text/html; charset=UTF-8" %><%@ 
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ 
taglib prefix="s" uri="/struts-tags"%><%@ 
taglib prefix="sj" uri="/struts-jquery-tags"%><%@ 
taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"
%><html><c:url value="/" var="appRoot" /><c:url value="/" var="resRoot" />
<head>
	
	<title><tiles:getAsString name="title" /> - Cine-Pantin</title>
	<%-- struts2-jquery JS (+CSS?) imports  --%>
	<sj:head 
	/>
	<%-- pas terrible : pas de fallback ni rien... 
				loadFromGoogle="true"
	 --%>
</head>
<body>
	<div id="head">
	
		<h1><a href="${appRoot}"><img src="${resRoot}/res/img/logo/movies-icone-8122-64.png" />Cinepantin</a></h1>
		
		
		<div id="loginZone">
			
			<fieldset>
				<legend>DEBUG</legend>
				<p>#session.visitor == null ? <strong><s:property value="#session.visitor == null"/></strong></p>
				<p>#session.visitor instanceof com.cinepantin.shop.domain.User ? <strong><s:property value="#session.visitor instanceof com.cinepantin.shop.domain.User" /></strong></p>
				<p>#session.visitor.basket == null ? <strong><s:property value="#session.visitor.basket == null"/></strong></p>
				<p>#session.visitor.basket instanceof com.cinepantin.shop.domain.UserBasket ? <strong><s:property value="#session.visitor.basket instanceof com.cinepantin.shop.domain.UserBasket" /></strong></p>
				<p>#session.visitor.basket.orderLines == null ? <strong><s:property value="#session.visitor.basket.orderLines == null"/></strong></p>
				<p>#session.visitor.basket.orderLines.size() ? <strong><s:property value="#session.visitor.basket.orderLines.size()"/></strong></p>
			</fieldset>
			
			
			
			<fieldset>
          			<legend>
          				<s:if test="#session.visitor instanceof com.cinepantin.shop.domain.User">
          					<s:property value="#session.visitor.login" />
          				</s:if>
          				<s:else>
          					Visiteur
          				</s:else>
          				<%-- c:out value="${(visitor instanceof User) ? ((User)visitor).getLogin() : 'Anonyme'}" default="User" / --%>
					<s:a
						namespace="/trn/basket"
						action="view"
						>
							(Panier : <s:property value="#session.visitor.basket.orderLines.size()" default="0"/> 
							article<s:if test="#session.visitor.basket.orderLines.size() gt 1">s</s:if>)
					</s:a>
          			</legend>
					
					
				<s:if test="#session.visitor 
							instanceof com.cinepantin.shop.domain.User"><%-- Authenticated user --%>
					<s:form namespace="/account" action="logout" includeContext="true">
						<sj:submit 
								id="formLogoutSubmit"
								value="Se déconnecter" 
								button="true"
								loadingText="Déconnexion en cours..."
								timeout="5000"
								targets="formResult" 
								onCompleteTopics="handleOnComplete"
								onBeforeTopics="removeMessage"
								clearForm="true" 
						/>
					</s:form>
				</s:if>
				<s:else><%-- Unauthenticated user --%>
					<s:form namespace="/account" action="login" includeContext="true">
						<s:textfield name="login" label="Login"></s:textfield>
						<s:password name="password" label="Password"></s:password>
						<s:token name="preventDoubleSubmit" />
						
						<sj:submit 
				            	id="formLoginSubmit"
				            	value="Se connecter" 
				            	button="true"
				            	loadingText="Authentification en cours..."
				            	timeout="5000"
				            	targets="formResult"
								onCompleteTopics="handleOnComplete"
								onBeforeTopics="removeMessage"
				            	/>
						
			            	<%--
									<s:url var="ajaxLoginAction" value="/account/login"/>
							onSuccessTopics="handleLoginResult"
			            	href="%{ajaxLoginAction}"
			            	requestType="POST"
			            	openDialog=""
			            	openDialogTitle=""
			            	openTemplate=""
			            	indicator="indicator" --%>
					</s:form>
		            <a href="#TODO">Me renvoyer mon mot de passe. <span class="todo">(TODO)</span></a>
				</s:else>
			
					
				<script type="text/javascript">
				    
				    jQuery.subscribe('handleOnComplete', function(event,data) {
				        console.log("handleOnComplete:");
				        
				        var outcome = $(data).find("#loginOutcome").text();
				        
				        console.log(event);
				        console.log(data);
				        console.log(outcome);
				        var newLoginZone = $(data).find("#loginZone").html();
				        $("#loginZone").html(newLoginZone);
				        $("#formResult").text(outcome);
				    });
				    
				    
				    jQuery.subscribe('removeMessage', function(event,data) {
				        $("#formResult").text("");
				    });
				</script>
				
				<div id="formResult"></div>
			</fieldset>
		</div><%-- /div#loginZone --%>
		
	</div><%-- /div#head --%>
	
	
	<div id="corps">
		<tiles:insertAttribute name="body" />
	</div>
	
</body>
</html>
