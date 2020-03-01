<?php
$username =  "s1965919";
$password = "ICTPass1670";
$database = "d1965919";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$output = array();
$username1 = $_POST["userName"];
$password1 = $_POST["password"];
$fname = $_POST["firstName"];
$lname = $_POST["lastName"];
$contact = $_POST["contact"];
$email = $_POST["emailAddress"];
$studentNo = $_POST["studentNumber"];
$icamNo = $_POST["icamNumber"];

if($result = mysqli_query($link,"insert into USERS values('$username1','$fname','$lname','$password1','$studentNo','$icamNo','$contact','$email')"));
$output["result"] = $result;
echo json_encode($result);
mysqli_close($link);

