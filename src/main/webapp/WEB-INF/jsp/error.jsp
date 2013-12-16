<%@include file="/WEB-INF/jsp/header.jsp" %>
<html>
	<head>
		<%@include file="/WEB-INF/jsp/head-include.jsp" %>
		<title>Error!</title>
	</head>
	<body>
		<div class="container">
			<div class="col-md-12">
				<h1>Something's wrong!</h1>
				<div class="asdf">
					<p>Can't find thread ID ${threadId} and subreddit ${subreddit}. Want to <a href="<c:url value="/"/>">try again?</a></p>
				</div>
			</div>
		</div>
	</body>
</html>