<?php
$username =  "s1876473";
$password = "Password062";
$database = "d1876473";
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

if($result = mysqli_query($link,"insert into USERS (USERNAME, FNAME, LNAME, PASSWORD, STUDENT_NO, ICAM_NO, CONTACT_NO, EMAIL_ADDRESS) values('$username1','$fname','$lname','$password1','$studentNo','$icamNo','$contact','$email')"));
$output["result"] = $result;
echo json_encode($result);
mysqli_close($link);
?>
