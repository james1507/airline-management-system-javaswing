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
public class LoaiVemdl {
    private String maLoaiVe;
    private String tenLoaiVe;

    public LoaiVemdl() {
    }

    public LoaiVemdl(String maLoaiVe) {
        this.maLoaiVe = maLoaiVe;
    }

    public LoaiVemdl(String maLoaiVe, String tenLoaiVe) {
        this.maLoaiVe = maLoaiVe;
        this.tenLoaiVe = tenLoaiVe;
    }

    public String getMaLoaiVe() {
        return maLoaiVe;
    }

    public void setMaLoaiVe(String maLoaiVe) {
        this.maLoaiVe = maLoaiVe;
    }

    public String getTenLoaiVe() {
        return tenLoaiVe;
    }

    public void setTenLoaiVe(String tenLoaiVe) {
        this.tenLoaiVe = tenLoaiVe;
    }
}
