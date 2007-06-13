<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/taglibs.jsp" %>
<h2><fmt:message key="admin.login.title" /></h2>
<form action="login" method="post">
<input type="text" name="openid_url" style="background: url(http://www.openid.net/login-bg.gif) no-repeat; background-position: 0 50%; padding-left: 18px;" />
<input type="submit" name="login" value="<fmt:message key="login" />" />
</form>
