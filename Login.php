<?php
	$username =  "s1876473";//The username for mysql
	$password = "Password062";
	$database = "d1876473";
	$link = mysqli_connect("127.0.0.1", $username, $password, $database);
	$output = array();
	
	$username1 = $_GET["userName"]; //The username of the person who is trying to login
	
	$result = mysqli_query($link, "select PASSWORD from USERS where USERNAME = $username1")
	$output["result"] = $result;//putting the results from sql into an array
	echo json_encode($output);//converting the output array into a json object
	msqli_close($ink);
?>