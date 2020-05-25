<?php
$username = "s1965919";
$password = "ICTPass1670";
$database = "d1965919";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$output = array();
$studentNo = $_POST["studentNumber"]; // The username of the person who is trying to buy;
$rate = $_POST["numberStars"];
$id = $_POST["itemId"]; // id of the item
$category = $_POST["itemCategory"];

if($category =='clothes'){
        $result = mysqli_query($link, "insert into CLOTHES_RATINGS values('$id','$studentNo','$rate')");
        echo json_encode($result);
}
else if ($category == 'food'){
        $result = mysqli_query($link, "insert into FOOD_RATINGS values('$id','$studentNo','$rate')");
        echo json_encode($result);
}

else if($category == 'stationary'){
        $result = mysqli_query($link, "insert into STATIONARY_RATINGS values('$id','$studentNo','$rate')");
        echo json_encode($result);
}
else if($category == 'event'){
        $result = mysqli_query($link, "insert into EVENT_RATINGS values('$id','$studentNo','$rate')");
        echo json_encode($result);
}
mysqli_close($link);
?>
