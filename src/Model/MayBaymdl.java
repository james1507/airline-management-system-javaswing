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
public class MayBaymdl {
     private String maMaybay;
    private String maHang;

    public MayBaymdl() {
    }

    public MayBaymdl(String maMaybay) {
        this.maMaybay = maMaybay;
    }

    public MayBaymdl(String maMaybay, String maHang) {
        this.maMaybay = maMaybay;
        this.maHang = maHang;
    }

    public String getMaMaybay() {
        return maMaybay;
    }

    public void setMaMaybay(String maMaybay) {
        this.maMaybay = maMaybay;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }
}
