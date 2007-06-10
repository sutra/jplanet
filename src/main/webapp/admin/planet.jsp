<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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


<table>
<tr>
<th>Open ID</th>
</tr>
<c:forEach var="editor" items="${planet.editors}">
<tr>
<td><input type="text" name="editor.openid" value="${editor.openid}" /></td>
</tr>
</c:forEach>
<tr>
<td><input type="text" name="editor.openid" value="" /></td>
</tr>
</table>

<table>
<tr>
<th>Title</th>
<th>FeedUrl</th>
<th>SiteUrl</th>
<th>Description</th>
</tr>
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
</table>
<input type="submit" name="save" value="Save" />
</form>