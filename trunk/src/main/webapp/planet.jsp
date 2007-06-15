<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/taglibs.jsp" %>
<html>
<head>
<title>${planet.title}</title>
</head>
<body>
	<c:set var="date" value="" />
	<c:forEach items="${entries}" var="entry">
		<c:set var="entryGroupingDate">
			<fmt:formatDate value="${entry.datetime}" pattern="${planet.groupingDateFormat}" />
		</c:set>
		<c:if test="${date != entryGroupingDate}">
			<h2 class="date"><fmt:formatDate value="${entry.datetime}" pattern="${planet.groupingDateFormat}" /></h2>
			<c:set var="date" value="${entryGroupingDate}" />
		</c:if>

		<div class="entry benoit">
			<div class="person-info">
				<a href="${entry.siteLink}" title="${entry.siteDescription}">
				<img class="face" src="http://planet.gnome.org/heads/nobody.png" alt="" />
				<br />${entry.siteName}
				<c:if test="${!empty entry.post.author}">
				<br />(${entry.post.author})
				</c:if>
				</a>
			</div>
			<div class="post">
				<div class="post2">
					<div class="post-header">
						<h4 class="post-title"><a href="${entry.post.link}">${entry.post.title}</a></h4>
					</div>
					<div class="post-contents"><p>${entry.description.value}</p></div>
					<div class="post-footer">
						<p>
							<a href="${entry.post.link}">
								<fmt:formatDate value="${entry.datetime}" pattern="${planet.postDateFormat}" />
							</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</body>
</html>