<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${planet.title}</title>
<meta http-equiv="Content-Type"
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
		<c:if test="${date != entry.groupingDate}">
			<h2 class="date">${entry.groupingDate}</h2>
			<c:set var="date" value="${entry.groupingDate}" />
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
					<div class="post-contents">${entry.description.value}</div>
					<div class="post-footer">
						<p><a href="${entry.post.link}">${entry.postDate}</a></p>
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
	<p>Updated on ${updateDate}. Entries are normalised to UTC time.</p>
	</div>

	<div class="section">
	<h3>Subscribe</h3>
	<ul>
		<li><a href="rss_0.9.xml">RSS 0.9</a></li>
		<li><a href="rss_0.92.xml">RSS 0.92</a></li>
		<li><a href="rss_0.93.xml">RSS 0.93</a></li>
		<li><a href="rss_0.94.xml">RSS 0.94</a></li>
		<li><a href="rss_1.0.xml">RSS 1.0</a></li>
		<li><a href="rss_2.0.xml">RSS 2.0</a></li>
		<li><a href="atom_0.3.xml">Atom 0.3</a></li>
		<li><a href="atom_1.0.xml">Atom 1.0</a></li>
	</ul>

	</div>

	<div class="section">
	<h3>Feeds</h3>
	<ul>
	<c:forEach items="${planet.subscriptions}" var="subscription">
		<li>
			<a href="${subscription.feedUrl}" title="subscribe">
			<img src="images/feed-icon-10x10.png" alt="(feed)" /></a>
			<a href="${subscription.siteUrl}" title="${subscription.description}">
			${subscription.title}
			</a>
		</li>
	</c:forEach>
	</ul>
	</div>

	<div class="section">
	<h3>Colophon</h3>
	<p>Brought to you by the <a href="http://code.google.com/p/jplanet">JPlanet</a> aggregator, <a href="http://redv.com/">Red V</a> (who kindly host the OpenID servers). Beautiful template design by <a href="http://www.actsofvolition.com/">Steven Garrity</a>, concept by <a href="http://www.gnome.org/~seth/">Seth Nickell</a> and <a href="http://www.isity.net/">Diana Fong</a>.</p>

	<p>Planet OpenID is edited by <a href="mailto:${planet.adminEmail}?Subject=${planet.mailSubject}">${planet.adminName}</a>. Please mail him if you have a question or would like your blog <a href="${ctx}/admin">added</a> to the feed.</p>
	</div>
	
	<div class="section">
<c:forEach begin="1" end="3">
<script type="text/javascript"><!--
google_ad_client = "pub-2182119399996810";
google_ad_width = 160;
google_ad_height = 600;
google_ad_format = "160x600_as";
google_ad_type = "text_image";
google_ad_channel = "";
google_color_border = "FFFFFF";
google_color_bg = "EEEEEE";
google_color_link = "0000FF";
google_color_text = "000000";
google_color_url = "008000";
//-->
</script>
<script type="text/javascript"
  src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
</c:forEach>
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
