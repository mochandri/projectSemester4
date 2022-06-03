<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Api extends CI_Controller{

    public function __construct()
    {
        parent::__construct();
        $this->load->model('M_api');
    }

    public function login()
    {
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            if (isset($_POST['user']) && isset($_POST['pass'])) {

                $user_login = $this->M_api->proses_login($_POST['user'], $_POST['pass']);
                $result['username'] = null;

                if ($user_login->num_rows() == 1) {
                    $result['value'] = "1";
                    $result['pesan'] = "sukses login!";
                    $result['username'] = $user_login->row()->username;
                } else {
                    $result['value'] = "0";
                    $result['pesan'] = "username atau password salah!";
                } 
            } else {
                $result['value'] = "0";
                $result['pesan'] = "beberapa inputan masih kosong!";
            }
        } else {
            $result['value'] = "0";
            $result['pesan'] = "invalid request method!";
        }
        echo json_encode($result);
    }

    public function register()
    {
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            if (isset($_POST['username']) && isset($_POST['pass'])) {
                if ($this->M-api->cek_username_register($_POST['username'])->num_rows() == 0) {
                    $result['value'] = "0";
                    $result['pesan']   = "username tidak terdaftar!";
                } else if ($this->M_api->cek_if_register($_POST['username'])->num_rows() == 1) {
                    $result['value'] = "0";
                    $result['pesan']   = "username sudah ter-regristasi!!";
                }  else if ($this->M_api->cek_user_exist_register($_POST['user'])->num_rows() == 1) {
                    $result['value'] = "0";
                    $result['pesan']   = "username sudah terdaftar!!";
                } else {
                    $this->M_api->proses_register();
                    $result['value'] = '1';
                    $result['pesan'] = 'registrasi berhasil!';
                }
            } else {
                $result['value'] = '0';
                $result['pesan'] = 'beberapa inputan masih kosong!';
            }
        } else {
            $result['value'] = '0';
            $result['pesan'] = 'invalid request method!';
        }

        echo json_encode($result);
    }



}