/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author conne
 */
public class DoanhThuTheoNammdl {
     private String maVe;
    private int nam;
    private double thanhTien;

    public DoanhThuTheoNammdl() {
    }

    public DoanhThuTheoNammdl(String maVe, int nam) {
        this.maVe = maVe;
        this.nam = nam;
    }

    public DoanhThuTheoNammdl(String maVe, int nam, double thanhTien) {
        this.maVe = maVe;
        this.nam = nam;
        this.thanhTien = thanhTien;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
}
