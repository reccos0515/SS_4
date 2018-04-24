function loadXML (){

	var http = new XMLHttpRequest();

	http.onreadystatechange = function(){
		if(http.readyState == 4){
			var response = JSON.stringify(http.responseText);
			document.getElementById("demo").innerHTML=response;
		}
	}
	http.open("GET", "http://localhost:8081/ben/users", true);
	http.send();
};