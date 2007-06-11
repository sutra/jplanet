<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<script type="text/javascript">
function addOpenIdRow(){
	var tbody = document.getElementById("editors");
	var row = document.createElement("tr");
	var td1 = document.createElement("td");
	var input1 = document.createElement("input");
	input1.name = "editor.openid";
	td1.appendChild(input1);
	row.appendChild(td1);
	tbody.appendChild(row);
}
function addSubscriptionRow() {
	var tbody = document.getElementById("subscriptions");
	var row = document.createElement("tr");
	var td1 = document.createElement("td");
	var td2 = document.createElement("td");
	var td3 = document.createElement("td");
	var td4 = document.createElement("td");
	var input1 = document.createElement("input");
	var input2 = document.createElement("input");
	var input3 = document.createElement("input");
	var input4 = document.createElement("input");
	input1.name = "subscription.title";
	input2.name = "subscription.feedUrl";
	input3.name = "subscription.siteUrl";
	input4.name = "subscription.description";
	td1.appendChild(input1);
	td2.appendChild(input2);
	td3.appendChild(input3);
	td4.appendChild(input4);
	row.appendChild(td1);
	row.appendChild(td2);
	row.appendChild(td3);
	row.appendChild(td4);
	tbody.appendChild(row);
}
</script>
</head>
<body>
<form name="planet" method="post">
<dl>
<dt>Title:</dt>
<dd><input type="text" name="title" value="${planet.title}" /></dd>
<dt>Description:</dt>
<dd><textarea rows="5" cols="100" name="description">${planet.description}</textarea></dd>
<dt>SiteUrl:</dt>
<dd><input type="text" name="siteUrl" value="${planet.siteUrl}" /></dd>
<dt>Language:</dt>
<dd><input type="text" name="language" value="${planet.language}" /></dd>
<dt>AdminName:</dt>
<dd><input type="text" name="adminName" value="${planet.adminName}" /></dd>
<dt>AdminEmail:</dt>
<dd><input type="text" name="adminEmail" value="${planet.adminEmail}" /></dd>
<dt>MailSubject:</dt>
<dd><input type="text" name="mailSubject" value="${planet.mailSubject}" /></dd>
<dt>GroupingDateFormat:</dt>
<dd><input type="text" name="groupingDateFormat" value="${planet.groupingDateFormat}" /></dd>
<dt>PostDateFormat:</dt>
<dd><input type="text" name="postDateFormat" value="${planet.postDateFormat}" /></dd>
<dt>UpdatePeriod:</dt>
<dd><input type="text" name="updatePeriod" value="${planet.updatePeriod}" />minutes</dd>
</dl>

<table>
<thead>
<tr>
<th>Open ID</th>
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
<tfoot><tr><td><input type="button" onclick="addOpenIdRow()" value="New row" /></td></tr></tfoot>
</table>

<table>
<thead>
<tr>
<th>Title</th>
<th>FeedUrl</th>
<th>SiteUrl</th>
<th>Description</th>
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
<tfoot><tr><td><input type="button" onclick="addSubscriptionRow()" value="New row" /></td></tr></tfoot>
</table>

<input type="submit" name="save" value="Save" />

</form>
</body>
</html>