<?php

defined('BASEPATH') OR exit('No direct script access allowed');

// This can be removed if you use __autoload() in config.php OR use Modular Extensions
/** @noinspection PhpIncludeInspection */
require APPPATH . '/libraries/REST_Controller.php';

// use namespace
use Restserver\Libraries\REST_Controller;

class user extends REST_Controller {

    function __construct()
    {
        // Construct the parent class
        parent::__construct();

        // Configure limits on our controller methods
        // Ensure you have created the 'limits' table and enabled 'limits' within application/config/rest.php
        $this->methods['users_get']['limit'] = 500; // 500 requests per hour per user/key
        $this->methods['users_post']['limit'] = 100; // 100 requests per hour per user/key
        $this->methods['users_delete']['limit'] = 50; // 50 requests per hour per user/key
    }

    public function index_get()
    {
        // Users from a data store e.g. database

        $id = $this->get('id');

        // If the id parameter doesn't exist return all the users

        if ($id === NULL)
        {
            $users = $this->db->get('tb_user')->result_array();
            // Check if the users data store contains users (in case the database result returns NULL)
            if ($users)
            {
                // Set the response and exit
                $this->response($users, REST_Controller::HTTP_OK); // OK (200) being the HTTP response code
            }
            else
            {
                // Set the response and exit
                $this->response([
                    'status' => FALSE,
                    'message' => 'No users were found'
                ], REST_Controller::HTTP_NOT_FOUND); // NOT_FOUND (404) being the HTTP response code
            }
        }

        // Find and return a single record for a particular user.
        else {

            // Validate the id.
            if ($id <= 0)
            {
                // Invalid id, set the response and exit.
                $this->response(NULL, REST_Controller::HTTP_BAD_REQUEST); // BAD_REQUEST (400) being the HTTP response code
            }

            // Get the user from the array, using the id as key for retrieval.
            // Usually a model is to be used for this.

            $this->db->where(array("id"=>$id));
            $users= $this->db->get("tb_user")->row_array();

            $this->response($users,REST_Controller::HTTP_OK);
        }
    }

    public function register_post()
    {
        // $this->some_model->update_user( ... );
        $data= [
            'nama' => $this->post('nama'),
            'username' => $this->post('username'),
            'password' => $this->post('password'),
            'role_id' => '2'
        
        ];
        $this->db->insert("tb_user",$data);


        $this->set_response($data, REST_Controller::HTTP_CREATED); // CREATED (201) being the HTTP response code
    }

    public function index_delete()
    {
        $id =  $this->delete('id');

       

        // $this->some_model->delete_something($id);
        $where = [
            'id' => $id,

        ];
        $this->db->delete("tb_user",$where);
        $message-array("status"=>"data berhasil dihapus");

        $this->set_response($message, REST_Controller::HTTP_NO_CONTENT); // NO_CONTENT (204) being the HTTP response code
    }

    public function index_put(){
        $where = array(
            "id"=>$this->put("id")
        );

        $data=array(
            "nama" => $this->put("nama"),
            "username" => $this->put("username" ),
            "password" => $this->put("password"),
            "role" => $this->put( "role"),
            
        );
        $this->db->update("tb_user",$data,$where);
        $this->set_response($data,REST_Controller::HTTP_CREATED);

    }
    public function login_post(){
        $data =[
            'username' => trim($this->input->post('username',TRUE)),
            'password' => trim($this->input->post('password',TRUE))
        ];
        $cek = $this->db->get_where('tb_user', array('username'=> $this->input->post('username',TRUE)));
        $row = $this->db->get_where('tb_user', $data)->row();

        if($cek->num_rows() >= 1){
            if($row){
                $result = [
                    'logged_in' => true,
                    'nama'      => $row->nama,
                    'username'  => $row->username,
                    'password'  => $row->password,
                ];

                $this->response(['error'=>false, 'message'=>'Login Berhasil','Result' => $result], 401);
            }else{
                $this->response(['error'=>true,'message'=>'Login Gagal'],401);
            } 
            
        }else{
            $this->response(['error'=>true,'message'=>'akun anda tidak terdaftar'],401);
        }
    }

}
