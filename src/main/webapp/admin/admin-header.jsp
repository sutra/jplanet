<%@ include file="/includes/taglibs.jsp" %>
<%@ include file="/includes/sitemesh-taglibs.jsp" %>
<div id="hdr">
	<div id="banner"></div>
	<div id="logo"></div>
	<div id="hdrNav">
		<div>
			<a href="<c:url value="/aggregator" />"><fmt:message key="aggregator" /></a>
			·
			<a href="<c:url value="/admin/planet" />"><fmt:message key="admin" /></a>
			·
			<c:choose>
				<c:when test="${!empty planet.editors and empty editor}">
					<a href="<c:url value="/admin/login" />"><fmt:message key="login" /></a>
				</c:when>
				<c:otherwise>
					<a href="<c:url value="/admin/logout" />"><fmt:message key="logout" /></a>
					<fmt:message key="admin.loggedInAs">
						<fmt:param>
							<c:choose>
								<c:when test="${!empty editor}">
									${editor}
								</c:when>
								<c:otherwise>
									<fmt:message key="admin.anonymous" />
								</c:otherwise>
							</c:choose>
						</fmt:param>
					</fmt:message>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
