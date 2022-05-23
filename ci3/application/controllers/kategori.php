<?php

class kategori extends CI_Controller{
    public function tart(){
        $data['tart'] = $this->model_kategori->data_tart()->result();
        $this->load->view('templates/header');
        $this->load->view('templates/sidebar');
        $this->load->view('tart',$data);
        $this->load->view('templates/footer');
    }
    public function kering(){
        $data['kering'] = $this->model_kategori->data_kering()->result();
        $this->load->view('templates/header');
        $this->load->view('templates/sidebar');
        $this->load->view('kering',$data);
        $this->load->view('templates/footer');
    }
}