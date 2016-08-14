
function isSupportFileApi(){
	if (window.File && window.FileReader && 
		window.FileList && window.Blob) {
		return true;
	} else {
		return false;
	}
}

function formatFileSize(fileSize){
	var sizeUnitArr = ['Byte','KB','MB','GB'];
	if (fileSize == 0) {
		return "0 KB";
	}
	var i = parseInt(Math.floor(Math.log(fileSize) / Math.log(1024)));
	if (i == 0) {
		return fileSize + sizeUnitArr[i];
	}
    return (fileSize / Math.pow(1024, i)).toFixed(1) + sizeUnitArr[i];
}

function buildTable(evt){
	fileArr = document.getElementById('fileArrId').files || [];
	//var fileArr = evt.target.files;
	
	var removeTable = document.getElementById("tbId");
	if (null != removeTable) {
		document.body.removeChild(removeTable);
	}
	
	var tb = document.createElement("table");
	tb.setAttribute("id","tbId");
	tb.setAttribute("cellspacing",0);
	
	var thead = document.createElement("thead");
	tb.appendChild(thead);
	
	var theadTr = document.createElement("tr");
	thead.appendChild(theadTr);
	
	var thMimeType = document.createElement("th");
	var thSize = document.createElement("th");
	var thName = document.createElement("th");
	var thProgress = document.createElement("th");
	
	theadTr.appendChild(thMimeType);
	theadTr.appendChild(thSize);
	theadTr.appendChild(thName);
	theadTr.appendChild(thProgress);
	
	var textMimeType = document.createTextNode("MimeType");
	var textSize = document.createTextNode("Size");
	var textName = document.createTextNode("Name");
	var textProgress = document.createTextNode("Progress");
	
	thMimeType.appendChild(textMimeType);
	thSize.appendChild(textSize);
	thName.appendChild(textName);
	thProgress.appendChild(textProgress);
	
	var tbody = document.createElement("tbody");
	tb.appendChild(tbody);
	
	for (var i = 0; i < fileArr.length; i++) {
		var tbodyTr = document.createElement("tr");
		var tbodyTrId = "tbodyTrId" + i;
		tbodyTr.setAttribute("id",tbodyTrId);
		if (i % 2 == 0) {
			tbodyTr.setAttribute("class","even");
		} else {
			tbodyTr.setAttribute("class","");
		}
		tbody.appendChild(tbodyTr);
		
		var tdMimeType = document.createElement("td");
		var tdSize = document.createElement("td");
		var tdName = document.createElement("td");
		var tdProgress = document.createElement("td");
		
		tbodyTr.appendChild(tdMimeType);
		tbodyTr.appendChild(tdSize);
		tbodyTr.appendChild(tdName);
		tbodyTr.appendChild(tdProgress);
		tbodyTr.appendChild(tdProgress);
		
		var tdTextMimeType = document.createTextNode(fileArr[i].type);
		var tdTextSize = document.createTextNode(formatFileSize(fileArr[i].size));
		var tdTextName = document.createTextNode(fileArr[i].name);
		
		var spanProgressBar = document.createElement("progress");
		spanProgressBar.setAttribute("id","spanProgressBarMulti"+i);
		spanProgressBar.setAttribute("value",0);
		spanProgressBar.setAttribute("max",100);
		spanProgressBar.setAttribute("style","display:none");
		
		var spanProgressBarNumber = document.createElement("span");
		spanProgressBarNumber.setAttribute("id","spanProgressBarMultiNumber"+i);
		spanProgressBarNumber.setAttribute("style","display:none");
		
		tdProgress.appendChild(spanProgressBar);
		tdProgress.appendChild(spanProgressBarNumber);
		
		var tdTextProgress = document.createTextNode("");
		var tdTextProgressNumber = document.createTextNode("");
		
		spanProgressBar.appendChild(tdTextProgress);
		spanProgressBarNumber.appendChild(tdTextProgressNumber);
		
		tdMimeType.appendChild(tdTextMimeType);
		tdSize.appendChild(tdTextSize);
		tdName.appendChild(tdTextName);
	}
	document.body.appendChild(tb);
}

var fileArr = [];
//useCapture:true:capture/false:bubbling(suggest)
document.getElementById("fileArrId").addEventListener("change",buildTable,false);

function uploadProgressMultiFile(){
	if (typeof fileArr !== "undefined") {
		for (var i = 0; i < fileArr.length; i++) {
			uploadFile(fileArr[i],i);
		}
	}
}

function uploadFile(file,idx){
	var formData = new FormData();
	var xhr = new XMLHttpRequest();
	formData.append("file",file);
	
	xhr.upload.addEventListener("progress",function(evt){
		var spanProgressBarMultiId = "spanProgressBarMulti" + idx;
		var spanProgressBarMultiNumberId = "spanProgressBarMultiNumber" + idx;
		
		if (evt.lengthComputable) {
			var loadPercent = Math.round(evt.loaded * 100 / evt.total);
			
			document.getElementById(spanProgressBarMultiId).style.display = "block";
			document.getElementById(spanProgressBarMultiNumberId).style.display = "block";
			
			document.getElementById(spanProgressBarMultiId).value = loadPercent;
			document.getElementById(spanProgressBarMultiNumberId).innerHTML = loadPercent.toString() + "%";
		}
	},false);
	
	xhr.open("post","uploadProgressMultiFile",true);
	xhr.send(formData);
}

/* @see https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest
xhr.readyState:XMLHttpRequest对象状态,等于4即数据接收完毕
xhr.status:服务器返回的状态码,等于200即正常
xhr.responseText:服务器返回的文本数据
xhr.responseXML:服务器返回的xml格式数据

load:传输完成;abort:取消传输;error:传输出现错误;loadstart:开始传输;
loadEnd:传输结束,不知成功或失败;progress:传输进度(分上传progress和下载progress);
下载progress属于XMLHttpRequest对象,上传progress属于XMLHttpRequest.upload对象
xhr.onprogress = updateProgress;xhr.upload.onprogress = updateProgress
xhr.upload.onprogress = function(evt){
	if (evt.lengthComputable) {
		var percent = Math.round((evt.loaded / evt.total) * 100) + "%";
	}
};*/



