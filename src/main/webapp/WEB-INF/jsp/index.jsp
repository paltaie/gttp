<%@include file="/WEB-INF/jsp/header.jsp"%>
<html>
	<head>
		<%@include file="/WEB-INF/jsp/head-include.jsp"%>
		<title>Guess the Top Post</title>
	</head>
	<body>
		<div class="container">
			<div class="col-md-12">
				<h1>Welcome to Guess the Top Post!</h1>
        		<p class="lead">A light-hearted dig at the reddit hivemind mentality - by <a href="http://github.com/paltaie">@paltaie</a></p>
				<form role="form" method="post" action="<c:url value="/topComment.gttp"/>">
					<div class="form-group col-md-6">
						<label for="subreddit">Subreddit (reddit.com/r/<code>pics</code>/comments/92dd8/test_post_please_ignore/)</label>
						<input type="text" class="form-control" id="subreddit" name="subreddit" placeholder="Enter subreddit">
					</div>
					<div class="form-group col-md-6">
						<label for="threadId">Thread ID (reddit.com/r/pics/comments/<code>92dd8</code>/test_post_please_ignore/)</label>
						<input type="text" class="form-control" id="threadId" name="threadId" placeholder="Enter thread ID">
					</div>
					<div class="form-group col-md-8 col-md-offset-2">
						<label for="guess">Your guess (keywords work better than sentences)</label>
						<input type="text" class="form-control" id="guess" name="guess" placeholder="fedorable m'lady my axe fingernails">
					</div>
					<div class="row">
						<div class="form-group col-md-4 col-md-offset-4">
							<button type="submit" class="btn-block btn-primary">Submit</button>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 text-center">
							<p>Need inspiration? See below!</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3" id="sub1col">
							<h2>/r/pics</h2>
							<img id="sub1colspinner" src="<c:url value="/img/ajax-loader.gif"/>" alt="Loading..."/>
						</div>
						<div class="col-md-3" id="sub2col">
							<h2>/r/funny</h2>
							<img id="sub2colspinner" src="<c:url value="/img/ajax-loader.gif"/>" alt="Loading..."/>
						</div>
						<div class="col-md-3" id="sub3col">
							<h2>/r/AdviceAnimals</h2>
							<img id="sub3colspinner" src="<c:url value="/img/ajax-loader.gif"/>" alt="Loading..."/>
						</div>
						<div class="col-md-3" id="sub4col">
						<h2 id="customSubHeader">/r/? <small class="input-group">
						      <input type="text" id="customSubreddit" placeholder="yoursubreddit" class="form-control">
						      <span class="input-group-btn">
						        <a id="customSubredditButton" class="btn btn-default" type="button">Go!</a>
						      </span></small></h2>
						</div>
					</div>
				</form>
			</div>
		</div>
		<%@include file="/WEB-INF/jsp/footer.jsp"%>
		<script type="text/javascript">
			populate("pics", 10, "#sub1col");
			populate("funny", 10, "#sub2col");
			populate("AdviceAnimals", 10, "#sub3col");
			populate("cringepics", 10, "#sub4col");
			
			function populate(subreddit, count, target) {
				$.get("<c:url value="/subreddit/"/>" + subreddit + ".gttp", function(data) {
					$(target + "spinner").remove();
					for (var i = 0; i < count; i++) {
						var threadPara = '<p class="clickable" subreddit="' + subreddit + '" thread-id="' + data[i].id + '">' + data[i].title + ' (<span class="reddit-ups">' + data[i].ups + '</span>, <span class="reddit-downs">' + data[i].downs + '</span>)</p>';
						$(threadPara).appendTo(target).on("click", function() {
							populateFields(this);
						});
					}
				});
			}
			
			$("#customSubredditButton").click(function(event) {
				$('<img src="<c:url value="/img/ajax-loader.gif"/>" alt="Please wait" id="sub4colspinner"/>').append("#sub4col");
				var subreddit = $("#customSubreddit").val();
				$(this).parent().hide();
				$("#customSubHeader").text("/r/" + subreddit);
				populate(subreddit, 10, '#sub4col');
			});
			
			function populateFields(source) {
				var threadId = $(source).attr("thread-id");
				var subreddit = $(source).attr("subreddit");
				$("#threadId").val(threadId);
				$("#subreddit").val(subreddit);
			}
		</script>
	</body>
</html>