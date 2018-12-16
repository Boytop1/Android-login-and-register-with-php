<?php

include("conn.php");


$json = file_get_contents ( 'php://input' );

$obj = json_decode ( $json );

$result= mysqli_query($mysqli, "INSERT INTO user(username,password) VALUES ('".$obj->{'UserName'}."', '".$obj->{'PassWord1'}."')");


header ( 'Content-type: application/json' );

if ($result ) {

	$response ["success"] = "1";


} else {

	$response ["success"] = "0";


}


echo json_encode ( $response );

mysqli_close($mysqli);

?>


