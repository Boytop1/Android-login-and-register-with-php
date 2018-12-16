<?php
    $target_path  = "/server/Web/upload/";//接收文件目录  

    $target_path = $target_path.($_FILES['file']['name']);

    $target_path = iconv("UTF-8","gb2312", $target_path);

	echo exec('whoami') ;

    if(move_uploaded_file($_FILES['file']['tmp_name'], $target_path)) {  

       echo "The file ".( $_FILES['file']['name'])." has been uploaded.";

    }else{  

       echo " upload error, please try again! error message: ".$_FILES['file']['error'];

    }
?>
