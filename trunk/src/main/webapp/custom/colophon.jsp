<%@ include file="/includes/taglibs.jsp" %>
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