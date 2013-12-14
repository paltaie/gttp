<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
[
	<c:forEach items="${listing}" var="link" varStatus="status">
		{
			"author": "${link.author}",
			"created_utc": "${link.created_utc}",
			"id": "${link.id}",
			"title": "${link.title}",
			"ups": ${link.ups},
			"downs": ${link.downs},
			"score": "${link.ups - link.downs}"
		}
		<c:if test="${not status.last}">
		,
		</c:if>
	</c:forEach>
]