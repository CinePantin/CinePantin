<%@ 
page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><%@ 
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" 
%><%@ 
taglib prefix="s" uri="/struts-tags"
%><h1>Bienvenue sur CinePantin !</h1><c:url value="/" var="appRoot" />

<s:if test="articles.size == 0"><!-- gt , gte , eq -->
	<p>Aucun article n'a été trouvé...</p>
</s:if>
<s:if test="articles.size gt 0">

	<h2>Tous nos articles :</h2>
	<table>
		<s:iterator value="articles" var="article">
			<tr 
				id="article_<s:property value="#article.articleId"/>" 
				class="article <s:property value="#article.getClass().getSimpleName()" />"
			>
				<td>
					<s:property value="articleId" />
				</td>
				<td>
					<s:property value="shortDescription" />
				</td>
				<td>
					<a 
						id="article_${articleId}_add"
						href="${appRoot}trn/basket/add/1/<s:property value="#article.getClass().getSimpleName()" />/${articleId}">
						[Ajouter au panier]
					</a>
				</td>
				<%--
				<td>
					<s:url id="removeUrl" action="remove">
						<s:param name="id" value="id" />
					</s:url>
					<s:a href="%{removeUrl}" theme="ajax" targets="persons">Remove</s:a>
					<s:a id="a_%{id}" theme="ajax" notifyTopics="/edit">Edit</s:a>
				</td>
				 --%>
			</tr>
		</s:iterator>
	</table>
	<div class="todo">(TODO: show only "show-on-home" articles)</div>
</s:if>