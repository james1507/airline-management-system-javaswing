/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import DAO.HoaDonDAO;
import DungChung.DungChung;
import Model.HanhKhachmdl;
import Model.HoaDonmdl;
import Model.NhanVienmdl;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Frame.HAND_CURSOR;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author conne
 */
public class HoaDon extends javax.swing.JFrame {

    /**
     * Creates new form HoaDon
     */
    
    String chucVu = DangNhap.vt;
    int showHide_CMND = 1;
    int showHide_MaNV = 1;
    int showHide_NgayLap = 1;
    int dong = -1;

    private void cbx_CMND() {
        ArrayList<JLabel> listLBL = new ArrayList<>();
        try {
            int cao = 3;
            ArrayList<HanhKhachmdl> arr_HK = new HoaDonDAO().layDS_HK();
            for (int i = 0; i < arr_HK.size(); i++) {
                JLabel lbl = new JLabel(arr_HK.get(i).getCmnd() + " - " + arr_HK.get(i).getHoTen());
                lbl.setSize(pnlCBX_CMND.getWidth(), 20);
                lbl.setLocation(10, cao);
                lbl.setName("lbl" + arr_HK.get(i).getCmnd());
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
                                txtCMND.setText(listLBL.get(i).getText());
                                pnlCBX_CMND.setVisible(false);
                                showHide_CMND++;
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
                pnlCBX_CMND.add(lbl);
                listLBL.add(lbl);
                cao += 20;
            }
            pnlCBX_CMND.setPreferredSize(new Dimension(sptCMND.getWidth(), listLBL.size() * 20 + 7));
            pnlCBX_CMND.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cbx_MaNV() {
        ArrayList<JLabel> listLBL = new ArrayList<>();
        try {
            int cao = 3;
            ArrayList<NhanVienmdl> arr_NV = new HoaDonDAO().layDS_NV();
            for (int i = 0; i < arr_NV.size(); i++) {
                JLabel lbl = new JLabel(arr_NV.get(i).getMaNhanVien() + " - " + arr_NV.get(i).getMatKhau());
                lbl.setSize(pnlCBX_MaNV.getWidth(), 20);
                lbl.setLocation(10, cao);
                lbl.setName("lbl" + arr_NV.get(i).getMaNhanVien());
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
                                txtMaNV.setText(listLBL.get(i).getText());
                                pnlCBX_MaNV.setVisible(false);
                                showHide_MaNV++;
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
                pnlCBX_MaNV.add(lbl);
                listLBL.add(lbl);
                cao += 20;
            }
            pnlCBX_MaNV.setPreferredSize(new Dimension(sptMaNV.getWidth(), listLBL.size() * 20 + 7));
            pnlCBX_MaNV.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showHideCBX_CMND() {
        showHide_CMND++;
        if (showHide_CMND % 2 == 0) {
            pnlCBX_CMND.setVisible(true);
        } else {
            pnlCBX_CMND.setVisible(false);
        }
    }

    private void showHideCBX_MaNV() {
        showHide_MaNV++;
        if (showHide_MaNV % 2 == 0) {
            pnlCBX_MaNV.setVisible(true);
        } else {
            pnlCBX_MaNV.setVisible(false);
        }
    }

    private void showHideDate_NgayLap() {
        showHide_NgayLap++;
        if (showHide_NgayLap % 2 == 0) {
            dcplNgayLap.setVisible(true);
        } else {
            dcplNgayLap.setVisible(false);
        }
    }

    private void an() {
        new DungChung().hideLBLError(new JLabel[]{lblLoiMaHD, lblLoiNhayLap, lblLoiCMND, lblLoiMaNV});
    }

    private boolean check() {
        JLabel[] lbl = new JLabel[]{lblLoiMaHD, lblLoiNhayLap, lblLoiCMND, lblLoiMaNV};
        JTextField[] txt = new JTextField[]{txtMaHD, txtNgayLap, txtCMND, txtMaNV};
        return new DungChung().check(lbl, txt);
    }

    private void moi() {
        new DungChung().reset(new JTextField[]{txtMaHD, txtNgayLap, txtCMND, txtMaNV});
        an();
        dong = -1;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        txtNgayLap.setText(String.valueOf(dtf.format(now)));
    }

    private void bang() {
        new HoaDonDAO().loadTable(tblHoaDon);
    }

    final void editColumnWidth() {
        int[] col = new int[]{40, 225, 225, 225, 225};
        new DungChung().editColumnWidth(col, tblHoaDon);
    }

    private void them() {
        if (check()) {
            String mahd = txtMaHD.getText().trim();
            String ngaylap = txtNgayLap.getText().trim();
            String[] str = txtCMND.getText().trim().split("-");
            String cmnd = str[0].trim();
            String[] str1 = txtMaNV.getText().trim().split("-");
            String manv = str1[0].trim();
            int kt = new HoaDonDAO().them(new HoaDonmdl(mahd, ngaylap, cmnd, manv));
            if (kt == 1) {
                bang();
                HoaDonmdl hk = new HoaDonDAO().timHDToDen(mahd);
                for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
                    String ma = String.valueOf(tblHoaDon.getValueAt(i, 1));
                    if (ma.equals(hk.getMaHoaDon())) {
                        tblHoaDon.setRowSelectionInterval(i, i);
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
        if (!txtMaNV.getText().isEmpty()) {
            String mahd = txtMaHD.getText().trim();
            String ngaylap = txtNgayLap.getText().trim();
            String[] str = txtCMND.getText().trim().split("-");
            String cmnd = str[0].trim();
            String[] str1 = txtMaNV.getText().trim().split("-");
            String manv = str1[0].trim();
            int kt = new HoaDonDAO().sua(new HoaDonmdl(mahd, ngaylap, cmnd, manv));
            if (kt == 1) {
                bang();
                tblHoaDon.setRowSelectionInterval(dong, dong);
                editColumnWidth();
                an();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void xoa() {
        if (dong >= 0) {
            int r = JOptionPane.showConfirmDialog(this, "Bạn cần xóa hóa đơn " + tblHoaDon.getValueAt(dong, 1) + "?", "Thông báo", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                String mahd = txtMaHD.getText().trim();
                int kt = new HoaDonDAO().xoa(new HoaDonmdl(mahd));
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
        HoaDonmdl hd = new HoaDonmdl();
        new HoaDonDAO().hienThi(tblHoaDon, hd, row);
        txtMaHD.setText(hd.getMaHoaDon());
        txtNgayLap.setText(hd.getNgayLap());
        txtCMND.setText(hd.getCmnd());
        txtMaNV.setText(hd.getMaNhanVien());
        txtMaHD.setEditable(false);
    }


    public HoaDon() {
        initComponents();
        setLocationRelativeTo(this);
        cbx_CMND();
        cbx_MaNV();
        dcplNgayLap.setVisible(false);
        new DungChung().transTXT(new JTextField[]{txtMaHD, txtNgayLap, txtCMND, txtMaNV});
        an();
        bang();
        editColumnWidth();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        txtNgayLap.setText(String.valueOf(dtf.format(now)));
        splTable.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
    }
    
    public HoaDon(String vt) {
        initComponents();
        setLocationRelativeTo(this);
        cbx_CMND();
        cbx_MaNV();
        dcplNgayLap.setVisible(false);
        new DungChung().transTXT(new JTextField[]{txtMaHD, txtNgayLap, txtCMND, txtMaNV});
        an();
        bang();
        editColumnWidth();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        txtNgayLap.setText(String.valueOf(dtf.format(now)));
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

        jPanel8 = new javax.swing.JPanel();
        pnlCBX_CMND = new javax.swing.JPanel();
        dcplNgayLap = new datechooser.beans.DateChooserPanel();
        pnlCBX_MaNV = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        txtMaHD = new javax.swing.JTextField();
        lblLoiMaHD = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        lblLoiNhayLap = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtCMND = new javax.swing.JTextField();
        sptCMND = new javax.swing.JSeparator();
        lblLoiCMND = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        sptMaNV = new javax.swing.JSeparator();
        lblLoiMaNV = new javax.swing.JLabel();
        btnMaNV = new javax.swing.JLabel();
        txtNgayLap = new javax.swing.JTextField();
        btnCMND = new javax.swing.JButton();
        btnNgayLap = new javax.swing.JButton();
        splTable = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnTroVe = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlCBX_CMND.setBackground(new java.awt.Color(194, 194, 194));

        javax.swing.GroupLayout pnlCBX_CMNDLayout = new javax.swing.GroupLayout(pnlCBX_CMND);
        pnlCBX_CMND.setLayout(pnlCBX_CMNDLayout);
        pnlCBX_CMNDLayout.setHorizontalGroup(
            pnlCBX_CMNDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        pnlCBX_CMNDLayout.setVerticalGroup(
            pnlCBX_CMNDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel8.add(pnlCBX_CMND, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        dcplNgayLap.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                dcplNgayLapOnSelectionChange(evt);
            }
        });
        jPanel8.add(dcplNgayLap, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 390, -1));

        pnlCBX_MaNV.setBackground(new java.awt.Color(194, 194, 194));

        javax.swing.GroupLayout pnlCBX_MaNVLayout = new javax.swing.GroupLayout(pnlCBX_MaNV);
        pnlCBX_MaNV.setLayout(pnlCBX_MaNVLayout);
        pnlCBX_MaNVLayout.setHorizontalGroup(
            pnlCBX_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        pnlCBX_MaNVLayout.setVerticalGroup(
            pnlCBX_MaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel8.add(pnlCBX_MaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 153, 153));
        jLabel15.setText("QUẢN LÝ HÓA ĐƠN");
        jPanel8.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 11, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 153, 153));
        jLabel16.setText("Tìm kiếm");
        jPanel8.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, -1, -1));

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiem.setForeground(new java.awt.Color(0, 153, 153));
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });
        jPanel8.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 196, -1));

        txtMaHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaHD.setForeground(new java.awt.Color(0, 153, 153));
        txtMaHD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtMaHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaHDKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMaHDKeyTyped(evt);
            }
        });
        jPanel8.add(txtMaHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 360, -1));

        lblLoiMaHD.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiMaHD.setText("Mã hóa đơn không chính xác");
        jPanel8.add(lblLoiMaHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 220, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 153, 153));
        jLabel17.setText("Mã hóa đơn");
        jPanel8.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 153, 153));
        jLabel18.setText("Ngày lập");
        jPanel8.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, -1, -1));
        jPanel8.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 390, 10));

        lblLoiNhayLap.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiNhayLap.setText("Chưa chọn ngày lập");
        jPanel8.add(lblLoiNhayLap, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 240, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 153, 153));
        jLabel19.setText("Chứng minh nhân dân");
        jPanel8.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        txtCMND.setEditable(false);
        txtCMND.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCMND.setForeground(new java.awt.Color(0, 153, 153));
        txtCMND.setBorder(null);
        txtCMND.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCMNDKeyReleased(evt);
            }
        });
        jPanel8.add(txtCMND, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 330, -1));
        jPanel8.add(sptCMND, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 360, 10));

        lblLoiCMND.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiCMND.setText("Chưa chọn chứng minh nhân dân");
        jPanel8.add(lblLoiCMND, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 230, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 153, 153));
        jLabel20.setText("Mã nhân viên");
        jPanel8.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, -1, -1));

        txtMaNV.setEditable(false);
        txtMaNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaNV.setForeground(new java.awt.Color(0, 153, 153));
        txtMaNV.setBorder(null);
        txtMaNV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaNVKeyReleased(evt);
            }
        });
        jPanel8.add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 360, -1));
        jPanel8.add(sptMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 390, 10));

        lblLoiMaNV.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiMaNV.setText("Chưa chọn mã nhân viên");
        jPanel8.add(lblLoiMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 190, -1));

        btnMaNV.setForeground(new java.awt.Color(0, 153, 153));
        btnMaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/down.png"))); // NOI18N
        btnMaNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMaNVMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMaNVMouseEntered(evt);
            }
        });
        jPanel8.add(btnMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 180, -1, -1));

        txtNgayLap.setEditable(false);
        txtNgayLap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNgayLap.setForeground(new java.awt.Color(0, 153, 153));
        txtNgayLap.setBorder(null);
        txtNgayLap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNgayLapKeyReleased(evt);
            }
        });
        jPanel8.add(txtNgayLap, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, 360, -1));

        btnCMND.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/down.png"))); // NOI18N
        btnCMND.setBorder(null);
        btnCMND.setBorderPainted(false);
        btnCMND.setContentAreaFilled(false);
        btnCMND.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCMND.setDefaultCapable(false);
        btnCMND.setFocusPainted(false);
        btnCMND.setFocusable(false);
        btnCMND.setIconTextGap(0);
        btnCMND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCMNDActionPerformed(evt);
            }
        });
        jPanel8.add(btnCMND, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, -1, -1));

        btnNgayLap.setForeground(new java.awt.Color(0, 153, 153));
        btnNgayLap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/down.png"))); // NOI18N
        btnNgayLap.setBorder(null);
        btnNgayLap.setBorderPainted(false);
        btnNgayLap.setContentAreaFilled(false);
        btnNgayLap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNgayLap.setDefaultCapable(false);
        btnNgayLap.setFocusPainted(false);
        btnNgayLap.setFocusable(false);
        btnNgayLap.setIconTextGap(0);
        btnNgayLap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayLapActionPerformed(evt);
            }
        });
        jPanel8.add(btnNgayLap, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 90, -1, -1));

        splTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                splTableMouseClicked(evt);
            }
        });

        tblHoaDon.setAutoCreateRowSorter(true);
        tblHoaDon.setForeground(new java.awt.Color(0, 153, 153));
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Ngày Lập", "CMND", "Mã Nhân Viên"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblHoaDon.setAutoscrolls(false);
        tblHoaDon.setFocusable(false);
        tblHoaDon.setGridColor(new java.awt.Color(255, 255, 255));
        tblHoaDon.setOpaque(false);
        tblHoaDon.setRequestFocusEnabled(false);
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        splTable.setViewportView(tblHoaDon);

        jPanel8.add(splTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 940, 250));

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
        jPanel8.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, -1, -1));

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
        jPanel8.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, -1, -1));

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
        jPanel8.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 270, -1, -1));

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
        jPanel8.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, -1, -1));

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
        jPanel8.add(btnTroVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 270, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dcplNgayLapOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dcplNgayLapOnSelectionChange
        try {
            String s = String.valueOf(dcplNgayLap.getSelection()).substring(1, String.valueOf(dcplNgayLap.getSelection()).length() - 1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String d = sdf.format(new Date(s));
            txtNgayLap.setText(d);
            dcplNgayLap.setVisible(false);
            showHide_NgayLap++;
        } catch (Exception e) {
            dcplNgayLap.setVisible(false);
            showHide_NgayLap++;
        }
    }//GEN-LAST:event_dcplNgayLapOnSelectionChange

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        new HoaDonDAO().tim(tblHoaDon, txtTimKiem.getText().trim());
        editColumnWidth();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtMaHDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaHDKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHDKeyReleased

    private void txtMaHDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaHDKeyTyped
        if (txtMaHD.getText().length() > 9) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMaHDKeyTyped

    private void txtCMNDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCMNDKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCMNDKeyReleased

    private void txtMaNVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaNVKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVKeyReleased

    private void btnMaNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaNVMouseClicked
        dcplNgayLap.setVisible(false);
        pnlCBX_CMND.setVisible(false);
        showHideCBX_MaNV();
    }//GEN-LAST:event_btnMaNVMouseClicked

    private void btnMaNVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaNVMouseEntered
        btnMaNV.setCursor(new Cursor(HAND_CURSOR));
    }//GEN-LAST:event_btnMaNVMouseEntered

    private void txtNgayLapKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNgayLapKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayLapKeyReleased

    private void btnCMNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCMNDActionPerformed
        dcplNgayLap.setVisible(false);
        pnlCBX_MaNV.setVisible(false);
        showHideCBX_CMND();
    }//GEN-LAST:event_btnCMNDActionPerformed

    private void btnNgayLapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayLapActionPerformed
        pnlCBX_MaNV.setVisible(false);
        pnlCBX_CMND.setVisible(false);
        showHideDate_NgayLap();
    }//GEN-LAST:event_btnNgayLapActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        txtTimKiem.setText(null);
        dong = tblHoaDon.getSelectedRow();
        hienThi(dong);
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void splTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_splTableMouseClicked
        // TODO add your handling code here:
        txtTimKiem.setText(null);
        dong = tblHoaDon.getSelectedRow();
        hienThi(dong);
    }//GEN-LAST:event_splTableMouseClicked

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
        if(tblHoaDon.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "Chọn nhân viên cần cập nhật.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }else{
            capNhat();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        moi();
        tblHoaDon.clearSelection();
    }//GEN-LAST:event_btnMoiActionPerformed

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
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCMND;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JLabel btnMaNV;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNgayLap;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTroVe;
    private javax.swing.JButton btnXoa;
    private datechooser.beans.DateChooserPanel dcplNgayLap;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JLabel lblLoiCMND;
    private javax.swing.JLabel lblLoiMaHD;
    private javax.swing.JLabel lblLoiMaNV;
    private javax.swing.JLabel lblLoiNhayLap;
    private javax.swing.JPanel pnlCBX_CMND;
    private javax.swing.JPanel pnlCBX_MaNV;
    private javax.swing.JScrollPane splTable;
    private javax.swing.JSeparator sptCMND;
    private javax.swing.JSeparator sptMaNV;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgayLap;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
