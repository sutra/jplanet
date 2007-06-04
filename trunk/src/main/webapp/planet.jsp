<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${title}</title>
<meta http-equiv="Content-Type
	content="application/xhtml-xml; charset=utf-8" />
<meta content="all" name="robots" />

<link rel="icon" type="image/png" href="http://shaderlab.com/openid/favicon.ico" />
<link rel="SHORTCUT ICON" type="image/png" href="http://shaderlab.com/openid/favicon.ico" />

<link rel="alternate" type="application/rss+xml" href="${ctx}/rss"
	title="Planet RSS Feed" />
<link rel="alternate" type="application/atom+xml"
	href="${ctx}/atom" title="Planet Atom Feed" />

<link rel="stylesheet" href="${ctx}/generic.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/layout.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/planet.css" type="text/css" />
</head>
<body>
<div>

	<div id="hdr">
		<div id="banner"></div>
		<div id="logo"></div>
		<div id="hdrNav"></div>
	</div>

	<div id="body">
	<c:set var="date" value="" />
	<c:forEach items="${entries}" var="entry">
		<c:if test="${date != entry.date}">
			<h2 class="date">${entry.date}</h2>
			<c:set var="date" value="${entry.date}" />
		</c:if>

		<div class="entry benoit">
			<div class="person-info">
				<a href="${entry.siteLink}" title="${entry.siteDescription}">
				<img class="face" src="http://planet.gnome.org/heads/nobody.png" alt="" />
				<br />${entry.siteName}<br />(${entry.post.author})</a>
			</div>
			<div class="post">
				<div class="post2">
					<div class="post-header">
						<h4 class="post-title"><a href="${entry.post.link}">${entry.post.title}</a></h4>
					</div>
					<div class="post-contents">${entry.post.description.value}</div>
					<div class="post-footer">
						<p><a href="${entry.post.link}">${entry.post.publishedDate}</a><p>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	</div>

<div id="sidebar">
	<div class="section">
	<h3>${title}</h3>
	<p>${description}</p>
	<p>Updated on June 03, 2007 06:42 PM UTC. Entries are normalised to UTC time.</p>
	</div>

	<div class="section">
	<h3>Subscribe</h3>
	<ul>

		<li><a href="atom">Atom 1.0</a></li>
		<li><a href="rss">RSS 2.0</a></li>
	</ul>

	</div>

	<div class="section">
	<h3>Feeds</h3>
	<ul>
	<li><a href="" title="subscribe"><img src="images/feed-icon-10x10.png" alt="(feed)"></a> <a href="" title="Somebody's weblog">Somebody</a></li>
	</ul>
	</div>

	<div class="section">
	<h3>Colophon</h3>
	<p>Brought to you by the <a href="">Planet</a> aggregator, <a href="http://redv.com/">Red V</a> (who kindly host the OpenID servers). Beautiful template design by <a href="http://www.actsofvolition.com/">Steven Garrity</a>, concept by <a href="http://www.gnome.org/~seth/">Seth Nickell</a> and <a href="http://www.isity.net/">Diana Fong</a>.</p>

	<p>Planet OpenID is edited by <a href="mailto:zhoushuqun@gmail.com?Subject=planet.openid.net.cn">Sutra Zhou</a>. Please mail him if you have a question or would like your blog added to the feed.</p>
	</div>
</div>

<div id="copyright">
Copyright &copy; 2007-2007, <a href="http://openid.net.cn/">The OpenID China Project</a><br />
Blog entries aggregated on this page are owned by, and represent the opinion of the author.<br />

<a href="http://validator.w3.org/check/referer">Optimised</a> for <a href="http://www.w3.org/">standards</a>. Hosted by <a href="http://redv.com/">Red V</a>.<br />
</div>


</div>
</body>
</html>
