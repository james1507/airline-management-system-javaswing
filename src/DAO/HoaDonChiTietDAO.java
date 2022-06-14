/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.HoaDonChiTietDTO;
import DungChung.DungChung;
import Model.HoaDonChiTietmdl;
import Model.HoaDonmdl;
import Model.VeMayBaymdl;
import java.awt.Cursor;
import java.awt.Font;
import static java.awt.Frame.HAND_CURSOR;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author conne
 */
public class HoaDonChiTietDAO extends Connection.ConnectSQL {
    public ArrayList<HoaDonmdl> layDS_HD() {
        ArrayList<HoaDonmdl> arr = new ArrayList<>();
        try {
            String sql = "SELECT MaHoaDon FROM ( "
                    + "  SELECT MaHoaDon, ROW_NUMBER() OVER (ORDER BY MaHoaDon) as row FROM HOADON ) a"
                    + " WHERE row > 0 and row <= 20";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                HoaDonmdl hd = new HoaDonmdl(rs.getString(1));
                arr.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    public ArrayList<VeMayBaymdl> layDS_Ve() {
        ArrayList<VeMayBaymdl> arr = new ArrayList<>();
        try {
            String sql = "select MaVe from VEMAYBAY";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                VeMayBaymdl vmb = new VeMayBaymdl(rs.getString(1));
                arr.add(vmb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    String checkHangVe(String ma) {
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

    String checkLoaiVe(String ma) {
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

    float getDonGiaVe(String ma) {
        try {
            String sql = "select GiaBan from VEMAYBAY where MaVe = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return 0;
    }

    public void loadTable(JTable tbl) {
        //Phần hạng vé
        // + HV01: thương gia * 1.5 (105%)
        // + HV02: phổ thông = đơn giá

        // Phần loại vé
        // + LV01: khứ hồi * 1.9 (90%)
        // + LV02: 1 chiều * 1 (0%)
        try {
            String[] header = new String[]{"STT", "Mã hóa đơn", "Mã vé", "Số ghế đặt"};
            String sql = "select ROW_NUMBER() Over (Order by MaHoaDon), * from HOADONCHITIET";
            DefaultTableModel model = new DefaultTableModel(header, 0);
            tbl.setDefaultEditor(Object.class, null);
            tbl.getTableHeader().setCursor(new Cursor(HAND_CURSOR));
            tbl.getTableHeader().setFont(new Font("Segoe UI", 1, 13));
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Vector data = new Vector();
                for (int i = 0; i < header.length; i++) {
                    data.add(rs.getObject(i + 1));
                }
                model.addRow(data);
            }
            model.addColumn("Đơn giá (triệu)");
            for (int i = 0; i < model.getRowCount(); i++) {
                String maVe = String.valueOf(model.getValueAt(i, 2));
                String hangVe = String.valueOf(checkHangVe(maVe));
                String loaiVe = String.valueOf(checkLoaiVe(maVe));
                float donGia = getDonGiaVe(maVe);
                if (hangVe.equals("HV01") && loaiVe.equals("LV01")) {
                    model.setValueAt(donGia * 1.05 * 1.9, i, 4);
                } else if (hangVe.equals("HV01") && loaiVe.equals("LV02")) {
                    model.setValueAt(donGia * 1.05, i, 4);
                } else if (hangVe.equals("HV02") && loaiVe.equals("LV01")) {
                    model.setValueAt(donGia * 1.9 + new DichVuDAO().getTienDV(maVe), i, 4);
                } else if (hangVe.equals("HV02") && loaiVe.equals("LV02")) {
                    model.setValueAt(donGia + new DichVuDAO().getTienDV(maVe), i, 4);
                }
            }
            for (int i = 0; i < model.getRowCount(); i++) {
                model.setValueAt(Math.round(Double.parseDouble(String.valueOf(model.getValueAt(i, 4)))), i, 4);
            }
            model.addColumn("Thành tiền (triệu)");
            for (int i = 0; i < model.getRowCount(); i++) {
                int soGhe = Integer.parseInt(String.valueOf(model.getValueAt(i, 3)));
                double gia = Double.parseDouble(String.valueOf(model.getValueAt(i, 4)));
                model.setValueAt(soGhe * gia, i, 5);
            }
            tbl.setModel(model);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int them(HoaDonChiTietmdl hdct) {
        try {
            String sql = "insert into HOADONCHITIET values(?, ?, ?)";
            Object[] obj = new Object[]{hdct.getMaHoaDon(), hdct.getMaVe(), hdct.getSoGheDat()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int sua(HoaDonChiTietmdl hdct) {
        try {
            String sql = "update HOADONCHITIET set SoGheDat = ? where MaHoaDon = ? and MaVe = ?";
            Object[] obj = new Object[]{hdct.getSoGheDat(), hdct.getMaHoaDon(), hdct.getMaVe()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int xoa(HoaDonChiTietmdl hdct) {
        try {
            String sql = "delete from HOADONCHITIET where MaHoaDon = ? and MaVe = ?";
            Object[] obj = new Object[]{hdct.getMaHoaDon(), hdct.getMaVe()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void hienThi(JTable tbl, HoaDonChiTietDTO hdctdto, int q) {
        String maHD = String.valueOf(tbl.getValueAt(q, 1));
        String maVe = String.valueOf(tbl.getValueAt(q, 2));
        int soGheDat = Integer.parseInt(String.valueOf(tbl.getValueAt(q, 3)));
        hdctdto.setHoaDonChiTietmdl(new HoaDonChiTietmdl(maHD, maVe, soGheDat));
        hdctdto.setDonGia(Double.parseDouble(String.valueOf(tbl.getValueAt(q, 4))));
        hdctdto.setThanhTien(Double.parseDouble(String.valueOf(tbl.getValueAt(q, 5))));
    }

    public HoaDonChiTietmdl timHDCTToDen(String mahd, String mave) {
        try {
            String sql = "select * from HOADONCHITIET where MaHoaDon = ? and MaVe = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, mahd);
            ps.setString(2, mave);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HoaDonChiTietmdl hdct = new HoaDonChiTietmdl(rs.getString(1), rs.getString(2));
                return hdct;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public void tim(JTable tbl, String ma) {
        try {
            tbl.removeAll();
            String sql = "select ROW_NUMBER() Over (Order by MaHoaDon), * from HOADONCHITIET where MaHoaDon like ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + ma + "%");
            String[] header = new String[]{"STT", "Mã hóa đơn", "Mã vé", "Số ghế đặt"};
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
            model.addColumn("Đơn giá (triệu)");
            for (int i = 0; i < model.getRowCount(); i++) {
                String maVe = String.valueOf(model.getValueAt(i, 2));
                String hangVe = String.valueOf(checkHangVe(maVe));
                String loaiVe = String.valueOf(checkLoaiVe(maVe));
                float donGia = getDonGiaVe(maVe);
                if (hangVe.equals("HV01") && loaiVe.equals("LV01")) {
                    model.setValueAt(donGia * 1.5 * 1.9, i, 4);
                } else if (hangVe.equals("HV01") && loaiVe.equals("LV02")) {
                    model.setValueAt(donGia * 1.5, i, 4);
                } else if (hangVe.equals("HV02") && loaiVe.equals("LV01")) {
                    model.setValueAt(donGia * 1.1 * 1.9, i, 4);
                } else if (hangVe.equals("HV02") && loaiVe.equals("LV02")) {
                    model.setValueAt(donGia * 1.1, i, 4);
                }
            }
            for (int i = 0; i < model.getRowCount(); i++) {
                model.setValueAt(Math.round(Double.parseDouble(String.valueOf(model.getValueAt(i, 4)))), i, 4);
            }
            model.addColumn("Thành tiền (triệu)");
            for (int i = 0; i < model.getRowCount(); i++) {
                int soGhe = Integer.parseInt(String.valueOf(model.getValueAt(i, 3)));
                double gia = Double.parseDouble(String.valueOf(model.getValueAt(i, 4)));
                model.setValueAt(soGhe * gia, i, 5);
            }
            tbl.setModel(model);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void donGia(JTextField ma, JTextField txt) {
        try {
            String maVe = String.valueOf(ma.getText().trim());
            String hangVe = String.valueOf(checkHangVe(maVe));
            String loaiVe = String.valueOf(checkLoaiVe(maVe));
            float donGia = getDonGiaVe(maVe);
            if (hangVe.equals("HV01") && loaiVe.equals("LV01")) {
                txt.setText(String.valueOf(donGia * 1.5 * 1.9));
            } else if (hangVe.equals("HV01") && loaiVe.equals("LV02")) {
                txt.setText(String.valueOf(donGia * 1.5));
            } else if (hangVe.equals("HV02") && loaiVe.equals("LV01")) {
                txt.setText(String.valueOf(donGia * 1.1 * 1.9));
            } else if (hangVe.equals("HV02") && loaiVe.equals("LV02")) {
                txt.setText(String.valueOf(donGia * 1.1));
            }
            txt.setText(String.valueOf(Math.round(Double.parseDouble(String.valueOf(txt.getText().trim())))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void thanhTien(JTextField txtSoGheDat, JTextField txtDonGia, JTextField txtThanhTien) {
        try {
            if (!txtSoGheDat.getText().isEmpty()) {
                double soGhe = Double.parseDouble(String.valueOf(txtSoGheDat.getText().trim()));
                double donGia = Double.parseDouble(String.valueOf(txtDonGia.getText().trim()));
                double thanhTien = donGia * soGhe;
                txtThanhTien.setText(String.valueOf(thanhTien));
            } else {
                txtThanhTien.setText("0");
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}
