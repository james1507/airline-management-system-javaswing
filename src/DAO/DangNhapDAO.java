/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.NhanVienmdl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author conne
 */
public class DangNhapDAO extends Connection.ConnectSQL {
    public String checkLogin(NhanVienmdl nv){
        try {
            String sql = "select * from NhanVien where MaNhanVien = ? and MatKhau = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getMaNhanVien());
            ps.setString(2, nv.getMatKhau());
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return rs.getString(3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
