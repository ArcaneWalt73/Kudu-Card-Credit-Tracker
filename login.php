<?php
$username =  "s1965919";//The username for mysql
$password = "ICTPass1670";
$database = "d1965919";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$output = array();
$studentNo = $_POST["studentNumber"]; //The username of the person who is trying to login
$password1 = $_POST["password"]
if ($result = mysqli_query($link, "select USERS_PASSWORD from USERS where USERS_STUDENT_NO='$studentNo';")){
	$hashed = $result->fetch_assoc()['USERS_PASSWORD'];
	if(!(empty($password1)) && password_verify($password1,$hashed)){
		echo json_encode("true");
	}
	else{
		echo json_encode("false");
	}
	msqli_close($link);
?>
