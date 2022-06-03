<?php
defined('BASEPATH') OR exit('No direct script access allowed');
require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class akun extends REST_Controller{
    function __construct(){
        parent::__construct();
        $this->load->model('userModel');
        $this->model = $this->userModel;
    }

    public function Register_post(){
        $data = [
            'role_id' => '2',
            'username' => $this->input->post('username',TRUE),
            'password'=> $this->input->post('password',TRUE),
            'nama'=> $this->input->post('nama', true),
        ];

        $response = $this->userModel->save_user($data);

        if($response){
            $this->response(['error' => false, 'message' => 'Register Berhasil','Result'=>$data], 200);
        }else{
            $this->response(['error' => false, 'message' => 'Register Gagal'], 200);
        }
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
}