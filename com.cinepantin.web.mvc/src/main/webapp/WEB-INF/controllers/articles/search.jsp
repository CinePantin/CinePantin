<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Search Results</h1>

<c:if test="${empty hotels}">
	No hotels found
</c:if>

<c:if test="${not empty hotels}">
	<ul>
		<c:forEach items="${hotels}" var="hotel">
			<li>
				<a href="<c:url value="/app/hotels/details?id=${hotel.id}" />">
					<c:out value="${hotel.name} (${hotel.city}, ${hotel.state})" />
				</a>
			</li>
		</c:forEach>
	</ul>
</c:if>

<div class="buttonGroup">
	<a href="<c:url value="/app/hotels/index" />">
		New Search
	</a>
</div>
