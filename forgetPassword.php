<?php
$username = "s1876473";
$password = "Password062";
$database = "d1876473";
$link = mysqli_connect("127.0.0.1",$username,$password,$database);
$username1 = $_POST["userName"];
$password1 = $_POST["newPassword"];

if($result=mysqli_query($link,"update USERS set USERS_PASSWORD='$password1' where USERS_USERNAME ='$username1'"));
echo json_encode($result);
mysqli_close($link);
?>
