<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class M_api extends CI_Model{
    public function proses_login($user, $pass)
    {
        return $this->db->query("SELECT username FROM tb_user WHERE username = '$user' AND password = MD5('$pass')");
    }

    public function cek_username_register($username)
    {
        return $this->db->query("SELECT username FROM tb_user WHERE username = '$username'");
    }
    public function cek_if_register($username)
    {
        return $this->db->query("SELECT = FROM tb_user WHERE username = '$username' AND username IS NOT NULL");
    }
    public function cek_user_exist_register($user)
    {
        return $this->db->query("SELECT username FROM tb_user WHERE username = '$user'");
    }
    public function proses_register()
    {
        $user       = $_POST['user'];
        $pass       = md5($_POST['pass']);
        $username   = $_POST['username'];

        $this->db->query("UPDATE user SET username = '$user', password = '$pass' WHERE username = '$username'");
    }



  
}