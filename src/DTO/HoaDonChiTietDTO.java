/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import Model.HoaDonChiTietmdl;

/**
 *
 * @author conne
 */
public class HoaDonChiTietDTO implements Serializable{
    private HoaDonChiTietmdl hoaDonChiTietmdl;
    private double donGia;
    private double thanhTien;

    public HoaDonChiTietDTO() {
    }

    public HoaDonChiTietDTO(HoaDonChiTietmdl hoaDonChiTietmdl) {
        this.hoaDonChiTietmdl = hoaDonChiTietmdl;
    }

    public HoaDonChiTietDTO(HoaDonChiTietmdl hoaDonChiTietmdl, double donGia, double thanhTien) {
        this.hoaDonChiTietmdl = hoaDonChiTietmdl;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public HoaDonChiTietmdl getHoaDonChiTietmdl() {
        return hoaDonChiTietmdl;
    }

    public void setHoaDonChiTietmdl(HoaDonChiTietmdl hoaDonChiTietmdl) {
        this.hoaDonChiTietmdl = hoaDonChiTietmdl;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
    
     
    
}
