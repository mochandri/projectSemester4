package com.example.login;

import java.util.List;

import javax.xml.transform.Result;

public class MainModel {
    public class  result{
        private List<Result> result;

        public List<Result> getResult() {
            return result;
        }

        public void setResult(List<Result> result) {
            this.result = result;
        }

        private int id;
        private  String nama_brg;
        private  String keterangan;
        private  String kategori;
        private  int harga;
        private  int  Stok;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
            return Stok;
        }

        public void setStok(int stok) {
            Stok = stok;
        }

        @Override
        public String toString() {
            return "result{" +
                    "result=" + result +
                    ", id=" + id +
                    ", nama_brg='" + nama_brg + '\'' +
                    ", keterangan='" + keterangan + '\'' +
                    ", kategori='" + kategori + '\'' +
                    ", harga=" + harga +
                    ", Stok=" + Stok +
                    '}';
        }
    }

}
