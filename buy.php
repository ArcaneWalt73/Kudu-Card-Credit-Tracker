<?php
$username = "s1965919";
$password = "ICTPass1670";
$database = "d1965919";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$output = array();
$studentNo = $_POST["studentNumber"]; // The username of the person who is trying to buy;
$amount = $_POST["productAmount"];

if ($result = mysqli_query($link, "select KUDU_BUCKS from CREDIT where STUDENT_NO='$studentNo';")){
        $current_Amount = $result->fetch_assoc()['KUDU_BUCKS'] - $amount; // the amount after payments
        if(mysqli_query($link,"update CREDIT set KUDU_BUCKS='$current_Amount' where STUDENT_NO='$student$
                echo json_enocode($current_Amount);
        }
        else{
                echo json_encode("false")
        }
        mysqli_close($link);
}

?>
