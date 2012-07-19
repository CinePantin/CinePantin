<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>Hotel Details</h1>

<c:if test="${empty hotel}">
	<div class="section">
		No hotel found
	</div>
</c:if>

<c:if test="${not empty hotel}">
	<div class="section">
		<fieldset>
			<div class="field">
				<div class="label">Name:</div>
				<div class="output"><c:out value="${hotel.name}" /></div>
			</div>
			<div class="field">
				<div class="label">Address:</div>
				<div class="output"><c:out value="${hotel.address}" /></div>
			</div>
			<div class="field">
				<div class="label">City:</div>
				<div class="output"><c:out value="${hotel.city}" /></div>
			</div>
			<div class="field">
				<div class="label">State:</div>
				<div class="output"><c:out value="${hotel.state}" /></div>
			</div>
			<div class="field">
				<div class="label">Zip:</div>
				<div class="output"><c:out value="${hotel.zip}" /></div>
			</div>
			<div class="field">
				<div class="label">Price:</div>
				<div class="output"><fmt:formatNumber value="${hotel.price}" type="currency" currencyCode="USD" /></div>
			</div>
		</fieldset>
	</div>
</c:if>

<div class="buttonGroup">
	<a href="<c:url value="/app/hotels/index" />">
		New Search
	</a>
</div>
