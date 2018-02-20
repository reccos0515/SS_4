<?php

// $myObj = new \JSON_FORCE_OBJECT;

// $myObj->name = "John";
// $myObj->age = 30;
// $myObj->city = "New York";

// $myJSON = json_encode($myObj);
// echo $myJSON;
class User {
	public $name = "";
	public $age = "";
	public $city = "";
	
}

$user = new User();
$user->name = "John";
$user->age = "30";
$user->city = "New York";

echo json_encode($user);



?>