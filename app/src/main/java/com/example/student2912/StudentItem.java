package com.example.student2912;

public class StudentItem {
    String hoten;
    String mssv;
    String email;
    String ngaysinh;
    String diachi;

    public StudentItem(String mssv, String hoten, String ngaysinh,  String email,String diachi) {
        this.hoten = hoten;
        this.mssv = mssv;
        this.email = email;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
    }

    public StudentItem(String mssv,String hoten,  String email){
        this. hoten = hoten;
        this.email = email;
        this.mssv = mssv;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }


    public String getHoten() {
        return hoten;
    }

    public String getEmail() {
        return email;
    }

    public String getMssv() {
        return mssv;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }
}
