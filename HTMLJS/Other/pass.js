
function checkCred() {
    var x, y, text;

    // Get the value of the input field with id="numb"
    x = document.getElementById("User").value;
    y = document.getElementById("Pass").value;

    // If x is Not a Number or less than one or greater than 10
    if (x==="conectarmod" && y==="modpass") {
        //text = "Moderator";
         window.location="modland.html";
         
    } else if (x==="conectaradmin" && y==="adminpass") {
       //text = "Admin";
        window.location="AdminHome.html";
    } else if(x==="conectaradmin" || x==="conectarmod") {
        document.getElementById("demo").innerHTML = "Password incorrect";

    } else {
        document.getElementById("demo").innerHTML = "Username incorrect";
    }
};
