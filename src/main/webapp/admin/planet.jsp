<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<script type="text/javascript" src="${ctx}/admin/planet.js"></script>
</head>
<body>
<form name="planet" method="post">
<dl>
<dt><fmt:message key="planet.title" /><fmt:message key="colon" /></dt>
<dd><input type="text" name="title" value="${planet.title}" /></dd>
<dt><fmt:message key="planet.description" /><fmt:message key="colon" /></dt>
<dd><textarea rows="5" cols="100" name="description">${planet.description}</textarea></dd>
<dt><fmt:message key="planet.siteUrl" /><fmt:message key="colon" /></dt>
<dd><input type="text" name="siteUrl" value="${planet.siteUrl}" /></dd>
<dt><fmt:message key="planet.language" /><fmt:message key="colon" /></dt>
<dd><input type="text" name="language" value="${planet.language}" /></dd>
<dt><fmt:message key="planet.adminName" /><fmt:message key="colon" /></dt>
<dd><input type="text" name="adminName" value="${planet.adminName}" /></dd>
<dt><fmt:message key="planet.adminEmail" /><fmt:message key="colon" /></dt>
<dd><input type="text" name="adminEmail" value="${planet.adminEmail}" /></dd>
<dt><fmt:message key="planet.mailSubject" /><fmt:message key="colon" /></dt>
<dd><input type="text" name="mailSubject" value="${planet.mailSubject}" /></dd>
<dt><fmt:message key="planet.groupingDateFormat" /><fmt:message key="colon" /></dt>
<dd><input type="text" name="groupingDateFormat" value="${planet.groupingDateFormat}" /></dd>
<dt><fmt:message key="planet.postDateFormat" /><fmt:message key="colon" /></dt>
<dd><input type="text" name="postDateFormat" value="${planet.postDateFormat}" /></dd>
<dt><fmt:message key="planet.updatePeriod" /><fmt:message key="colon" /></dt>
<dd><input type="text" name="updatePeriod" value="${planet.updatePeriod}" /><fmt:message key="minutes" /></dd>
</dl>

<table>
<thead>
<tr>
<th><fmt:message key="planet.editor.openid" /></th>
</tr>
</thead>
<tbody id="editors">
<c:forEach var="editor" items="${planet.editors}">
<tr>
<td><input type="text" name="editor.openid" value="${editor.openid}" /></td>
</tr>
</c:forEach>
<tr>
<td><input type="text" name="editor.openid" value="" /></td>
</tr>
</tbody>
<tfoot><tr><td><input type="button" onclick="addOpenIdRow()" value="<fmt:message key="addRow" />" /></td></tr></tfoot>
</table>

<table>
<thead>
<tr>
<th><fmt:message key="planet.subscription.title" /></th>
<th><fmt:message key="planet.subscription.feedUrl" /></th>
<th><fmt:message key="planet.subscription.siteUrl" /></th>
<th><fmt:message key="planet.subscription.description" /></th>
</tr>
</thead>
<tbody id="subscriptions">
<c:forEach var="subscription" items="${planet.subscriptions}">
<tr>
<td><input type="text" name="subscription.title" value="${subscription.title}" /></td>
<td><input type="text" name="subscription.feedUrl" value="${subscription.feedUrl}" /></td>
<td><input type="text" name="subscription.siteUrl" value="${subscription.siteUrl}" /></td>
<td><input type="text" name="subscription.description" value="${subscription.description}" /></td>
</tr>
</c:forEach>
<tr>
<td><input type="text" name="subscription.title" value="" /></td>
<td><input type="text" name="subscription.feedUrl" value="" /></td>
<td><input type="text" name="subscription.siteUrl" value="" /></td>
<td><input type="text" name="subscription.description" value="" /></td>
</tr>
</tbody>
<tfoot><tr><td><input type="button" onclick="addSubscriptionRow()" value="<fmt:message key="addRow" />" /></td></tr></tfoot>
</table>

<input type="submit" name="save" value="<fmt:message key="save" />" />

</form>
</body>
</html>