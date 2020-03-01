<?php

	$username =  "s1965919";//The username for mysql

	$password = "ICTPass1670";

	$database = "d1965919";

	#echo "hello";

	$link = mysqli_connect("127.0.0.1", $username, $password, $database);

	$output = array();
	$username1 = $_POST["userName"]; //The username of the person who is trying to login

	if ($result = mysqli_query($link, "select * from USERS where USERS_USERNAME='$username1';"))
	{
		while ($row = $result -> fetch_assoc())
		{
	                $output[] = $row;
        	}
	}


	//$output["result"] = $result;//putting the results from sql into an array

	//if ($result = mysqli_query($link))

	echo json_encode($output);//converting the output array into a json object

	#msqli_close($link);
?>
