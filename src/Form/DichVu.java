/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import DAO.DichVuDAO;
import DungChung.DungChung;
import Model.DichVumdl;
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
public class DichVu extends javax.swing.JFrame {

    /**
     * Creates new form DichVu
     */
    
    String chucVu = DangNhap.vt;
    int showHide = 1;
    int dong = -1;

    private void cbx() {
        ArrayList<JLabel> listLBL = new ArrayList<>();
        try {
            int cao = 3;
            ArrayList<VeMayBaymdl> arr_Ve = new DichVuDAO().layDS();
            for (int i = 0; i < arr_Ve.size(); i++) {
                JLabel lbl = new JLabel(arr_Ve.get(i).getMaVe());
                lbl.setSize(pnlVeMB.getWidth(), 20);
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
                                txtMaVe.setText(listLBL.get(i).getText());
                                pnlVeMB.setVisible(false);
                                showHide++;
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
                pnlVeMB.add(lbl);
                listLBL.add(lbl);
                cao += 20;
            }
            pnlVeMB.setPreferredSize(new Dimension(sptMaVe.getWidth(), listLBL.size() * 20 + 7));
            pnlVeMB.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showHideCBX() {
        showHide++;
        if (showHide % 2 == 0) {
            pnlVeMB.setVisible(true);
        } else {
            pnlVeMB.setVisible(false);
        }
    }

    private void an() {
        new DungChung().hideLBLError(new JLabel[]{lblLoiMaDV, lblLoiMaVe, lblLoiTenDV, lblLoiGiaDV});
    }

    private boolean check() {
        JLabel[] lbl = new JLabel[]{lblLoiMaDV, lblLoiMaVe, lblLoiTenDV, lblLoiGiaDV};
        JTextField[] txt = new JTextField[]{txtMaDichVu, txtMaVe, txtMaDichVu, txtGiaDV};
        return new DungChung().check(lbl, txt);
    }

    private void moi() {
        new DungChung().reset(new JTextField[]{txtMaDichVu, txtMaVe, txtMaDichVu, txtGiaDV});
        an();
        dong = -1;
    }

    private void bang() {
        new DichVuDAO().loadTable(tblDichVu);
    }

    private void editColumnWidth() {
        int[] col = new int[]{40, 225, 225, 225, 225};
        new DungChung().editColumnWidth(col, tblDichVu);
    }

    private void them() {
        if (check()) {
            String ma = txtMaDichVu.getText().trim();
            String mave = txtMaVe.getText().trim();
            String ten = txtMaDichVu.getText().trim();
            String gia = txtGiaDV.getText().trim();
            float giaf = Float.parseFloat(gia);
            int kt = new DichVuDAO().them(new DichVumdl(ma, mave, ten, giaf));
            if (kt == 1) {
                bang();
                DichVumdl dv = new DichVuDAO().timDVToDen(ma);
                for (int i = 0; i < tblDichVu.getRowCount(); i++) {
                    String id = String.valueOf(tblDichVu.getValueAt(i, 1));
                    if (id.equals(dv.getMaDichVu())) {
                        tblDichVu.setRowSelectionInterval(i, i);
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
        if (!txtMaDichVu.getText().isEmpty() && !txtMaVe.getText().isEmpty()) {
            String ma = txtMaDichVu.getText().trim();
            String mave = txtMaVe.getText().trim();
            String ten = txtMaDichVu.getText().trim();
            String gia = txtGiaDV.getText().trim();
            float giaf = Float.parseFloat(gia);
            int kt = new DichVuDAO().sua(new DichVumdl(ma, mave, ten, giaf));
            if (kt == 1) {
                bang();
                tblDichVu.setRowSelectionInterval(dong, dong);
                an();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
        editColumnWidth();
    }

    private void xoa() {
        if (dong >= 0) {
            int r = JOptionPane.showConfirmDialog(this, "Bạn cần xóa dịch vụ " + tblDichVu.getValueAt(dong, 3) + " của vé " + tblDichVu.getValueAt(dong, 2) + "?", "Thông báo", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                String ma = txtMaDichVu.getText().trim();
                int kt = new DichVuDAO().xoa(new DichVumdl(ma));
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
        DichVumdl sb = new DichVumdl();
        new DichVuDAO().hienThi(tblDichVu, sb, row);
        txtMaDichVu.setText(sb.getMaDichVu());
        txtMaVe.setText(sb.getMaVe());
        txtTenDV.setText(sb.getTenDichVu());
        txtGiaDV.setText(sb.getGiaDichVu() + "");
        txtMaDichVu.setEditable(false);
    }

    public DichVu() {
        initComponents();
        setLocationRelativeTo(this);
        cbx();
        new DungChung().transTXT(new JTextField[]{txtMaDichVu, txtMaVe, txtMaDichVu, txtGiaDV, txtTimKiem});
        an();
        bang();
        editColumnWidth();
        splTable.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
    }
    
    public DichVu(String vt) {
        initComponents();
        setLocationRelativeTo(this);
        cbx();
        new DungChung().transTXT(new JTextField[]{txtMaDichVu, txtMaVe, txtMaDichVu, txtGiaDV, txtTimKiem});
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

        jPanel9 = new javax.swing.JPanel();
        pnlVeMB = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        sptMaVe = new javax.swing.JSeparator();
        lblLoiMaVe = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtMaVe = new javax.swing.JTextField();
        jSeparator21 = new javax.swing.JSeparator();
        lblLoiGiaDV = new javax.swing.JLabel();
        txtGiaDV = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnCBXMaVe = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        splTable = new javax.swing.JScrollPane();
        tblDichVu = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        txtMaDichVu = new javax.swing.JTextField();
        lblLoiMaDV = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtTenDV = new javax.swing.JTextField();
        lblLoiTenDV = new javax.swing.JLabel();
        btnTroVe = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlVeMB.setBackground(new java.awt.Color(194, 194, 194));
        pnlVeMB.setForeground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout pnlVeMBLayout = new javax.swing.GroupLayout(pnlVeMB);
        pnlVeMB.setLayout(pnlVeMBLayout);
        pnlVeMBLayout.setHorizontalGroup(
            pnlVeMBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        pnlVeMBLayout.setVerticalGroup(
            pnlVeMBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel9.add(pnlVeMB, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 153, 153));
        jLabel17.setText("QUẢN LÝ DỊCH VỤ");
        jPanel9.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 11, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 153, 153));
        jLabel18.setText("Tìm kiếm");
        jPanel9.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, -1, -1));

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiem.setForeground(new java.awt.Color(0, 153, 153));
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });
        jPanel9.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 196, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 153, 153));
        jLabel20.setText("Mã vé máy bay");
        jPanel9.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, -1, -1));
        jPanel9.add(sptMaVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 390, 10));

        lblLoiMaVe.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiMaVe.setText("Chưa chọn mã vé máy bay");
        jPanel9.add(lblLoiMaVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 240, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 153, 153));
        jLabel22.setText("Giá dịch vụ");
        jPanel9.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, -1, -1));

        txtMaVe.setEditable(false);
        txtMaVe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaVe.setForeground(new java.awt.Color(0, 153, 153));
        txtMaVe.setBorder(null);
        txtMaVe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaVeKeyReleased(evt);
            }
        });
        jPanel9.add(txtMaVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, 360, -1));
        jPanel9.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 390, 10));

        lblLoiGiaDV.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiGiaDV.setText("Giá dịch vụ chưa chính xác");
        jPanel9.add(lblLoiGiaDV, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 190, -1));

        txtGiaDV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGiaDV.setForeground(new java.awt.Color(0, 153, 153));
        txtGiaDV.setText("0");
        txtGiaDV.setBorder(null);
        txtGiaDV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiaDVKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGiaDVKeyTyped(evt);
            }
        });
        jPanel9.add(txtGiaDV, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 340, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(55, 38, 91));
        jLabel1.setText("Triệu");
        jPanel9.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 180, -1, -1));

        btnCBXMaVe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/down.png"))); // NOI18N
        btnCBXMaVe.setBorder(null);
        btnCBXMaVe.setBorderPainted(false);
        btnCBXMaVe.setContentAreaFilled(false);
        btnCBXMaVe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCBXMaVe.setDefaultCapable(false);
        btnCBXMaVe.setFocusPainted(false);
        btnCBXMaVe.setIconTextGap(0);
        btnCBXMaVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCBXMaVeActionPerformed(evt);
            }
        });
        jPanel9.add(btnCBXMaVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 90, -1, -1));

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
        jPanel9.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, -1, -1));

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
        jPanel9.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, -1, -1));

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
        jPanel9.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, -1, -1));

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
        jPanel9.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 260, -1, -1));

        splTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                splTableMouseClicked(evt);
            }
        });

        tblDichVu.setAutoCreateRowSorter(true);
        tblDichVu.setForeground(new java.awt.Color(0, 153, 153));
        tblDichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Dịch Vụ", "Mã Vé Máy Bay", "Tên Dịch Vụ", "Giá Dịch Vụ (Triệu)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDichVu.setAutoscrolls(false);
        tblDichVu.setFocusable(false);
        tblDichVu.setGridColor(new java.awt.Color(255, 255, 255));
        tblDichVu.setOpaque(false);
        tblDichVu.setRequestFocusEnabled(false);
        tblDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDichVuMouseClicked(evt);
            }
        });
        splTable.setViewportView(tblDichVu);

        jPanel9.add(splTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 940, 250));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 153, 153));
        jLabel19.setText("Mã dịch vụ");
        jPanel9.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        txtMaDichVu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaDichVu.setForeground(new java.awt.Color(0, 153, 153));
        txtMaDichVu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtMaDichVu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaDichVuKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMaDichVuKeyTyped(evt);
            }
        });
        jPanel9.add(txtMaDichVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 360, -1));

        lblLoiMaDV.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiMaDV.setText("Mã dịch vụ không chính xác");
        jPanel9.add(lblLoiMaDV, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 220, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 153, 153));
        jLabel21.setText("Tên dịch vụ");
        jPanel9.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        txtTenDV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenDV.setForeground(new java.awt.Color(0, 153, 153));
        txtTenDV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTenDV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenDVKeyReleased(evt);
            }
        });
        jPanel9.add(txtTenDV, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 360, -1));

        lblLoiTenDV.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiTenDV.setText("Tên dịch vụ không chính xác");
        jPanel9.add(lblLoiTenDV, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 190, -1));

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
        jPanel9.add(btnTroVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 260, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        new DichVuDAO().tim(tblDichVu, txtTimKiem.getText().trim());
        editColumnWidth();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtMaVeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaVeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaVeKeyReleased

    private void txtGiaDVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaDVKeyReleased
        new DungChung().xetSo(txtGiaDV);
    }//GEN-LAST:event_txtGiaDVKeyReleased

    private void txtGiaDVKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaDVKeyTyped
        if (txtGiaDV.getText().length() > 3) {
            evt.consume();
        }
    }//GEN-LAST:event_txtGiaDVKeyTyped

    private void btnCBXMaVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCBXMaVeActionPerformed
        showHideCBX();
    }//GEN-LAST:event_btnCBXMaVeActionPerformed

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
        if(tblDichVu.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "Chọn nhân viên cần cập nhật.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }else{
            capNhat();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        moi();
        tblDichVu.clearSelection();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void tblDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDichVuMouseClicked
        // TODO add your handling code here:
        txtTimKiem.setText(null);
        dong = tblDichVu.getSelectedRow();
        hienThi(dong);
    }//GEN-LAST:event_tblDichVuMouseClicked

    private void splTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_splTableMouseClicked
        // TODO add your handling code here:
        txtTimKiem.setText(null);
        dong = tblDichVu.getSelectedRow();
        hienThi(dong);
    }//GEN-LAST:event_splTableMouseClicked

    private void txtMaDichVuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaDichVuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaDichVuKeyReleased

    private void txtMaDichVuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaDichVuKeyTyped
        if (txtMaDichVu.getText().length() > 9) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMaDichVuKeyTyped

    private void txtTenDVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenDVKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenDVKeyReleased

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
            java.util.logging.Logger.getLogger(DichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DichVu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCBXMaVe;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTroVe;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JLabel lblLoiGiaDV;
    private javax.swing.JLabel lblLoiMaDV;
    private javax.swing.JLabel lblLoiMaVe;
    private javax.swing.JLabel lblLoiTenDV;
    private javax.swing.JPanel pnlVeMB;
    private javax.swing.JScrollPane splTable;
    private javax.swing.JSeparator sptMaVe;
    private javax.swing.JTable tblDichVu;
    private javax.swing.JTextField txtGiaDV;
    private javax.swing.JTextField txtMaDichVu;
    private javax.swing.JTextField txtMaVe;
    private javax.swing.JTextField txtTenDV;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
