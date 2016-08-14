<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width,initial-scale=1.0">
	</head>
	<body>
		<form method="post" enctype="multipart/form-data"
			action="<%=request.getContextPath()%>/uploadServlet">
			<input type="file" name="file" />
			<input type="submit" value="Upload" />
		</form>
	</body>
</html>