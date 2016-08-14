<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width,initial-scale=1.0">
		
		<script type="text/javascript">
			
			function uploadAjaxSingle(){
	    		var fileObj = document.getElementById("fileSingleId").files[0];
	    		var formData = new FormData();
	    		
	    		formData.append("file",fileObj);
	    		var xhr = new XMLHttpRequest();
	    		
	    		xhr.open("post","uploadAjaxSingle",true);
	    		xhr.send(formData);
	
	    		xhr.onload = function(e){
	    			if (this.status == 200) {
						window.alert(this.responseText);
					}
	    		}
	    	}
			
			function uploadAjaxMultiple(){
	    		var formData = new FormData();
	    		var fileArr = document.getElementById("fileArrId").files || [];
		    	for (var i = 0; i < fileArr.length; i++) {
		    		formData.append("fileArr",fileArr[i]);
				}
	    		
	    		var xhr = new XMLHttpRequest();
	    		xhr.open("post","uploadAjaxMultiple",true);
	    		xhr.send(formData);

	    		xhr.onload = function(e){
	    			if (this.status == 200) {
						window.alert(this.responseText);
					}
	    		}
	    	}
		</script>
	</head>
	<body>
		
		<form method="post" enctype="multipart/form-data">
			<input type="file" name="file" id="fileSingleId" />
			<input type="button" value="Upload" onclick="uploadAjaxSingle()" />
		</form>
		
		<form method="post" enctype="multipart/form-data" style="margin-top:50px;">
			<input type="file" name="fileArr" id="fileArrId" multiple="multiple" />
			<input type="button" value="Upload" onclick="uploadAjaxMultiple()" />
		</form>
		
	</body>
</html>