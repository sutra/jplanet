<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/taglibs.jsp" %>
<h2><fmt:message key="admin.login.title" /></h2>
<form action="login" method="post">

<%-- Display the error: ${openid_identifier} is not an editor. --%>
<c:if test="${!empty error}">
	<div class="error">
		<fmt:message key="${error}">
			<fmt:param>${openid_identifier}</fmt:param>
		</fmt:message>
	</div>
</c:if>

<%-- Display the OpenID Exception --%>
<c:if test="${!empty openIDException}">
	<div>OpenID Error: <span class="error">${openIDException.localizedMessage}</span></div>
</c:if>

<input type="text" id="openid_identifier" name="openid_identifier" class="text" style="background: url(http://www.openid.net/login-bg.gif) no-repeat; background-position: 0 50%; padding-left: 18px;" size="50" value="${openid_identifier}" />
<input type="submit" name="login" value="<fmt:message key="login" />" />
</form>
<div class="tip"><fmt:message key="admin.login.tip" /></div>
<script type="text/javascript">
<!--
// Focus to the textbox.
document.getElementById("openid_identifier").focus();
//-->
</script>