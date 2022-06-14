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
public class HangVemdl {
    private String maHangVe;
    private String tenHangVe;

    public HangVemdl() {
    }

    public HangVemdl(String maHangVe) {
        this.maHangVe = maHangVe;
    }

    public HangVemdl(String maHangVe, String tenHangVe) {
        this.maHangVe = maHangVe;
        this.tenHangVe = tenHangVe;
    }

    public String getMaHangVe() {
        return maHangVe;
    }

    public void setMaHangVe(String maHangVe) {
        this.maHangVe = maHangVe;
    }

    public String getTenHangVe() {
        return tenHangVe;
    }

    public void setTenHangVe(String tenHangVe) {
        this.tenHangVe = tenHangVe;
    }
}
