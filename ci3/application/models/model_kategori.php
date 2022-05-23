<?php

class model_kategori extends CI_Model{
    public function data_tart(){
        return $this->db->get_where("tb_barang",array('kategori'=>'kue tart'));
    }
    public function data_kering(){
        return $this->db->get_where("tb_barang",array('kategori'=>'kue kering'));
    }
}