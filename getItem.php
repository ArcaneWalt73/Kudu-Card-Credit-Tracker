<?php
$username = "s1965919";
$password = "ICTPass1670";
$database = "d1965919";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$output = array(); //stores all the items from different catogories
$arr1 = array();
$arr2 = array();
$arr3 = array();
$arr4 = array();

if ($result =mysqli_query($link,"select* from CLOTHES")){
        while($row=$result->fetch_assoc()){
                $arr1[] = $row;
        }
        array_push($output,$arr);
}
if ($result =mysqli_query($link,"select* from FOOD")){
        while($row=$result->fetch_assoc()){
                $arr2[] = $row;
        }
}
if ($result =mysqli_query($link,"select* from EVENTS")){
        while($row=$result->fetch_assoc()){
                $arr3[] = $row;
        }
}
if ($result =mysqli_query($link,"select* from STATIONARY")){
        while($row=$result->fetch_assoc()){
                $arr4[] = $row;
        }
}
array_push($output,$arr1,$arr2,$arr3,$arr4);
echo json_encode($output);
mysqli_close($link);
?>
