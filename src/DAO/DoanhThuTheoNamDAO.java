/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.ConnectSQL;
import Model.DoanhThuTheoNammdl;
import java.awt.Cursor;
import java.awt.Font;
import static java.awt.Frame.HAND_CURSOR;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author conne
 */
public class DoanhThuTheoNamDAO extends ConnectSQL {
     public String checkHangVe(String ma) {
        try {
            String sql = "select MaHangVe from VEMAYBAY where MaVe = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public String checkLoaiVe(String ma) {
        try {
            String sql = "select MaLoaiVe from VEMAYBAY where MaVe = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    // Hạng vé
    // HV01: Thương gia * 1.05 (105%)
    // HV02: Phổ thông = đơn giá
    // Loại vé
    // LV01: khứ hồi * 1.9 (90%)
    // LV02: 1 chiều * 1
    public double getDoanhThu(int n) {
        try {
            String sql = "select VEMAYBAY.MaVe, YEAR(NgayLap), (GiaBan * SoGheDat)"
                    + " from VEMAYBAY, HOADONCHITIET, HOADON"
                    + " where HOADONCHITIET.MaVe = VEMAYBAY.MaVe and HOADON.MaHoaDon = HOADONCHITIET.MaHoaDon"
                    + " and YEAR(NgayLap) = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, n);
            ResultSet rs = ps.executeQuery();
            ArrayList<DoanhThuTheoNammdl> arr = new ArrayList<>();
            while (rs.next()) {
                String maVe = rs.getString(1);
                int nam = Integer.parseInt(String.valueOf(rs.getInt(2)));
                double thanhTien = Double.parseDouble(String.valueOf(rs.getDouble(3)));
                String hang = checkHangVe(maVe);
                String loai = checkLoaiVe(maVe);
                if (hang.equals("HV01") && loai.equals("LV01")) {
                    thanhTien = thanhTien * 1.05 * 1.9;
                } else if (hang.equals("HV01") && loai.equals("LV02")) {
                    thanhTien = thanhTien * 1.05;
                } else if (hang.equals("HV02") && loai.equals("LV01")) {
                    thanhTien = thanhTien * 1.9;
                } else if (hang.equals("HV02") && loai.equals("LV02")) {
                    thanhTien = thanhTien;
                }
                DoanhThuTheoNammdl dt = new DoanhThuTheoNammdl(maVe, nam, thanhTien);
                arr.add(dt);
            }

            double tongCong = 0.0;
            for (int i = 0; i < arr.size(); i++) {
                tongCong += arr.get(i).getThanhTien();
            }
            return Math.round(tongCong);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return 0.0;
    }

    public int getTongNV() {
        try {
            String sql = "select count(*) from NHANVIEN";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1) * 1847;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return 0;
    }

    public int getTongHK() {
        try {
            String sql = "select count(*) from HANHKHACH";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1) * 12433;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return 0;
    }

    public void loadTable_DoanhThu(JTable tbl, int nam) {
        try {
            String sql = "select ROW_NUMBER() Over (Order by hoadon.MaHoaDon), hoadon.MaHoaDon, NgayLap,"
                    + " GiaBan, SoGheDat,"
                    + " (GiaBan * SoGheDat)"
                    + " from VEMAYBAY, HOADON, HOADONCHITIET"
                    + " where HOADON.MaHoaDon = HOADONCHITIET.MaHoaDon"
                    + " and HOADONCHITIET.MaVe = VEMAYBAY.MaVe"
                    + " and Year(NgayLap) = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nam);
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

    public int getSL_Ve(int nam, String maHang, String maLoai) {
        try {
            int tong = 0;
            String sql = "select COUNT(VEMAYBAY.MaVe)"
                    + " from VEMAYBAY, HOADON, HOADONCHITIET"
                    + " where HOADON.MaHoaDon = HOADONCHITIET.MaHoaDon"
                    + " and HOADONCHITIET.MaVe = VEMAYBAY.MaVe"
                    + " and YEAR(NgayLap) = ?"
                    + " and MaHangVe = ?"
                    + " and MaLoaiVe = ?"
                    + " group by VEMAYBAY.MaVe";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nam);
            ps.setString(2, maHang);
            ps.setString(3, maLoai);
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

    public int getTongTien(int nam) {
        try {
            int tong = 0;
            String sql = "select hoadon.MaHoaDon, SUM(GiaBan * SoGheDat)"
                    + " from VEMAYBAY, HOADON, HOADONCHITIET"
                    + " where HOADON.MaHoaDon = HOADONCHITIET.MaHoaDon"
                    + " and HOADONCHITIET.MaVe = VEMAYBAY.MaVe"
                    + " and YEAR(NgayLap) = ?"
                    + " group by hoadon.MaHoaDon";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nam);
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
    
    
}
