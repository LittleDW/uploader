<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width,initial-scale=1.0">
	</head>
	<body>
		<table>
			<tr>
				<td>OriginalFileName :</td>
				<td>${file.originalFilename}</td>
			</tr>
			<tr>
				<td>ContentType :</td>
				<td>${file.contentType}</td>
			</tr>
			<tr>
				<td>Size :</td>
				<td>${file.size}</td>
			</tr>
		</table>
		<br />
		
		<table>
			<c:forEach items="${mfArr}" var="file">	
				<tr>
					<td>OriginalFileName :</td>
					<td>${file.originalFilename}</td>
				</tr>
				<tr>
					<td>ContentType :</td>
					<td>${file.contentType}</td>
				</tr>
				<tr>
					<td>Size :</td>
					<td>${file.size}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>