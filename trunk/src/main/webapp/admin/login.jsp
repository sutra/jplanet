<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/taglibs.jsp" %>
<h2><fmt:message key="admin.login.title" /></h2>
<form action="login" method="post">
<c:if test="${!empty error}">
	<div class="error">
		<fmt:message key="${error}">
			<fmt:param>${openid_url}</fmt:param>
		</fmt:message>
	</div>
</c:if>
<input type="text" name="openid_url" class="text" style="background: url(http://www.openid.net/login-bg.gif) no-repeat; background-position: 0 50%; padding-left: 18px;" size="50" value="${openid_url}" />
<input type="submit" name="login" value="<fmt:message key="login" />" />
</form>