<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1>Tous nos articles :</h1>

<s:if test="articles.size == 0"><!-- gt , gte , eq -->
	<p>Aucun article n'a été trouvé...</p>
</s:if>
<s:if test="articles.size gt 0">
	<table>
		<s:iterator value="articles" var="article">
			<tr id="row_<s:property value="article.articleId"/>" class="<s:property value="#article.getClass().getSimpleName()" />">
				<%--<td>
					[
					<s:if test="#article instanceof com.cinepantin.shop.domain.Book">Book</s:if>
					<s:elseif test="#article instanceof com.cinepantin.shop.domain.Dvd">Dvd</s:elseif>
					<s:else>unknown</s:else>
					]
				</td> --%>
				<td>
					<s:property value="shortDescription" />
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
</s:if>

