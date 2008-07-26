<%--
  ~ Copyright 2006-2008 Redv.com
  --%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>OpenID HTML FORM Redirection</title>
</head>
<body>
    <form name="openid-form-redirection" action="${message.OPEndpoint}" method="post" accept-charset="utf-8">
        <c:forEach var="parameter" items="${message.parameterMap}">
        <input type="hidden" name="${parameter.key}" value="${parameter.value}"/>
        </c:forEach>
        <button type="submit">Continue...</button>
    </form>
<script type="text/javascript">
document.forms['openid-form-redirection'].submit();
</script>
</body>
</html>
