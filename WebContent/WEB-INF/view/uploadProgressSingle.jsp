<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width,initial-scale=1.0">
	    
	    <script type="text/javascript">
	    	
	    	function uploadProgress(evt){
	    		if (evt.lengthComputable) {
					var loadPercent = Math.round(evt.loaded * 100 / evt.total);
					document.getElementById("progressNumberSingleFile").innerHTML = loadPercent.toString() + "%";
					document.getElementById("progressBarSingleFile").style.display = "block";
					document.getElementById("progressBarSingleFile").value = loadPercent;
				}else {
					document.getElementById("progressBarSingleFile").innerHTML = "unable to computable progress!";
				}
	    	}
	    	
	    	function uploadComplete(evt){
	    		alert(evt.target.responseText);
	    	}
	    	
			function uploadFailure(evt){
				alert("upload file failure!");
			}
			
			function uploadAbort(evt){
				alert("upload file abort!");
			}
			
			function uploadProgressSingleFile(){
				var formData = new FormData();
				var xhr = new XMLHttpRequest();
				formData.append("file",document.getElementById("fileId").files[0]);
				xhr.upload.addEventListener("progress",uploadProgress,false);
				xhr.addEventListener("load",uploadComplete,false);
				xhr.addEventListener("error",uploadFailure,false);
				xhr.addEventListener("abort",uploadAbort,false);
				xhr.open("post","uploadProgressSingleFile",true);
				xhr.send(formData);
			}
			
			function showFileInfo(){
				document.getElementById("progressNumberSingleFile").innerHTML = "";
				document.getElementById("progressBarSingleFile").style.display = "none";
			}
	    </script>
	</head>
	<body>
		
		<form method="post" enctype="multipart/form-data">
			<input type="file" name="file" id="fileId" onchange="showFileInfo()" /><br/>
			<input type="button" value="Upload" onclick="uploadProgressSingleFile()" />
			<progress id="progressBarSingleFile" value="0" max="100" style="display:none; "></progress> 
			<span id="progressNumberSingleFile" value="0" max="100"></span>
		</form>
		
	</body>
</html>