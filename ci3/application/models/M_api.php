<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class M_api extends CI_Model{
    public function proses_login($username, $password)
    {
        return $this->db->query("SELECT nama FROM tb_user WHERE username = '$username' AND password = '$password' ");
    }

    public function cek_username_register($username)
    {
        return $this->db->query("SELECT username FROM user WHERE username = '$username'");
    }
    public function cek_if_register($username)
    {
        return $this->db->query("SELECT = FROM user WHERE username = '$username' AND username IS NOT NULL");
    }
    public function cek_user_exist_register($user)
    {
        return $this->db->query("SELECT username FROM user WHERE username = '$user'");
    }
    public function proses_register()
    {
        $user       = $_POST['user'];
        $pass       = $_POST['pass'];
        $nama       = $_POST['nama'];

        $this->db->query("UPDATE user SET username = '$user', password = '$pass' WHERE username = '$username'");
    }



  
}