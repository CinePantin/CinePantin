<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><%@ taglib 
		prefix="c" 
		uri="http://java.sun.com/jsp/jstl/core" 
%><%@ taglib 
		prefix="s" 
		uri="/struts-tags"
%><c:url 
	value="/" 
	var="appRoot" 
/>	<h1>Votre panier - <s:property value="#session.visitor.basket.orderLines.size()" default="0" /> référence<s:if test="#session.visitor.basket.orderLines.size() gt 1">s</s:if></h1>

	<s:if test="#session.visitor.basket.orderLines.size() gt 0"><!-- gt , gte , eq, ... -->
		<table>
			<thead>
				<tr>
					<td>&Delta;</td>
					<td>Description</td>
					<td>Qty</td>
					<td>Actions</td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#session.visitor.basket.orderLines.entrySet()" var="basketLine">
				<tr>
					<td>
						<s:if test="#basketLine.key.articleId == #request.articleId">
							<s:property value="#basketLine.value - #request.quantity"/> + (<s:property value="#request.quantity"/>) =&gt;
						</s:if>
					</td>
					<td>
						<s:property value="#basketLine.key.shortDescription"/>
					</td>
					<td>
						<s:property value="#basketLine.value"/>
					</td>
					<td>
						<s:if test="#basketLine.key instanceof com.cinepantin.shop.domain.PhysicalArticle">
							<a  title="One less of this reference, please"
								id="article_<s:property value="#basketLine.key.articleId"/>_add"
								href="${appRoot}trn/basket/add/-1/<s:property value="#basketLine.key.class.simpleName" />/<s:property value="#basketLine.key.articleId"/>">
								[-1]
							</a>
							&nbsp;
							<a  title="None of this reference. Clear all, please"
								id="article_<s:property value="#basketLine.key.articleId"/>_add"
								href="${appRoot}trn/basket/remove/all/<s:property value="#basketLine.key.class.simpleName" />/<s:property value="#basketLine.key.articleId"/>">
								[X]
							</a>
							&nbsp;
							<a  title="One more of this reference, please"
								id="article_<s:property value="#basketLine.key.articleId"/>_add"
								href="${appRoot}trn/basket/add/1/<s:property value="#basketLine.key.class.simpleName" />/<s:property value="#basketLine.key.articleId"/>">
								[+1]
							</a>
						 </s:if>
					</td>
				</tr>
				</s:iterator>
			</tbody>
			
				
			
		</table>
		<a 
			id="basketClearAll"
			href="${appRoot}trn/basket/clear-all">
			[Vider le panier]
		</a>
	</s:if>
	<s:else>
		<p>
			...est vide. Vous n'avez pas encore trouvé votre bonheur
			dans notre <a href="${appRoot}">catalogue</a> ?  
			<span class="todo">(TODO: update link)</span>
		 </p>
	</s:else>
	
	<h2>
		<s:if test="#request.articleId != null">
			<s:if test="removedArticle != null">
				Successfully removed reference # <s:property value="#request.articleId"/> (<s:property value="removedArticle.shortDescription"/>)
			</s:if>
			<s:else>
				Successfully updated reference #<s:property value="#request.articleId"/> (quantity modified by <s:property value="#request.quantity"/>).
			</s:else>
		</s:if>
	</h2>
	<h2><s:property value="#session.foo" /></h2>