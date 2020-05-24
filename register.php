<?php
$username =  "s1965919";
$password = "ICTPass1670";
$database = "d1965919";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$output = array();
$password1 = $_POST["password"];
$fname = $_POST["firstName"];
$lname = $_POST["lastName"];
$contact = $_POST["contact"];
$email = $_POST["emailAddress"];
$studentNo = $_POST["studentNumber"];
$hash = password_hash($password1,PASSWORD_DEFAULT);

if($result = mysqli_query($link,"insert into USERS values('$studentNo','$hash','$fname','$lname','$contact','$email');"));
$output["result"] = $result;
echo json_encode($result);
mysqli_close($link);
?>
