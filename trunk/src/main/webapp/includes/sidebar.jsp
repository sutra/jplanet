<%@ include file="/includes/taglibs.jsp" %>
<div id="sidebar">
	<div class="section">
	<h3>${planet.title}</h3>
	<p>${planet.description}</p>
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

	<jsp:include flush="true" page="/custom/colophon.jsp" />

</div>