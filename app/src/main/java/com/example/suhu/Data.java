package com.example.suhu;

public class Data {
    private double suhu;
    private double alkohol;
    private String status;
    private String waktu;

    public Data() {
        // kosong, diperlukan untuk Firebase Realtime Database
    }

    public Data(double suhu, double alkohol, String status, String waktu) {
        this.suhu = suhu;
        this.alkohol = alkohol;
        this.status = status;
        this.waktu = waktu;
    }
    public double getSuhu() {
        return suhu;
    }

    public void setSuhu(double suhu) {
        this.suhu = suhu;
    }

    public double getAlkohol() {
        return alkohol;
    }

    public void setAlkohol(double alkohol) {
        this.alkohol = alkohol;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

}
