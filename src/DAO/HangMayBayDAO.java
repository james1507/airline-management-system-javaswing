/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DungChung.DungChung;
import Model.HangMayBaymdl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author conne
 */
public class HangMayBayDAO extends Connection.ConnectSQL {
     public void loadTable(JTable tbl) {
        try {
            String[] header = new String[]{"STT", "Mã hãng", "Tên hãng", "Quốc gia"};
            String sql = "select ROW_NUMBER() Over (Order by MaHang), * from HANGMAYBAY";
            new DungChung().statement(sql, tbl, header);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int them(HangMayBaymdl hmb) {
        try {
            String sql = "insert into HANGMAYBAY values(?, ?, ?)";
            Object[] obj = new Object[]{hmb.getMaHang(), hmb.getTenHang(), hmb.getQuocGia()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int sua(HangMayBaymdl hmb) {
        try {
            String sql = "update HANGMAYBAY set TenHang = ?, QuocGia = ? where MaHang = ?";
            Object[] obj = new Object[]{hmb.getTenHang(), hmb.getQuocGia(), hmb.getMaHang()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int xoa(HangMayBaymdl hmb) {
        try {
            String sql = "delete from HANGMAYBAY where MaHang = ?";
            Object[] obj = new Object[]{hmb.getMaHang()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void hienThi(JTable tbl, HangMayBaymdl hmb, int q) {
        hmb.setMaHang(String.valueOf(tbl.getValueAt(q, 1)));
        hmb.setTenHang(String.valueOf(tbl.getValueAt(q, 2)));
        hmb.setQuocGia(String.valueOf(tbl.getValueAt(q, 3)));
    }
    
    public HangMayBaymdl timHMBToDen(String cmnd) {
        try {
            String sql = "select * from HANGMAYBAY where MaHang = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cmnd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HangMayBaymdl lv = new HangMayBaymdl(rs.getString(1));
                return lv;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public void tim(JTable tbl, String ma) {
        try {
            String sql = "select ROW_NUMBER() Over (Order by MaHang), * from HANGMAYBAY where MaHang like ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + ma + "%");
            String[] header = new String[]{"STT", "Mã hãng", "Tên hãng", "Quốc gia"};
            DefaultTableModel model = new DefaultTableModel(header, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vector data = new Vector();
                data.add(rs.getString(1));
                data.add(rs.getString(2));
                data.add(rs.getString(3));
                data.add(rs.getString(4));
                model.addRow(data);
            }
            tbl.removeAll();
            tbl.setModel(model);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}
