/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author conne
 */
public class MainDAO extends Connection.ConnectSQL {
      public String getHoTen(String ma){
        try {
            String sql = "select HoTen from NhanVien where MaNhanVien = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
