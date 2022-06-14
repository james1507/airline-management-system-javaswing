/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.ConnectSQL;
import java.awt.Cursor;
import java.awt.Font;
import static java.awt.Frame.HAND_CURSOR;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author conne
 */
public class DoanhThuTheoThangDAO extends ConnectSQL {
     private int[] checkQuy(int quy) {
        int[] so = new int[3];
        switch (quy) {
            case 1:
                // 1, 2, 3
                so[0] = quy;
                so[1] = quy + 1;
                so[2] = quy + 2;
                break;
            case 2:
                //4, 5, 6
                so[0] = quy + 2;
                so[1] = quy + 3;
                so[2] = quy + 4;
                break;
            case 3:
                //7, 8, 9
                so[0] = quy + 4;
                so[1] = quy + 5;
                so[2] = quy + 6;
                break;
            case 4:
                //10, 11, 12
                so[0] = quy + 6;
                so[1] = quy + 7;
                so[2] = quy + 8;
                break;
        }
        return so;
    }

    public void loadTable_DoanhThu(JTable tbl, int quy, int nam) {
        try {
            int[] so = checkQuy(quy);
            String sql = "select ROW_NUMBER() Over (Order by hoadon.MaHoaDon), hoadon.MaHoaDon, NgayLap,"
                    + " GiaBan, SoGheDat,"
                    + " (GiaBan * SoGheDat)"
                    + " from VEMAYBAY, HOADON, HOADONCHITIET"
                    + " where HOADON.MaHoaDon = HOADONCHITIET.MaHoaDon"
                    + " and HOADONCHITIET.MaVe = VEMAYBAY.MaVe"
                    + " and Month(NgayLap) in (?, ?, ?)"
                    + " and Year(NgayLap) = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, so[0]);
            ps.setInt(2, so[1]);
            ps.setInt(3, so[2]);
            ps.setInt(4, nam);
            String[] header = new String[]{"STT", "Mã hóa đơn", "Ngày lập", "Giá bán (triệu)", "Số ghế đặt", "Thành tiền (triệu)"};
            DefaultTableModel model = new DefaultTableModel(header, 0);
            tbl.setDefaultEditor(Object.class, null);
            tbl.getTableHeader().setCursor(new Cursor(HAND_CURSOR));
            tbl.getTableHeader().setFont(new Font("Segoe UI", 1, 13));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vector data = new Vector();
                data.add(rs.getString(1));
                data.add(rs.getString(2));
                data.add(rs.getString(3));
                data.add(rs.getDouble(4));
                data.add(rs.getInt(5));
                data.add(rs.getDouble(6));
                model.addRow(data);
            }
            tbl.removeAll();
            tbl.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTien(int quy, int nam) {
        try {
            int tong = 0;
            String sql = "select hoadon.MaHoaDon, SUM(GiaBan * SoGheDat)"
                    + " from VEMAYBAY, HOADON, HOADONCHITIET"
                    + " where HOADON.MaHoaDon = HOADONCHITIET.MaHoaDon"
                    + " and HOADONCHITIET.MaVe = VEMAYBAY.MaVe"
                    + " and MONTH(NgayLap) = ?"
                    + " and YEAR(NgayLap) = ?"
                    + " group by hoadon.MaHoaDon";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, quy);
            ps.setInt(2, nam);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tong += rs.getInt(2);
            }
            return tong;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getSL_Ve(int quy, int nam, String maHang, String maLoai) {
        try {
            int[] so = checkQuy(quy);
            int tong = 0;
            String sql = "select COUNT(VEMAYBAY.MaVe)"
                    + " from VEMAYBAY, HOADON, HOADONCHITIET"
                    + " where HOADON.MaHoaDon = HOADONCHITIET.MaHoaDon"
                    + " and HOADONCHITIET.MaVe = VEMAYBAY.MaVe"
                    + " and MONTH(NgayLap) in (?, ?, ?)"
                    + " and YEAR(NgayLap) = ?"
                    + " and MaHangVe = ?"
                    + " and MaLoaiVe = ?"
                    + " group by VEMAYBAY.MaVe";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, so[0]);
            ps.setInt(2, so[1]);
            ps.setInt(3, so[2]);
            ps.setInt(4, nam);
            ps.setString(5, maHang);
            ps.setString(6, maLoai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tong += rs.getInt(1);
            }
            return tong;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTongTien(int quy, int nam) {
        try {
            int tong = 0;
            int[] so = checkQuy(quy);
            for (int i = 0; i < 3; i++) {
                String sql = "select hoadon.MaHoaDon, SUM(GiaBan * SoGheDat)"
                        + " from VEMAYBAY, HOADON, HOADONCHITIET"
                        + " where HOADON.MaHoaDon = HOADONCHITIET.MaHoaDon"
                        + " and HOADONCHITIET.MaVe = VEMAYBAY.MaVe"
                        + " and MONTH(NgayLap) = ?"
                        + " and YEAR(NgayLap) = ?"
                        + " group by hoadon.MaHoaDon";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, so[i]);
                ps.setInt(2, nam);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    tong += rs.getInt(2);
                }
            }
            return tong;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    private int getTT(int thang, int nam) {
        try {
            int tong = 0;
            String sql = "select sum(GiaBan * SoGheDat) as thanhtien"
                    + " from HOADONCHITIET, HOADON, VEMAYBAY"
                    + " where HOADONCHITIET.MaHoaDon = HOADON.MaHoaDon"
                    + " and HOADONCHITIET.MaVe = VEMAYBAY.MaVe"
                    + " and MONTH(NgayLap) = ?"
                    + " and YEAR(NgayLap) = ?"
                    + " group by HOADON.MaHoaDon";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, thang);
            ps.setInt(2, nam);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tong += rs.getInt(1);
            }
            return tong;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    
    
}
