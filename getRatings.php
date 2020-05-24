<?php
$username = "s1965919";
$password = "ICTPass1670";
$database = "d1965919";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$output = array();
$studentNo = $_POST["studentNumber"]; // The username of the person who is trying to buy;
$id = $_POST["itemId"]; // id of the item
$category = $_POST["itemCategory"];

if($category == 'clothes'){
        if ($result =mysqli_query($link,"select AVG(RATE) from CLOTHES_RATINGS where CLOTHES_ID='$id'")){
                while($row=$result->fetch_assoc()){
                        $output[] = $row;
                }
        }
        echo json_encode($output);
}
else if ($category == 'food'){
        if ($result =mysqli_query($link,"select AVG(RATE) from FOOD_RATINGS where FOOD_ID='$id'")){
                while($row=$result->fetch_assoc()){
                        $output[] = $row;
                }
        }
        echo json_encode($output);
}

else if($category == 'stationary'){
        if ($result =mysqli_query($link,"select AVG(RATE) from STATIONARY_RATINGS where STATIONARY_ID='$id'")){
                while($row=$result->fetch_assoc()){
                        $output[] = $row;
                }
        }
        echo json_encode($output);
}
else if($category == 'event'){
        if ($result =mysqli_query($link,"select AVG(RATE) from EVENT_RATINGS where EVENTS_ID='$id'")){
                while($row=$result->fetch_assoc()){
                        $output[] = $row;
                }
        }
        echo json_encode($output);
}
mysqli_close($link);
?>
