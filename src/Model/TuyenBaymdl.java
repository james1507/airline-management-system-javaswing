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
public class TuyenBaymdl {
    private String maTuyenBay;
    private String maSanBayDi;
    private String maSanBayDen;

    public TuyenBaymdl() {
    }

    public TuyenBaymdl(String maTuyenBay) {
        this.maTuyenBay = maTuyenBay;
    }

    public TuyenBaymdl(String maTuyenBay, String maSanBayDi, String maSanBayDen) {
        this.maTuyenBay = maTuyenBay;
        this.maSanBayDi = maSanBayDi;
        this.maSanBayDen = maSanBayDen;
    }

    public String getMaTuyenBay() {
        return maTuyenBay;
    }

    public void setMaTuyenBay(String maTuyenBay) {
        this.maTuyenBay = maTuyenBay;
    }

    public String getMaSanBayDi() {
        return maSanBayDi;
    }

    public void setMaSanBayDi(String maSanBayDi) {
        this.maSanBayDi = maSanBayDi;
    }

    public String getMaSanBayDen() {
        return maSanBayDen;
    }

    public void setMaSanBayDen(String maSanBayDen) {
        this.maSanBayDen = maSanBayDen;
    }
}
