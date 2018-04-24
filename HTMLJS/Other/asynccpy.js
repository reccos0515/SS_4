function loadXML (){

	var http = new XMLHttpRequest();

	http.onreadystatechange = function(){
		if(http.readyState == 4){
			console.log(http.response);
			document.getElementById("demo").innerHTML=http.response;
		}
	}
	http.open("GET", "http://proj-309-ss-4.cs.iastate.edu:9001/ben/report", true);
	http.send();
};