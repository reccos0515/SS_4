var i = 0;
var j = 0;
var url = "http://localhost:9001";

function loadReports (){
	var http = new XMLHttpRequest();
	http.onreadystatechange = function(){
		if(http.readyState == 4){
			var response = JSON.parse(http.responseText);
			clearBox("div1");
			var tbl=$("<table/>").attr("id","mytable");
		    $("#div1").append(tbl);
		    $("#mytable").append("<tr>" + "<th>ID</th>" + "<th>Status</th>" + 
		    	"<th>User Name</th>" + "<th>Interests</th>" + "<th>Biography</th>" + "</tr>");
 				var count = 0;
 				if(i == response.users.length){
 					i=0;
 				}
		    for (i = i; i < response.users.length; i++) {
			    if(count == 10){
			    	break;
			    }
			    var tr="<tr>";
		        var td1="<td>"+response.users[i]["id"]+"</td>";
		        var td2="<td>"+response.users[i]["status"]+"</td>";
		        var td3="<td>"+response.users[i]["userName"]+"</td>";
		        var td4="<td>"+response.users[i]["interests"]+"</td>";
		        var td5="<td>"+response.users[i]["bio"]+"</td></tr>";
		        count++;
		       $("#mytable").append(tr+td1+td2+td3+td4+td5); 
			}
		}
	}
	http.open("GET", url + "/ben/users", true);
	http.send();
}

function goModeratorHome() {
	 window.location="ModeratorHome.html";
}

function goModeratorReports() {
	window.location="ModeratorReports.html";
}

function goSpecificReports() {
	window.location="ModeratorSpecificReports.html";
}

function goBan() {
	window.location="ModeratorBan.html";
}

function goLogin() {
	window.location="../Login.html";
}

function clearBox(elementID)
{
    document.getElementById(elementID).innerHTML = "";
}