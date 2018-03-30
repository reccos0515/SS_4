
<?php

$myObj =  (object) [
	'userName' => json_decode($_POST["userName"], FALSE),
	'bio' => json_decode($_POST["bio"], FALSE)
];

$myJSON = json_encode($myObj);

echo $myJSON;

?>