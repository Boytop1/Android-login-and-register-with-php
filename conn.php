<?php

$servername="localhost";
$username="root";
$password="root";

$mysqli=mysqli_connect($servername,$username,$password) or die("无法连接数据库");


//创建login数据库
// if (mysqli_query($mysqli,"CREATE DATABASE login"))
//   {
//   echo "Database created";
//   }
// else
//   {
//   echo "Error creating database: " . mysqli_error($mysqli);
//   }

    //选择login数据库建表
    mysqli_select_db($mysqli,"login");
    mysqli_query($mysqli,"set name 'utf-8'");

//在login数据库中创建user表
// $sql1 = "UPDATE TABLE user( 
// -- id int(12) not null auto_increment, 
// -- username varchar(20),
// -- password varchar(20),
// -- sex varchar(4) DEFAULT '保密' NOT NULL, 
// text varchar(200),
// -- primary key(id)
// )"; 

// if (!mysqli_query($mysqli,$sql1) ) { // 新建表

//         echo "Table create failed: (" . mysqli_errno($mysqli) . ") " . mysqli_error($mysqli);

//     } else {

//         echo "Table create success!<br>";

//     }
 
//在login数据库中创建user_info表
// $sql2 = "CREATE TABLE user_info( 
// id int(12) not null auto_increment, 
// name varchar(20),
// pwd varchar(20),
// sex varchar(4) DEFAULT '保密' NOT NULL, 
// email varchar(20),
// phone varchar(20),
// primary key(id)
// )"; 

// if (!mysqli_query($mysqli,$sql2) ) { // 新建表

//         echo "Table create failed: (" . mysqli_errno($mysqli) . ") " . mysqli_error($mysqli);

//     } else {

//         echo "Table create success!<br>";

//     }


?>