<?php
//获取客户端传入的json数据
include("conn.php");

//Post请求读取客户端数据，加@是防止系统打印其他信息，影响json读取
// @$username=$_POST['UserName'];
// @$password=$_POST['PassWord'];
$json=file_get_contents('php://input');

$obj=json_decode($json);

$result = mysqli_query ($mysqli ,"select * from user where username='".$obj->{'UserName'}."' and password='".$obj->{'PassWord'}."'" );

mysqli_close ( $mysqli );

$success = iconv("GB2312","UTF-8//IGNORE","login success");

$error = iconv("GB2312","UTF-8//IGNORE","login fail");

//将GB2312的中文转换成utf-8编码格式
header ( 'Content-type: application/json;charset=UTF-8' );

//返回结果集数，并设置提示信息
if (mysqli_num_rows ( $result ) < 1) {

	$response ["success"] = "1";

	$response ["message"] = $error;

} else {

	$response ["success"] = "0";

	$response ["message"] = $success;

}

//解决json_encode出现乱码的问题

foreach ( $response as $key => $value ) {

	$newData[$key] = urlencode( $value );

// 	$newData [$key] ['message'] = urlencode ( $value ['message'] );

}

echo urldecode ( json_encode ( $newData ) );

?>



