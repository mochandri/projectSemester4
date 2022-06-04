<?php 
require_once 'connection.php';

if($con){
	$nama = $_POST['nama'];
	$username = $_POST['username'];
	$password = $_POST['password'];


	$insert = "INSERT INTO tb_user(nama, username,password) VALUES('$nama','$username','$password')";
	

	if($nama != "" && $username != "" && $password !=""){
		$result = mysqli_query($con, $insert);
		$response = array();

		if ($result) {
			array_push($response,array(
					'status'=>'OK'
				));
		}else{
		array_push($response,array(
			'status'=>'FAILED'
		));
	}
	}else{
		array_push($response,array(
			'status'=>'FAILED'
		));
	}

}else{
	array_push($response,array(
		'status'=>"FAIL"
	));
}

echo json_encode(array("server_response"=>$response));
mysqli_close($con);



?>