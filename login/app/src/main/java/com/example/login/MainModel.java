package com.example.login;

import java.util.List;

import javax.xml.transform.Result;

public class MainModel {

        private List<Result> Result;

        public List<Result> getResult() {
            return Result;
        }

        public void setResult(List<Result> Result) {

            this.Result = Result;
        }
    public class  Result{

        private int id_brg;
        private  String nama_brg;
        private  String keterangan;
        private  String kategori;
        private  int harga;
        private  int  stok;
        private String gambar;
        private String gambar_url;

        public int getId_brg() {
            return id_brg;
        }

        public void setId_brg(int id_brg) {
            this.id_brg = id_brg;
        }

        public String getNama_brg() {
            return nama_brg;
        }

        public void setNama_brg(String nama_brg) {
            this.nama_brg = nama_brg;
        }

        public String getKeterangan() {
            return keterangan;
        }

        public void setKeterangan(String keterangan) {
            this.keterangan = keterangan;
        }

        public String getKategori() {
            return kategori;
        }

        public void setKategori(String kategori) {
            this.kategori = kategori;
        }

        public int getHarga() {
            return harga;
        }

        public void setHarga(int harga) {
            this.harga = harga;
        }

        public int getStok() {
            return stok;
        }

        public void setStok(int stok) {
            this.stok = stok;
        }

        public String getGambar() {
            return gambar;
        }

        public void setGambar(String gambar) {
            this.gambar = gambar;
        }

        public String getGambar_url() {
            return gambar_url;
        }

        public void setGambar_url(String gambar_url) {
            this.gambar_url = gambar_url;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "id_brg=" + id_brg +
                    ", nama_brg='" + nama_brg + '\'' +
                    ", keterangan='" + keterangan + '\'' +
                    ", kategori='" + kategori + '\'' +
                    ", harga=" + harga +
                    ", stok=" + stok +
                    ", gambar='" + gambar + '\'' +
                    ", gambar_url='" + gambar_url + '\'' +
                    '}';
        }
    }

}
