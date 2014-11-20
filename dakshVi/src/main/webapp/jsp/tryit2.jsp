<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
String deviceName = (String)request.getAttribute("deviceName");
String property = (String)request.getAttribute("property");
%>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>Spark</title>
		<link href="<c:url value="/css/other.css" />" rel="stylesheet">
		<link href="<c:url value="/css/visualize.jQuery.css" />" rel="stylesheet">
		<link href="<c:url value="/css/demopage.css" />" rel="stylesheet">		
		<script src="<c:url value="/js/jquery.min.js" />"></script>
		<script src="<c:url value="/js/visualize.jQuery.js" />"></script>
		<script src="<c:url value="/js/editabletable.js" />"></script>
		<script type="text/javascript">
		var property = "<%=property%>";
			$(function(){
				$('.visualize.visualize-area').css('float','left');
				$('table').visualize({type: 'area'});
				getContent();
			});

			function updateGraph(object) {
				var number_of_x_elements = document.getElementById('x').getElementsByTagName('th').length;
				if(number_of_x_elements > 6) {
					document.getElementById('x').removeChild(document.getElementById('x').getElementsByTagName('th')[0]);
				}


				
				var number_of_y_elements = document.getElementById('y').getElementsByTagName('td').length;
				if(number_of_y_elements > 6) {
					document.getElementById('y').removeChild(document.getElementById('y').getElementsByTagName('td')[0]);
				}


				var value = object[property];
				setTimeout(getContent, 25000);
				var thTag = document.createElement('th');
				thTag.setAttribute("width","100%");
				thTag.style.minWidth="100px"
				var date = new Date(object["lastTimeStamp"]);
				thTag.innerHTML=date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
				var tdTag = document.createElement('td');
				tdTag.setAttribute("width","100%");
				tdTag.style.minWidth="100px"
				tdTag.innerHTML=value;
				document.getElementById('x').appendChild(thTag);
				document.getElementById('y').appendChild(tdTag);
				$('.visualize').trigger('visualizeRefresh');
				//getContent();
			}
			
			
			function getContent()
            {
				
				var dev = "<%=deviceName%>";
                $.ajax({
                    url: "jsp/GetData.jsp",
                    type: 'GET',
                    dataType: 'json',
                    parameters:{deviceName:dev},
                    success: function(data) {
                    	updateGraph(data);
                    	//createDetailBoard(data);     
                    },
                    error: function() {
                        $('#details').text("error");
                    }
                });
            }
			
			function createDetailBoard(jsonObject) {
				document.getElementById("details").innerHTML="";
				var tableTag = document.createElement("table");
				var headerTag = document.createElement("th");
				headerTag.innerHTML="Metrics";
				var headerRow = getRow();
				var headerTag2 = document.createElement("th");
				headerTag2.innerHTML="Value";
				headerRow.appendChild(headerTag);
				headerRow.appendChild(headerTag2);
				tableTag.appendChild(headerRow);
				
				for(key in jsonObject) {
					if(key === "tx_pckt_lost" | key === "rx_pckt_lost" | key === "rx_total_pckts") {
						var trTag = document.createElement("tr");
						var tdTag = document.createElement("td");
						var tdTag2 = document.createElement("td");
						
						tdTag.innerHTML=key;
						trTag.appendChild(tdTag)
						tdTag2.innerHTML=jsonObject[key];
						trTag.appendChild(tdTag2);
						
						tableTag.appendChild(trTag);
					}
				}
				document.getElementById("details").appendChild(tableTag);
				
			}
			
			function getCell() {
				return document.createElement("td");
			}
			
			function getRow() {
				return document.createElement("tr");
			}
			
		</script>

</head>
	<body>
	<img alt="Juniper Networks" src="images/juniper.jpeg" height="100" width="120">
	<a href="${contextPath}" style="float:right"><font size="4">Home</font></a>
	<div style="margin-left:80px;margin-bottom:10px">
		<table style="margin:left:60px" width="100%">
			<caption><%=deviceName %></caption>
			<thead>
				<tr id="x" width="100%">
					<td style="min-width: 100px;" width="100%"></td>
<!-- 					<th style="min-width: 100px;" width="100%">6:01</th> -->
<!-- 					<th style="min-width: 100px;" width="100%">6:02</th> -->
<!-- 					<th style="min-width: 100px;" width="100%">6:03</th> -->
<!-- 					<th style="min-width: 100px;" width="100%">6:04</th> -->
<!-- 					<th style="min-width: 100px;" width="100%">6:05</th> -->
<!-- 					<th style="min-width: 100px;" width="100%">6:06</th> -->
				</tr>
			</thead>
			<tbody>
				<tr id="y" width="100%">

					<th style="min-width: 100px;" width="100%"><%=property %></th>
<!-- 					<td style="min-width: 100px;" width="100%">50</td> -->
<!-- 					<td style="min-width: 100px;" width="100%">10</td> -->
<!-- 					<td style="min-width: 100px;" width="100%">40</td> -->
<!-- 					<td style="min-width: 100px;" width="100%">60</td> -->
<!-- 					<td style="min-width: 100px;" width="100%">20</td> -->
<!-- 					<td style="min-width: 100px;" width="100%">70</td> -->
				</tr>
			</tbody>
		</table>
	</div>
		<div id="details" style="inline:block;position:relative;float:right;margin-top:-205px;margin-right:60px"></div>
	</body>
</html>
