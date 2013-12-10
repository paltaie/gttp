<%@include file="/WEB-INF/jsp/header.jsp"%>
<html>
	<head>
		<%@include file="/WEB-INF/jsp/head-include.jsp"%>
		<title>Guess the Top Post</title>
	</head>
	<body>
		<div class="container">
			<div class="col-md-12">
				<h1>Guess the top post</h1>
				<form role="form" action="<c:url value="/topComment.gttp"/>">
					<div class="form-group col-md-6">
						<label for="subreddit">Subreddit (http://www.reddit.com/r/<code>pics</code>/comments/92dd8/test_post_please_ignore/)</label>
						<input type="text" class="form-control" id="subreddit" name="subreddit" placeholder="Enter subreddit">
					</div>
					<div class="form-group col-md-6">
						<label for="threadId">Thread ID (http://www.reddit.com/r/pics/comments/<code>92dd8</code>/test_post_please_ignore/)</label>
						<input type="text" class="form-control" id="threadId" name="threadId" placeholder="Enter thread ID">
					</div>
					<div class="form-group col-md-8 col-md-offset-2">
						<label for="guess">Your guess</label>
						<input type="text" class="form-control" id="guess" name="guess" placeholder="&quot;fedorable m'lady my axe fingernails&quot;">
					</div>
					<div class="form-group col-md-2">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</form>
			</div>
		</div>
		<%@include file="/WEB-INF/jsp/footer.jsp"%>
		<script type="text/javascript">
			$(document).ready(function() {
				$(".markdownable").each(function(index) {
					var thisText = $(this).text();
					$(this).text(unescape(markdown.toHTML(thisText)));
				});
			});
		</script>
	</body>
</html>