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
public class HoaDonmdl {
    private String maHoaDon;
    private String ngayLap;
    private String cmnd;
    private String maNhanVien;

    public HoaDonmdl() {
    }

    public HoaDonmdl(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public HoaDonmdl(String maHoaDon, String ngayLap, String cmnd, String maNhanVien) {
        this.maHoaDon = maHoaDon;
        this.ngayLap = ngayLap;
        this.cmnd = cmnd;
        this.maNhanVien = maNhanVien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
}
