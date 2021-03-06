<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/taglibs.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<script type="text/javascript" src="${ctx}/admin/planet.js"></script>
<script type="text/javascript">
<!--
function $(id) {
	return document.getElementById(id);
}
function $F(id) {
	return document.getElementById(id).value;
}
function chkFrm() {
	if (!(/^[0-9]*[1-9][0-9]*$/.test($F("updatePeriod")))) {
		alert("<fmt:message key="admin.updatePeriod.plusOnly" />");
		$("updatePeriod").focus();
		return false;
	}
}
//-->
</script>
</head>
<body>
<form name="planet" method="post" onsubmit="return chkFrm()">
<c:if test="${success == true}">
	<div class="message">
		<fmt:message key="admin.save.success" />
	</div>
</c:if>

<%-- Site settings --%>
<fieldset>
<legend><fmt:message key="planet.site" /></legend>
<dl>
<dt><label for="admin.planet.title"><fmt:message key="planet.title" /><fmt:message key="colon" /></label></dt>
<dd><input type="text" name="title" id="admin.planet.title" class="text" style="width:100%;" value="${planet.title}" /></dd>
<dt><label for="admin.planet.siteUrl"><fmt:message key="planet.siteUrl" /><fmt:message key="colon" /></label></dt>
<dd><input type="text" name="siteUrl" id="admin.planet.siteUrl" class="text" style="width:100%;" value="${planet.siteUrl}" /></dd>
<dt><label for="admin.planet.language"><fmt:message key="planet.language" /><fmt:message key="colon" /></label></dt>
<dd><input type="text" name="language" id="admin.planet.language" class="text" style="width:100%;" value="${planet.language}" /></dd>
<dt><label for="admin.planet.keywords"><fmt:message key="planet.keywords" /><fmt:message key="colon" /></label></dt>
<dd><input type="text" name="keywords" id="admin.planet.keywords" class="text" style="width:100%;" value="${planet.keywords}" /></dd>
<dt><label for="admin.planet.description"><fmt:message key="planet.description" /><fmt:message key="colon" /></label></dt>
<dd><textarea rows="5" cols="80" name="description" id="admin.planet.description" style="width:100%;" class="text">${planet.description}</textarea></dd>
<dt><label for="admin.planet.copyright"><fmt:message key="planet.copyright" /><fmt:message key="colon" /></label></dt>
<dd><textarea rows="5" cols="80" name="copyright" id="admin.planet.copyright" style="width:100%;" class="text">${planet.copyright}</textarea></dd>
</dl>
</fieldset>

<%-- Admin --%>
<fieldset>
<legend><fmt:message key="planet.admin" /></legend>
<dl>
<dt><label for="adminName"><fmt:message key="planet.adminName" /><fmt:message key="colon" /></label></dt>
<dd><input type="text" name="adminName" id="adminName" class="text" style="width:100%;" value="${planet.adminName}" /></dd>
<dt><label for="adminEmail"><fmt:message key="planet.adminEmail" /><fmt:message key="colon" /></label></dt>
<dd><input type="text" name="adminEmail" id="adminEmail" class="text" style="width:100%;" value="${planet.adminEmail}" /></dd>
<dt><label for="mailSubject"><fmt:message key="planet.mailSubject" /><fmt:message key="colon" /></label></dt>
<dd><input type="text" name="mailSubject" id="mailSubject" class="text" style="width:100%;" value="${planet.mailSubject}" /></dd>
</dl>
</fieldset>

<%-- Dates settings --%>
<fieldset>
<legend><fmt:message key="date" /></legend>
<dl>
<dt><label for="groupingDateFormat"><fmt:message key="planet.groupingDateFormat" /><fmt:message key="colon" /></label></dt>
<dd><input type="text" name="groupingDateFormat" id="groupingDateFormat" class="text" style="width:100%;" value="${planet.groupingDateFormat}" /></dd>
<dt><label for="postDateFormat"><fmt:message key="planet.postDateFormat" /><fmt:message key="colon" /></label></dt>
<dd><input type="text" name="postDateFormat" id="postDateFormat" class="text" style="width:100%;" value="${planet.postDateFormat}" /></dd>
<dt><label for="updatePeriod"><fmt:message key="planet.updatePeriod" /><fmt:message key="colon" /></label></dt>
<dd>
<input type="text" name="updatePeriod" id="updatePeriod" class="text" style="width:40px;" value="${planet.updatePeriod}" /><fmt:message key="minutes" /></dd>
</dl>
</fieldset>

<%-- editors --%>
<fieldset>
	<legend><fmt:message key="planet.editors" /></legend>
	<table style="width:100%;">
		<thead>
			<tr>
				<th><fmt:message key="planet.editor.openid" /></th>
				<th><fmt:message key="planet.editor.comment" /></th>
			</tr>
		</thead>
		<tbody id="editors">
			<c:forEach var="editor" items="${planet.editors}">
			<tr>
				<td style="width:50%;"><input type="text" name="editor.openid" class="text" style="width:100%;" value="${editor.openid}" /></td>
				<td><input type="text" name="editor.comment" class="text" style="width:100%;" value="${editor.comment}" /></td>
			</tr>
			</c:forEach>
			<tr>
				<td style="width:50%;"><input type="text" name="editor.openid" class="text" style="width:100%;" value="" /></td>
				<td><input type="text" name="editor.comment" class="text" style="width:100%;" value="" /></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					<input type="button" onclick="addOpenIdRow()" value="<fmt:message key="addRow" />" />
					<c:choose>
						<c:when test="${empty planet.editors}">
							<font color='red'><fmt:message key="admin.editor.warning" /></font>
						</c:when>
						<c:otherwise><fmt:message key="admin.editor.warning" /></c:otherwise>
					</c:choose>
				</td>
			</tr>
		</tfoot>
	</table>
</fieldset>

<%-- Subscriptions --%>
<fieldset>
	<legend><fmt:message key="planet.subscriptions" /></legend>
	<table style="width:100%;">
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
				<td style="width:10%;"><input type="text" name="subscription.title" class="text" style="width:100%;" value="${subscription.title}" /></td>
				<td style="width:25%;"><input type="text" name="subscription.feedUrl" class="text" style="width:100%;" value="${subscription.feedUrl}" /></td>
				<td style="width:25%;"><input type="text" name="subscription.siteUrl" class="text" style="width:100%;" value="${subscription.siteUrl}" /></td>
				<td style="width:40%;"><input type="text" name="subscription.description" class="text" style="width:100%;" value="${subscription.description}" /></td>
			</tr>
			</c:forEach>
			<tr>
				<td style="width:10%;"><input type="text" name="subscription.title" class="text" style="width:100%;" value="" /></td>
				<td style="width:25%;"><input type="text" name="subscription.feedUrl" class="text" style="width:100%;" value="" /></td>
				<td style="width:25%;"><input type="text" name="subscription.siteUrl" class="text" style="width:100%;" value="" /></td>
				<td style="width:40%;"><input type="text" name="subscription.description" class="text" style="width:100%;" value="" /></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td><input type="button" onclick="addSubscriptionRow()" value="<fmt:message key="addRow" />" /></td>
			</tr>
		</tfoot>
	</table>
</fieldset>

<input type="submit" name="save" value="<fmt:message key="save" />" />
<input type="reset" name="reset" />

</form>
</body>
</html>