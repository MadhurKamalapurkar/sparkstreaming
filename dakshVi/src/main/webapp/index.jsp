<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>

<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>DakshVi</title>
<link href="<c:url value="/css/tooltipster.css" />" rel="stylesheet">
<link href="<c:url value="/css/other.css" />" rel="stylesheet">
<link href="<c:url value="/css/visualize.jQuery.css" />" rel="stylesheet">
<link href="<c:url value="/css/demopage.css" />" rel="stylesheet">
<script src="<c:url value="/js/jquery.min.js" />"></script>
<script src="<c:url value="/js/visualize.jQuery.js" />"></script>
<script src="<c:url value="/js/editabletable.js" />"></script>
<script src="<c:url value="/js/jquery.tooltipster.min.js" />"></script>
<script type="text/javascript">

var deviceParams = ["Total packets received", "Total packets transmitted", "Total transmitted packets dropped", "Total received packets dropped"];

var devJson = [{"Total packets received":"rx_total_pckts"}, {"Total packets transmitted":"tx_total_pckts"}, {"Total transmitted packets dropped":"tx_pckt_lost"}, {"Total received packets dropped":"rx_pckt_lost"}];

var deviceRealParams = ["rx_total_pckts", "tx_total_pckts", "tx_pckt_lost", "rx_pckt_lost"];

var jsonObject = [{deviceName: 'analytics-qfx5100-01',},{deviceName: 'analytics-qfx5100-02',},{deviceName: 'analytics-qfx5100-03',},{deviceName: 'analytics-qfx5100-04',},{deviceName: 'analytics-qfx5100-05',}];

function loadData() {
	$(".tooltip").tooltipster({
	    theme: 'tooltipster-punk'
	});
	var mainDivContainer = document.getElementById('mainDiv');
	
	createDetailBoard(jsonObject);
	fillInfo();
}

	function fillInfo() {
		
		for(var i=0;i<deviceParams.length;i++) {
			fillTitle(jsonObject[i].deviceName);
		}
		setTimeout(fillInfo, 1000);
	}

	
	function createDetailBoard(jsonObject) {
		var tableTag = document.createElement("table");
		tableTag.setAttribute("width", "100%");
		tableTag.style.marginLeft="250px";
		var headerTag = document.createElement("th");
		//headerTag.setAttribute("width","90%");
		headerTag.innerHTML="Device Name";
		headerTag.style.minWidth="150px";
		var headerRow = getRow();		
		var headerTag2 = document.createElement("th");
		//headerTag2.setAttribute("width","90%");
		headerTag2.innerHTML="Number of interfaces";
		headerTag2.style.minWidth="150px";
		var headerTag3 = document.createElement("th");
		//headerTag3.setAttribute("width","90%");
		headerTag3.innerHTML="Device statistics";
		headerTag3.style.minWidth="150px";
		var headerTag4 = document.createElement("th");
		//headerTag3.setAttribute("width","90%");
		headerTag4.innerHTML="Information";
		headerTag4.style.minWidth="150px";
		headerRow.appendChild(headerTag);
		headerRow.appendChild(headerTag2);
		headerRow.appendChild(headerTag3);
		headerRow.appendChild(headerTag4);
		tableTag.appendChild(headerRow);
		
		for(key in jsonObject) {
			var trTag = document.createElement("tr");
			var tdTag = document.createElement("td");
			var tdTag2 = document.createElement("td");
			var stats = document.createElement("td");
			var info = document.createElement("td");
			
			var deviceNameInput = getInputCell();
			deviceNameInput.setAttribute("type","radio");
					
			
			for(var i=0;i<deviceParams.length;i++) {
				var anchor = document.createElement("a");
				anchor.appendChild(document.createTextNode(deviceParams[i]));
				anchor.setAttribute("href", "${contextPath}/hello?deviceName="+jsonObject[key].deviceName+"&property="+deviceRealParams[i]);
				anchor.appendChild(document.createElement("br"));
				anchor.style.textDecoration="none";
				stats.appendChild(anchor);
			}
			
			var div = document.createElement('div');
			
				div.setAttribute("id", jsonObject[key].deviceName+"-info");
				fillTitle(jsonObject[key].deviceName);
		
			info.appendChild(div);
			
			
			
			var div = document.createElement('div');
			div.appendChild(document.createTextNode(jsonObject[key].deviceName));
			div.setAttribute("class","tooltip");
			div.setAttribute("title", "");
			tdTag.appendChild(div);
			trTag.appendChild(tdTag);
			tdTag2.innerHTML=100;
			trTag.appendChild(tdTag2);
			trTag.appendChild(stats);
			trTag.appendChild(info);
			
			tableTag.appendChild(trTag);
		}
		document.getElementById("mainDiv").appendChild(tableTag);
		
	}
	
	function fillTitle(deviceName) {
		var obj = {};
        $.ajax({
            url: "jsp/GetData.jsp",
            type: 'GET',
            dataType: 'json',
            parameters:{deviceName:deviceName},
            success: function(data) {
            		document.getElementById(deviceName+"-info").innerHTML = "";
            	    //document.getElementById(deviceName+"-info").innerHTML = data;
            	    var mainDiv = document.createElement('div');
            	    for(key in data) {
            	    	
            	    	if( key != "_id" | key != "portCount" | key != "lastTimeStamp") {
            	    		var div = document.createElement('div');
                	    	div.innerHTML=key+":"+data[key];
                	    	div.appendChild(document.createElement('br'));
                	    	mainDiv.appendChild(div);
            	    	}
            	    	
            	    }
            	    document.getElementById(deviceName+"-info").innerHTML = mainDiv.innerHTML;
            	    
            },
            error: function() {
                obj="error";
            }
        });
        return obj;
	}
	
	
	function createRadioButton(text) {
		var radioInput = getInputCell();
		var div = document.createElement("div");
		radioInput.setAttribute("type","radio");
		radioInput.setAttribute("value",text);
		radioInput.setAttribute("name","deviceName");
		div.appendChild(radioInput);
		div.appendChild(document.createTextNode(text));
		return div;
	}
	
	function getInputCell() {
		return document.createElement("input");
	}
	
	function getCell() {
		return document.createElement("td");
	}
	
	function getRow() {
		return document.createElement("tr");
	}



</script>


</head>
<body onload="loadData()">
	<img alt="Juniper Networks" src="images/juniper.jpeg" height="100" width="120" />
	<img alt="Daksh" src="images/DAKSH-4.jpg" height="60" width="120" style="float:right"/>
	<div id="mainDiv"></div>
	<input type="hidden" id="tooltipTitle" />
</body>
</html>
