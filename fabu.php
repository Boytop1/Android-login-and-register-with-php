<?php 
include("conn.php");

$json=file_get_contents('php://input');

$obj=json_decode($json);


$result= mysqli_query($mysqli, "INSERT INTO user(username,text) VALUES ('".$obj->{'UserName'}."', '".$obj->{'text'}."')");

header ( 'Content-type: application/json' );


if ($result ) {

	$response ["success"] = "1";

} else {

	$response ["success"] = "0";

}


echo json_encode ( $response );

mysqli_close($mysqli);
?>