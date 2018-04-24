
function checkCred() {
    var x, y, text;

    // Get the value of the input field with id="numb"
    x = document.getElementById("User").value;
    y = document.getElementById("Pass").value;

    // If x is Not a Number or less than one or greater than 10
    if (x==="conectarmod" && y==="modpass") {
        //text = "Moderator";
         window.location="modland.html";
         
    } else if (x==="conectaradmin" && y==="adminpass"){
       //text = "Admin";
        window.location="adland.html";
    } else if(x==="conectaradmin" || x==="conectarmod") {
        text = "Password incorrect";

    } else{
        text = "Username incorrect";
    }
    document.getElementById("demo").innerHTML = text;
};
