<%@include file="/WEB-INF/jsp/header.jsp" %>
<html>
	<head>
		<%@include file="/WEB-INF/jsp/head-include.jsp" %>
		<title>Result!</title>
	</head>
	<body>
		<div class="container">
			<h1>Your guess</h1>
			<div class="col-md-12">
				<div class="asdf">
					<p>${result.guess}</p>
				</div>
			</div>
			<h1>Top post
				<small>
					<span class="reddit-ups">+${result.topComment.ups}</span>, <span class="reddit-downs">-${result.topComment.downs}</span> by <a href="http://reddit.com/u/${result.topComment.author}">/u/${result.topComment.author}</a>
				</small>
			</h1>
			<div class="col-md-12">
				<div class="asdf">
					<div class="markdownable">${result.topComment.body}</div>
				</div>
			</div>
			<h1>Match result <small id="matchPercent"></small></h1>
			<div class="col-md-12">
				<div class="asdf">
					<p class="markdownable highlightable">${result.topComment.body}</p>
				</div>
			</div>
			<div class="col-md-12">
				<a class="btn" id="tweetMe" href="https://twitter.com/intent/tweet?hashtags=gttp&original_referer=https%3A%2F%2Fabout.twitter.com%2Fresources%2Fbuttons&text=I%20was%20_percent_%20right%20in%20guessing%20_link_%27s%20top%20comment!&tw_p=tweetbutton&url=http%3A%2F%2Fgttp.paltaie.com">Tweet my score</a>
				<a class="btn btn-primary" href="<c:url value="/"/>">Try again</a>
			</div>
		</div>
		<%@include file="/WEB-INF/jsp/footer.jsp" %>
		<script type="text/javascript">
			$(document).ready(function() {
				var converter = new Showdown.converter();
				var highlightedLength = 0;
				var topCommentLength = ${fn:length(result.topComment.body)};
				
				$(".markdownable").each(function(index) {
					var html = converter.makeHtml($(this).text());
					$(this).html(html);
				});

				<c:forEach items="${result.matchedWords}" var="matchedWord">
					$(".highlightable").highlight("${matchedWord}");
				</c:forEach>
				
				$(".highlight").each(function(index) {
					highlightedLength += $(this).text().length;
				});
				
				var matchPercent = Math.round((highlightedLength / topCommentLength) * 100, 2);
				
				$("#matchPercent").text(matchPercent + "% match");
				
				var href = $("#tweetMe").attr("href");
				var moddedHref = href.replace("_percent_", matchPercent + "%25").replace("_link_", "http://redd.it/${result.topComment.id}");
				$("#tweetMe").attr("href", moddedHref);
			});
			
		</script>
	</body>
</html>