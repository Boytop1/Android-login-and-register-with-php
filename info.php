<?php 

include("conn.php");

$result = mysqli_query ($mysqli ,"select id,text from user" );

header('content-type:application/json;charset=utf8');
class User 
{
public $id;
}

$data=array();

if($result){

 $i=0;



 while($row=mysqli_fetch_array($result)){
 	$i++;
    $users=new User();
    
 	$users->id=$row["id"];
 	$users->$i=$row["text"];

    $data[]=$users;


 }} 
$json = json_encode($data,JSON_UNESCAPED_UNICODE);//把数据转换为JSON数据.

echo $json;

 // echo json_encode($users);

 mysqli_close($mysqli);
?>