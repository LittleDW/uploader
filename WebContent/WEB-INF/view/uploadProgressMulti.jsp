<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width,initial-scale=1.0">
	    <link rel="stylesheet" type="text/css" href="static/css/uploadProgressMulti.css" />
	</head>
	<body>
		
		<form method="post" enctype="multipart/form-data" style="margin-top:50px;">
			<input type="file" name="fileArr" id="fileArrId" multiple="multiple"  /><br/>
			<input type="button" value="Upload" onclick="uploadProgressMultiFile()" />
		</form>

		<script type="text/javascript" charset="UTF-8" 
			src="static/js/uploadProgressMulti.js"></script>
	</body>
</html>