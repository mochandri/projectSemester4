<?php

class Registrasi extends CI_Controller{
    public function __construct(){
        parent::__construct();
    }
    public function index()
    {
        $this->form_validation->set_rules('nama',' Nama','required', [
            'required' => 'Nama wajib diisi!'
        ]);
        $this->form_validation->set_rules('username',' Username','required', [
            'required' => 'Username wajib diisi!'
        ]);
        $this->form_validation->set_rules('password',' Password','required', [
            'required' => 'Password wajib diisi!'
        ]);
        $this->form_validation->set_rules('password2',' Confirm Password','matches[password]',[
            'matches'  => 'Password tidak cocok'
        ]);
        
        if($this->form_validation->run() == FALSE){
            $this->load->view('templates/header');
            $this->load->view('registrasi');
            $this->load->view('templates/footer');  
        } else {
            $data = array(
                'id'            => '',
                'nama'          => $this->input->post('nama'),
                'username'      => $this->input->post('username'),
                'password'      => $this->input->post('password'),
                'role_id'       => 2
            );

            $this->db->insert('tb_user',$data);
            redirect('auth/login');
        }

    }
}