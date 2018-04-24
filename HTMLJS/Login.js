function checkCred() {
    var x, y, text;

    // Get the value of the input field with id="numb"
    x = document.getElementById("User").value;
    y = document.getElementById("Pass").value;
    if (x==="conectarmod" && y==="modpass") {
         window.location="Moderator/ModeratorHome.html";
    } else if (x==="conectaradmin" && y==="adminpass") {
        window.location="Admin/AdminHome.html";
    } else if(x==="conectaradmin" || x==="conectarmod") {
        document.getElementById("demo").innerHTML = "Password incorrect";
    } else {
        document.getElementById("demo").innerHTML = "Username incorrect";
    }
};
