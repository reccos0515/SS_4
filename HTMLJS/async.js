function loadXML (){

	var http = new XMLHttpRequest();

	http.onreadystatechange = function(){
		if(http.readyState == 4){
			console.log(http.response);
			document.getElementById("demo").innerHTML=http.response;
		}
	}
	http.open("GET", "http://localhost:8081/ben/report", true);
	http.send();
};