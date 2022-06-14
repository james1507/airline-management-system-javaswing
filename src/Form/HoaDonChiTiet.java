/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import DAO.HoaDonChiTietDAO;
import DTO.HoaDonChiTietDTO;
import DungChung.DungChung;
import Model.HoaDonChiTietmdl;
import Model.HoaDonmdl;
import Model.VeMayBaymdl;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Frame.HAND_CURSOR;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author conne
 */
public class HoaDonChiTiet extends javax.swing.JFrame {

    /**
     * Creates new form HoaDonChiTiet
     */
    
    String chucVu = DangNhap.vt;
    int showHide_MaHD = 1;
    int showHide_MaVe = 1;
    int dong = -1;

    private void cbx_MaHD() {
        ArrayList<JLabel> listLBL = new ArrayList<>();
        try {
            int cao = 3;
            ArrayList<HoaDonmdl> arr_HD = new HoaDonChiTietDAO().layDS_HD();
            for (int i = 0; i < arr_HD.size(); i++) {
                JLabel lbl = new JLabel(arr_HD.get(i).getMaHoaDon());
                lbl.setSize(pnlMaHoaDon.getWidth(), 20);
                lbl.setLocation(10, cao);
                lbl.setName("lbl" + arr_HD.get(i).getMaHoaDon());
                lbl.setForeground(Color.white);
                lbl.setFont(new Font("Segoe UI", 0, 14));
                lbl.setCursor(new Cursor(HAND_CURSOR));
                lbl.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String[] s = e.toString().split(" ");
                        String str = s[s.length - 1];
                        for (int i = 0; i < listLBL.size(); i++) {
                            if (listLBL.get(i).getName().equals(str)) {
                                txtMaHoaDon.setText(listLBL.get(i).getText());
                                pnlMaHoaDon.setVisible(false);
                                showHide_MaHD++;
                            }
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        String[] s = e.toString().split(" ");
                        String str = s[s.length - 1];
                        for (int i = 0; i < listLBL.size(); i++) {
                            if (listLBL.get(i).getName().equals(str)) {
                                listLBL.get(i).setForeground(new Color(55, 38, 91));
                            }
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        String[] s = e.toString().split(" ");
                        String str = s[s.length - 1];
                        for (int i = 0; i < listLBL.size(); i++) {
                            if (listLBL.get(i).getName().equals(str)) {
                                listLBL.get(i).setForeground(Color.white);
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                });
                pnlMaHoaDon.add(lbl);
                listLBL.add(lbl);
                cao += 20;
            }
            pnlMaHoaDon.setPreferredSize(new Dimension(sptMaHoaDon.getWidth(), listLBL.size() * 20 + 7));
            pnlMaHoaDon.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cbx_MaVe() {
        ArrayList<JLabel> listLBL = new ArrayList<>();
        try {
            int cao = 3;
            ArrayList<VeMayBaymdl> arr_Ve = new HoaDonChiTietDAO().layDS_Ve();
            for (int i = 0; i < arr_Ve.size(); i++) {
                JLabel lbl = new JLabel(arr_Ve.get(i).getMaVe());
                lbl.setSize(pnlMaVeMayBay.getWidth(), 20);
                lbl.setLocation(10, cao);
                lbl.setName("lbl" + arr_Ve.get(i).getMaVe());
                lbl.setForeground(Color.white);
                lbl.setFont(new Font("Segoe UI", 0, 14));
                lbl.setCursor(new Cursor(HAND_CURSOR));
                lbl.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String[] s = e.toString().split(" ");
                        String str = s[s.length - 1];
                        for (int i = 0; i < listLBL.size(); i++) {
                            if (listLBL.get(i).getName().equals(str)) {
                                txtMaVeMB.setText(listLBL.get(i).getText());
                                pnlMaVeMayBay.setVisible(false);
                                showHide_MaHD++;
                            }
                        }
                        new HoaDonChiTietDAO().donGia(txtMaVeMB, txtDonGia);
                        new HoaDonChiTietDAO().thanhTien(txtSoGheDat, txtDonGia, txtThanhTien);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        String[] s = e.toString().split(" ");
                        String str = s[s.length - 1];
                        for (int i = 0; i < listLBL.size(); i++) {
                            if (listLBL.get(i).getName().equals(str)) {
                                listLBL.get(i).setForeground(new Color(55, 38, 91));
                            }
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        String[] s = e.toString().split(" ");
                        String str = s[s.length - 1];
                        for (int i = 0; i < listLBL.size(); i++) {
                            if (listLBL.get(i).getName().equals(str)) {
                                listLBL.get(i).setForeground(Color.white);
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                });
                pnlMaVeMayBay.add(lbl);
                listLBL.add(lbl);
                cao += 20;
            }
            pnlMaVeMayBay.setPreferredSize(new Dimension(sptMaVeMayBay.getWidth(), listLBL.size() * 20 + 7));
            pnlMaVeMayBay.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showHideCBX_MaHD() {
        showHide_MaHD++;
        if (showHide_MaHD % 2 == 0) {
            pnlMaHoaDon.setVisible(true);
        } else {
            pnlMaHoaDon.setVisible(false);
        }
    }

    private void showHideCBX_MaVe() {
        showHide_MaVe++;
        if (showHide_MaVe % 2 == 0) {
            pnlMaVeMayBay.setVisible(true);
        } else {
            pnlMaVeMayBay.setVisible(false);
        }
    }

    private void an() {
        new DungChung().hideLBLError(new JLabel[]{lblLoiMaHD, lblLoiMaVe, lblLoiSoGheDat});
    }

    private boolean check() {
        JLabel[] lbl = new JLabel[]{lblLoiMaHD, lblLoiMaVe, lblLoiSoGheDat};
        JTextField[] txt = new JTextField[]{txtMaHoaDon, txtMaVeMB, txtSoGheDat};
        return new DungChung().check(lbl, txt);
    }

    void moi() {
        new DungChung().reset(new JTextField[]{txtMaHoaDon, txtMaVeMB, txtSoGheDat, txtDonGia, txtThanhTien});
        an();
        txtMaHoaDon.setEditable(false);
        dong = -1;
        txtDonGia.setText("0");
        txtThanhTien.setText("0");
        txtSoGheDat.setText("0");
    }

    private void bang() {
        new HoaDonChiTietDAO().loadTable(tblHoaDonChiTiet);
    }

    private void editColumnWidth() {
        int[] col = new int[]{40, 180, 180, 180, 180, 180};
        new DungChung().editColumnWidth(col, tblHoaDonChiTiet);
    }

    private void them() {
        if (check()) {
            String mahd = txtMaHoaDon.getText().trim();
            String maVe = txtMaVeMB.getText().trim();
            int soGheDat = Integer.parseInt(String.valueOf(txtSoGheDat.getText().trim()));
            int kt = new HoaDonChiTietDAO().them(new HoaDonChiTietmdl(mahd, maVe, soGheDat));
            if (kt == 1) {
                bang();
                HoaDonChiTietmdl hdct = new HoaDonChiTietDAO().timHDCTToDen(mahd, maVe);
                for (int i = 0; i < tblHoaDonChiTiet.getRowCount(); i++) {
                    String maHD = String.valueOf(tblHoaDonChiTiet.getValueAt(i, 1));
                    String mave = String.valueOf(tblHoaDonChiTiet.getValueAt(i, 2));
                    if (maHD.equals(hdct.getMaHoaDon()) && mave.equals(hdct.getMaVe())) {
                        tblHoaDonChiTiet.setRowSelectionInterval(i, i);
                    }
                }
                moi();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại! Trùng khóa chính.", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
        editColumnWidth();
    }

    private void capNhat() {
        if (!txtMaHoaDon.getText().isEmpty() && !txtMaVeMB.getText().isEmpty()) {
            String mahd = txtMaHoaDon.getText().trim();
            String maVe = txtMaVeMB.getText().trim();
            int soGheDat = Integer.parseInt(String.valueOf(txtSoGheDat.getText().trim()));
            int kt = new HoaDonChiTietDAO().sua(new HoaDonChiTietmdl(mahd, maVe, soGheDat));
            if (kt == 1) {
                bang();
                tblHoaDonChiTiet.setRowSelectionInterval(dong, dong);
                editColumnWidth();
                an();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void xoa() {
        if (dong >= 0) {
            int r = JOptionPane.showConfirmDialog(this, "Bạn cần xóa hóa đơn " + tblHoaDonChiTiet.getValueAt(dong, 1) + " có mã vé là " + tblHoaDonChiTiet.getValueAt(dong, 2) + "?", "Thông báo", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                String mahd = txtMaHoaDon.getText().trim();
                String maVe = txtMaVeMB.getText().trim();
                int kt = new HoaDonChiTietDAO().xoa(new HoaDonChiTietmdl(mahd, maVe));
                if (kt == 1) {
                    bang();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        editColumnWidth();
    }

    private void hienThi(int row) {
        HoaDonChiTietDTO hdct = new HoaDonChiTietDTO();
        new HoaDonChiTietDAO().hienThi(tblHoaDonChiTiet, hdct, row);
        txtMaHoaDon.setText(hdct.getHoaDonChiTietmdl().getMaHoaDon());
        txtMaVeMB.setText(hdct.getHoaDonChiTietmdl().getMaVe());
        txtSoGheDat.setText(String.valueOf(hdct.getHoaDonChiTietmdl().getSoGheDat()));
        txtDonGia.setText(String.valueOf(hdct.getDonGia()));
        txtThanhTien.setText(String.valueOf(hdct.getThanhTien()));
        txtMaHoaDon.setEditable(false);
        txtMaVeMB.setEditable(false);
    }

    public HoaDonChiTiet() {
        initComponents();
        setLocationRelativeTo(this);
        cbx_MaHD();
        cbx_MaVe();
        new DungChung().transTXT(new JTextField[]{txtMaHoaDon, txtMaVeMB, txtSoGheDat, txtDonGia, txtThanhTien});
        an();
        bang();
        editColumnWidth();
        splTable.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
    }

    public HoaDonChiTiet(String vt) {
        initComponents();
        setLocationRelativeTo(this);
        cbx_MaHD();
        cbx_MaVe();
        new DungChung().transTXT(new JTextField[]{txtMaHoaDon, txtMaVeMB, txtSoGheDat, txtDonGia, txtThanhTien});
        an();
        bang();
        editColumnWidth();
        chucVu = vt;
        splTable.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        pnlMaVeMayBay = new javax.swing.JPanel();
        pnlMaHoaDon = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        sptMaHoaDon = new javax.swing.JSeparator();
        txtMaHoaDon = new javax.swing.JTextField();
        lblLoiMaHD = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtMaVeMB = new javax.swing.JTextField();
        sptMaVeMayBay = new javax.swing.JSeparator();
        lblLoiMaVe = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtSoGheDat = new javax.swing.JTextField();
        lblLoiSoGheDat = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnMaHoaDon = new javax.swing.JButton();
        btnMaVeMayBay = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        splTable = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        btnTroVe = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlMaVeMayBay.setBackground(new java.awt.Color(194, 194, 194));

        javax.swing.GroupLayout pnlMaVeMayBayLayout = new javax.swing.GroupLayout(pnlMaVeMayBay);
        pnlMaVeMayBay.setLayout(pnlMaVeMayBayLayout);
        pnlMaVeMayBayLayout.setHorizontalGroup(
            pnlMaVeMayBayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        pnlMaVeMayBayLayout.setVerticalGroup(
            pnlMaVeMayBayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel5.add(pnlMaVeMayBay, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, -1, -1));

        pnlMaHoaDon.setBackground(new java.awt.Color(194, 194, 194));

        javax.swing.GroupLayout pnlMaHoaDonLayout = new javax.swing.GroupLayout(pnlMaHoaDon);
        pnlMaHoaDon.setLayout(pnlMaHoaDonLayout);
        pnlMaHoaDonLayout.setHorizontalGroup(
            pnlMaHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        pnlMaHoaDonLayout.setVerticalGroup(
            pnlMaHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel5.add(pnlMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 153));
        jLabel9.setText("QUẢN LÝ HÓA ĐƠN CHI TIẾT");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 11, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 153));
        jLabel10.setText("Tìm kiếm");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, -1, -1));

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiem.setForeground(new java.awt.Color(0, 153, 153));
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });
        jPanel5.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 196, -1));
        jPanel5.add(sptMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 360, 10));

        txtMaHoaDon.setEditable(false);
        txtMaHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaHoaDon.setForeground(new java.awt.Color(0, 153, 153));
        txtMaHoaDon.setBorder(null);
        txtMaHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaHoaDonKeyReleased(evt);
            }
        });
        jPanel5.add(txtMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 330, -1));

        lblLoiMaHD.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiMaHD.setText("Chưa chọn mã hóa đơn");
        jPanel5.add(lblLoiMaHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 220, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 153, 153));
        jLabel11.setText("Mã hóa đơn");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 153, 153));
        jLabel12.setText("Mã vé máy bay");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, -1, -1));

        txtMaVeMB.setEditable(false);
        txtMaVeMB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaVeMB.setForeground(new java.awt.Color(0, 153, 153));
        txtMaVeMB.setBorder(null);
        txtMaVeMB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaVeMBKeyReleased(evt);
            }
        });
        jPanel5.add(txtMaVeMB, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, 360, -1));
        jPanel5.add(sptMaVeMayBay, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 390, 10));

        lblLoiMaVe.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiMaVe.setText("Chưa chọn mã vé");
        jPanel5.add(lblLoiMaVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 240, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 153, 153));
        jLabel13.setText("ghế");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, -1, -1));

        txtSoGheDat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoGheDat.setForeground(new java.awt.Color(0, 153, 153));
        txtSoGheDat.setText("0");
        txtSoGheDat.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSoGheDat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoGheDatActionPerformed(evt);
            }
        });
        txtSoGheDat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoGheDatKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSoGheDatKeyTyped(evt);
            }
        });
        jPanel5.add(txtSoGheDat, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 360, -1));

        lblLoiSoGheDat.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiSoGheDat.setText("Chưa chọn số ghế đặt");
        jPanel5.add(lblLoiSoGheDat, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 190, -1));

        txtDonGia.setEditable(false);
        txtDonGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDonGia.setForeground(new java.awt.Color(0, 153, 153));
        txtDonGia.setText("0");
        txtDonGia.setBorder(null);
        txtDonGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDonGiaKeyReleased(evt);
            }
        });
        jPanel5.add(txtDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 340, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 153, 153));
        jLabel14.setText("Đơn giá");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 153, 153));
        jLabel15.setText("Thành tiền");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        txtThanhTien.setEditable(false);
        txtThanhTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtThanhTien.setForeground(new java.awt.Color(0, 153, 153));
        txtThanhTien.setText("0");
        txtThanhTien.setBorder(null);
        txtThanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThanhTienActionPerformed(evt);
            }
        });
        txtThanhTien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtThanhTienKeyReleased(evt);
            }
        });
        jPanel5.add(txtThanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 310, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 153, 153));
        jPanel5.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 390, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Triệu");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Triệu");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 180, -1, -1));

        jSeparator2.setForeground(new java.awt.Color(0, 153, 153));
        jPanel5.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 360, -1));

        btnMaHoaDon.setForeground(new java.awt.Color(0, 153, 153));
        btnMaHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/down.png"))); // NOI18N
        btnMaHoaDon.setBorder(null);
        btnMaHoaDon.setBorderPainted(false);
        btnMaHoaDon.setContentAreaFilled(false);
        btnMaHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMaHoaDon.setDefaultCapable(false);
        btnMaHoaDon.setFocusPainted(false);
        btnMaHoaDon.setFocusable(false);
        btnMaHoaDon.setIconTextGap(0);
        btnMaHoaDon.setInheritsPopupMenu(true);
        btnMaHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaHoaDonActionPerformed(evt);
            }
        });
        jPanel5.add(btnMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, -1, -1));

        btnMaVeMayBay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/down.png"))); // NOI18N
        btnMaVeMayBay.setBorder(null);
        btnMaVeMayBay.setBorderPainted(false);
        btnMaVeMayBay.setContentAreaFilled(false);
        btnMaVeMayBay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMaVeMayBay.setDefaultCapable(false);
        btnMaVeMayBay.setFocusPainted(false);
        btnMaVeMayBay.setFocusable(false);
        btnMaVeMayBay.setIconTextGap(0);
        btnMaVeMayBay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaVeMayBayActionPerformed(evt);
            }
        });
        jPanel5.add(btnMaVeMayBay, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 90, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 153, 153));
        jLabel16.setText("Số ghế đặt");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/bgButtonThemHover.png"))); // NOI18N
        btnThem.setBorderPainted(false);
        btnThem.setContentAreaFilled(false);
        btnThem.setDefaultCapable(false);
        btnThem.setIconTextGap(0);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel5.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, -1, -1));

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/bgButtonXoaHover.png"))); // NOI18N
        btnXoa.setBorderPainted(false);
        btnXoa.setContentAreaFilled(false);
        btnXoa.setDefaultCapable(false);
        btnXoa.setIconTextGap(0);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel5.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 320, -1, -1));

        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/bgButtonCapNhatHover.png"))); // NOI18N
        btnCapNhat.setBorderPainted(false);
        btnCapNhat.setContentAreaFilled(false);
        btnCapNhat.setDefaultCapable(false);
        btnCapNhat.setIconTextGap(0);
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        jPanel5.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 320, -1, -1));

        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/bgButtonMoiHover.png"))); // NOI18N
        btnMoi.setBorderPainted(false);
        btnMoi.setContentAreaFilled(false);
        btnMoi.setDefaultCapable(false);
        btnMoi.setIconTextGap(0);
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        jPanel5.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 320, -1, -1));

        splTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                splTableMouseClicked(evt);
            }
        });

        tblHoaDonChiTiet.setAutoCreateRowSorter(true);
        tblHoaDonChiTiet.setForeground(new java.awt.Color(0, 153, 153));
        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Mã Vé Máy Bay", "Ghế Đặt", "Đơn Giá", "Dịch Vụ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblHoaDonChiTiet.setAutoscrolls(false);
        tblHoaDonChiTiet.setFocusable(false);
        tblHoaDonChiTiet.setGridColor(new java.awt.Color(255, 255, 255));
        tblHoaDonChiTiet.setOpaque(false);
        tblHoaDonChiTiet.setRequestFocusEnabled(false);
        tblHoaDonChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChiTietMouseClicked(evt);
            }
        });
        splTable.setViewportView(tblHoaDonChiTiet);

        jPanel5.add(splTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, 940, 250));

        btnTroVe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/bgButtonTroVeMenu.png"))); // NOI18N
        btnTroVe.setBorderPainted(false);
        btnTroVe.setContentAreaFilled(false);
        btnTroVe.setDefaultCapable(false);
        btnTroVe.setIconTextGap(0);
        btnTroVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTroVeActionPerformed(evt);
            }
        });
        jPanel5.add(btnTroVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 320, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        new HoaDonChiTietDAO().tim(tblHoaDonChiTiet, txtTimKiem.getText().trim());
        editColumnWidth();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtMaHoaDonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaHoaDonKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHoaDonKeyReleased

    private void txtMaVeMBKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaVeMBKeyReleased

    }//GEN-LAST:event_txtMaVeMBKeyReleased

    private void txtSoGheDatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoGheDatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoGheDatActionPerformed

    private void txtSoGheDatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoGheDatKeyReleased
        new DungChung().xetSo(txtSoGheDat);
        new HoaDonChiTietDAO().thanhTien(txtSoGheDat, txtDonGia, txtThanhTien);
    }//GEN-LAST:event_txtSoGheDatKeyReleased

    private void txtSoGheDatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoGheDatKeyTyped
        if (txtSoGheDat.getText().length() > 2) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSoGheDatKeyTyped

    private void txtDonGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDonGiaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDonGiaKeyReleased

    private void txtThanhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThanhTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThanhTienActionPerformed

    private void txtThanhTienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtThanhTienKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThanhTienKeyReleased

    private void btnMaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaHoaDonActionPerformed
        showHideCBX_MaHD();
        pnlMaVeMayBay.setVisible(false);
    }//GEN-LAST:event_btnMaHoaDonActionPerformed

    private void btnMaVeMayBayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaVeMayBayActionPerformed
        showHideCBX_MaVe();
        pnlMaHoaDon.setVisible(false);
    }//GEN-LAST:event_btnMaVeMayBayActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        them();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if (chucVu.equals("Trưởng phòng")) {
            xoa();
        } else {
            JOptionPane.showMessageDialog(this, "Chỉ admin được phép xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if(tblHoaDonChiTiet.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "Chọn nhân viên cần cập nhật.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }else{
            capNhat();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        moi();
        tblHoaDonChiTiet.clearSelection();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void tblHoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChiTietMouseClicked
        // TODO add your handling code here:
        txtTimKiem.setText(null);
        dong = tblHoaDonChiTiet.getSelectedRow();
        hienThi(dong);
    }//GEN-LAST:event_tblHoaDonChiTietMouseClicked

    private void splTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_splTableMouseClicked
        // TODO add your handling code here:
        txtTimKiem.setText(null);
        dong = tblHoaDonChiTiet.getSelectedRow();
        hienThi(dong);
    }//GEN-LAST:event_splTableMouseClicked

    private void btnTroVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTroVeActionPerformed
        // TODO add your handling code here:
        new MainForm().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTroVeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HoaDonChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDonChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDonChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDonChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDonChiTiet().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnMaHoaDon;
    private javax.swing.JButton btnMaVeMayBay;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTroVe;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblLoiMaHD;
    private javax.swing.JLabel lblLoiMaVe;
    private javax.swing.JLabel lblLoiSoGheDat;
    private javax.swing.JPanel pnlMaHoaDon;
    private javax.swing.JPanel pnlMaVeMayBay;
    private javax.swing.JScrollPane splTable;
    private javax.swing.JSeparator sptMaHoaDon;
    private javax.swing.JSeparator sptMaVeMayBay;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaVeMB;
    private javax.swing.JTextField txtSoGheDat;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
