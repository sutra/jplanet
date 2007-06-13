<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/taglibs.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<script type="text/javascript" src="${ctx}/admin/planet.js"></script>
</head>
<body>
<form name="planet" method="post">
<fieldset>
<legend><fmt:message key="planet.site" /></legend>
<dl>
<dt><label for="title"><fmt:message key="planet.title" /><fmt:message key="colon" /></label></dt>
<dd><input type="text" id="title" name="title" value="${planet.title}" /></dd>
<dt><label for="siteUrl"><fmt:message key="planet.siteUrl" /><fmt:message key="colon" /></label></dt>
<dd><input type="text" name="siteUrl" id="siteUrl" value="${planet.siteUrl}" /></dd>
<dt><label for="language"><fmt:message key="planet.language" /><fmt:message key="colon" /></label></dt>
<dd><input type="text" name="language" id="language" value="${planet.language}" /></dd>
<dt><label for="description"><fmt:message key="planet.description" /><fmt:message key="colon" /></label></dt>
<dd><textarea rows="5" cols="100" name="description" id="description">${planet.description}</textarea></dd>
</dl>
</fieldset>
<fieldset>
<legend><fmt:message key="planet.admin" /></legend>
<dl>
<dt><label for="adminName"><fmt:message key="planet.adminName" /><fmt:message key="colon" /></label></dt>
<dd><input type="text" name="adminName" id="adminName" value="${planet.adminName}" /></dd>
<dt><label for="adminEmail"><fmt:message key="planet.adminEmail" /><fmt:message key="colon" /></label></dt>
<dd><input type="text" name="adminEmail" id="adminEmail" value="${planet.adminEmail}" /></dd>
<dt><label for="mailSubject"><fmt:message key="planet.mailSubject" /><fmt:message key="colon" /></label></dt>
<dd><input type="text" name="mailSubject" id="mailSubject" value="${planet.mailSubject}" /></dd>
</dl>
</fieldset>

<fieldset>
<legend><fmt:message key="date" /></legend>
<dl>
<dt><label for="groupingDateFormat"><fmt:message key="planet.groupingDateFormat" /><fmt:message key="colon" /></label></dt>
<dd><input type="text" name="groupingDateFormat" id="groupingDateFormat" value="${planet.groupingDateFormat}" /></dd>
<dt><label for="postDateFormat"><fmt:message key="planet.postDateFormat" /><fmt:message key="colon" /></label></dt>
<dd><input type="text" name="postDateFormat" id="postDateFormat" value="${planet.postDateFormat}" /></dd>
<dt><label for="updatePeriod"><fmt:message key="planet.updatePeriod" /><fmt:message key="colon" /></label></dt>
<dd><input type="text" name="updatePeriod" id="updatePeriod" value="${planet.updatePeriod}" /><fmt:message key="minutes" /></dd>
</dl>
</fieldset>

<fieldset>
<legend><fmt:message key="planet.editors" /></legend>
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
<tfoot>
<tr>
<td>
<c:choose>
<c:when test="${empty planet.editors}">
<font color='red'>
<fmt:message key="admin.editor.warning" />
</font>
</c:when>
<c:otherwise><fmt:message key="admin.editor.warning" /></c:otherwise>
</c:choose>
</td>
<td><input type="button" onclick="addOpenIdRow()" value="<fmt:message key="addRow" />" /></td>
</tr>
</tfoot>
</table>
</fieldset>

<fieldset>
<legend><fmt:message key="planet.subscriptions" /></legend>
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
</fieldset>

<input type="reset" name="reset" />
<input type="submit" name="save" value="<fmt:message key="save" />" />

</form>
</body>
</html>