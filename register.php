<?php
$username =  "tshego";
$password = "password123";
$database = "kudu_card_tracker";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$output = array();
$password1 = $_POST["password"];
$fname = $_POST["firstName"];
$lname = $_POST["lastName"];
$contact = $_POST["contact"];
$email = $_POST["emailAddress"];
$studentNo = $_POST["studentNumber"];
$hash = password_hash($password1,PASSWORD_DEFAULT);

if($result = mysqli_query($link,"insert into STUDENTS values('$studentNo','$fname','$lname','$hash','$email','$contact')"));
$output["result"] = $result;
echo json_encode($result);
mysqli_close($link);
?>
