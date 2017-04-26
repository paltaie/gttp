<%@include file="/WEB-INF/jsp/header.jsp" %>
<html>
	<head>
		<%@include file="/WEB-INF/jsp/head-include.jsp" %>
		<title>Result!</title>
	</head>
	<body>
		<div class="container">
			
			<div class="col-md-12">
				<h1>Fedora results!</h1>
				<div class="asdf">
					<p>Thread: "<a target="_blank" href="${thread.url}">${thread.title}</a>" <c:if test="${not empty thread.url}"></c:if></p> <span class="reddit-ups">${thread.ups}</span>, <span class="reddit-downs">${thread.downs}</span> by <a target="_blank" href="http://reddit.com/u/${thread.author}">/u/${thread.author}</a></p>
				</div>
			</div>
			<div class="col-md-5 text-right">
				<h1>Your guess</h1>
				<div class="asdf">
					<p>${result.guess}</p>
				</div>
			</div>
			<div class="col-md-6 col-md-offset-1">
				<h1>Top post
					<small>
						<span class="reddit-ups">${result.topComment.ups}</span>, <span class="reddit-downs">${result.topComment.downs}</span> by <a href="http://reddit.com/u/${result.topComment.author}">/u/${result.topComment.author}</a>
					</small>
				</h1>
				<div class="asdf">
					<div class="markdownable">${result.topComment.body}</div>
				</div>
			</div>
			<div class="col-md-12 text-center">
				<h1>Match result <small id="matchPercent"><img src="<c:url value="/img/spinner.gif"/>" alt="Calculating..."/></small></h1>
				<div class="asdf">
					<p id="matchResult" class="markdownable highlightable">${result.topComment.body}</p>
				</div>
			</div>
			<hr/>
			<div class="col-md-8 col-md-offset-4">
				<a class="btn btn-default" id="tweetMe" target="_blank" href="https://twitter.com/intent/tweet?hashtags=gttp&amp;original_referer=https%3A%2F%2Fabout.twitter.com%2Fresources%2Fbuttons&amp;text=I%20was%20_percent_%20right%20in%20guessing%20_link_%27s%20top%20comment!&amp;tw_p=tweetbutton&amp;url=http%3A%2F%2Freddit-gttp.appspot.com"><span class="glyphicon glyphicon-retweet"></span>&nbsp;Tweet my score</a>
				<a class="btn btn-default" id="fbMe" target="_blank" href="http://www.facebook.com/sharer.php?u=http://reddit-gttp.appspot.com&amp;t=I was _percent_ right in guessing _link_'s top comment!"><span class="glyphicon glyphicon-share"></span>&nbsp;Share on Facebook</a>
				<a class="btn btn-primary" href="<c:url value="/"/>">Try again</a>
			</div>
		</div>
		<%@include file="/WEB-INF/jsp/footer.jsp" %>
		<script type="text/javascript">
			$(document).ready(function() {
				var converter = new Showdown.converter();
				var highlightedLength = 0;
				var topComment = $("#matchResult").text().replace(/\s/g, "");
				var topCommentLength = topComment.length;
				
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
				var fbHref = $("#fbMe").attr("href");
				var moddedHref = href.replace("_percent_", matchPercent + "%25").replace("_link_", "http://redd.it/${thread.id}");
				var moddedFbHref = fbHref.replace("_percent_", matchPercent + "%25").replace("_link_", "http://redd.it/${thread.id}");
				$("#tweetMe").attr("href", moddedHref);
				$("#fbMe").attr("href", moddedFbHref);
			});
		</script>
	</body>
</html>