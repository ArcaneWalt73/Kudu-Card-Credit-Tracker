<?php
$username =  "tshego";//The username for mysql
$password = "password123";
$database = "kudu_card_tracker";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$output = array();
$studentNo = $_POST["studentNumber"]; //The username of the person who is trying to login
$password1 = $_POST["password"]
if ($result = mysqli_query($link, "select * from STUDENTS where STUDENT_NO='$studentNo'")){
	if(password_verify($password,$result)){
		echo json_encode("true");
	}
	else{
		echo json_encode("false");
	}
	msqli_close($link);
?>
