<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class userModel extends CI_Model{
    public $table = 'tb_user';
    public $primaryKey = 'id';

    public function __construct(){
        parent::__construct();
    }
    function save_user($data){
        return $this->db->insert($this->table,$data);
    }
}