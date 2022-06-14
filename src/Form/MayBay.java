/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import DAO.MayBayDAO;
import DungChung.DungChung;
import Model.HangMayBaymdl;
import Model.MayBaymdl;
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
public class MayBay extends javax.swing.JFrame {

    /**
     * Creates new form MayBay
     */
    
    String chucVu = DangNhap.vt;
    int showHide = 1;
    int dong = -1;

    private void cbx() {
        ArrayList<JLabel> listLBL = new ArrayList<>();
        try {
            int cao = 3;
            ArrayList<HangMayBaymdl> arr_HMB = new MayBayDAO().layDS();
            for (int i = 0; i < arr_HMB.size(); i++) {
                JLabel lbl = new JLabel(arr_HMB.get(i).getMaHang() + " - " + arr_HMB.get(i).getTenHang());
                lbl.setSize(pnlMaHangMayBay.getWidth(), 20);
                lbl.setLocation(10, cao);
                lbl.setName("lbl" + arr_HMB.get(i).getMaHang());
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
                                txtMaHangMB.setText(listLBL.get(i).getText());
                                pnlMaHangMayBay.setVisible(false);
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
                pnlMaHangMayBay.add(lbl);
                listLBL.add(lbl);
                cao += 20;
            }
            pnlMaHangMayBay.setPreferredSize(new Dimension(sptMaMB.getWidth(), listLBL.size() * 20 + 7));
            pnlMaHangMayBay.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showHideCBX() {
        showHide++;
        if (showHide % 2 == 0) {
            pnlMaHangMayBay.setVisible(true);
        } else {
            pnlMaHangMayBay.setVisible(false);
        }
    }

    private void an() {
        new DungChung().hideLBLError(new JLabel[]{lblLoiMaMayBay, lblLoiMaHangMB});
    }

    private boolean check() {
        return new DungChung().check(new JLabel[]{lblLoiMaMayBay, lblLoiMaHangMB}, new JTextField[]{txtMaMayBay, txtMaHangMB});
    }

    private void moi() {
        new DungChung().reset(new JTextField[]{txtMaMayBay, txtMaHangMB});
        an();
        dong = -1;
    }

    private void bang() {
        new MayBayDAO().loadTable(tblMayBay);
    }

    private void editColumnWidth() {
        int[] col = new int[]{40, 450, 450};
        new DungChung().editColumnWidth(col, tblMayBay);
    }

    private void them() {
        if (check()) {
            String ma = txtMaMayBay.getText().trim();
            String[] str = txtMaHangMB.getText().trim().split("-");
            String mahang = str[0].trim();
            int kt = new MayBayDAO().them(new MayBaymdl(ma, mahang));
            if (kt == 1) {
                bang();
                MayBaymdl lv = new MayBayDAO().timMBToDen(ma);
                for (int i = 0; i < tblMayBay.getRowCount(); i++) {
                    String id = String.valueOf(tblMayBay.getValueAt(i, 1));
                    if (id.equals(lv.getMaMaybay())) {
                        tblMayBay.setRowSelectionInterval(i, i);
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
        if (!txtMaMayBay.getText().isEmpty()) {
            String ma = txtMaMayBay.getText().trim();
            String[] str = txtMaHangMB.getText().trim().split("-");
            String mahang = str[0].trim();
            int kt = new MayBayDAO().sua(new MayBaymdl(ma, mahang));
            if (kt == 1) {
                bang();
                tblMayBay.setRowSelectionInterval(dong, dong);
                an();
                editColumnWidth();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void xoa() {
        if (dong >= 0) {
            int r = JOptionPane.showConfirmDialog(this, "Bạn cần xóa máy bay " + tblMayBay.getValueAt(dong, 1) + "?", "Thông báo", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                String ma = txtMaMayBay.getText().trim();
                int kt = new MayBayDAO().xoa(new MayBaymdl(ma));
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
        MayBaymdl mb = new MayBaymdl();
        new MayBayDAO().hienThi(tblMayBay, mb, row);
        txtMaMayBay.setText(mb.getMaMaybay());
        txtMaHangMB.setText(mb.getMaHang());
        txtMaMayBay.setEditable(false);
    }

    public MayBay() {
        initComponents();
        setLocationRelativeTo(this);
        cbx();
        new DungChung().transTXT(new JTextField[]{txtMaMayBay, txtMaHangMB, txtTimKiem});
        an();
        bang();
        editColumnWidth();
        splTable.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
    }
    
    public MayBay(String vt) {
        initComponents();
        setLocationRelativeTo(this);
        cbx();
        new DungChung().transTXT(new JTextField[]{txtMaMayBay, txtMaHangMB, txtTimKiem});
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

        jPanel1 = new javax.swing.JPanel();
        pnlMaHangMayBay = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        txtMaMayBay = new javax.swing.JTextField();
        lblLoiMaMayBay = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMaHangMB = new javax.swing.JTextField();
        sptMaMB = new javax.swing.JSeparator();
        lblLoiMaHangMB = new javax.swing.JLabel();
        btnChonCBX = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        splTable = new javax.swing.JScrollPane();
        tblMayBay = new javax.swing.JTable();
        btnTroVe = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlMaHangMayBay.setBackground(new java.awt.Color(194, 194, 194));
        pnlMaHangMayBay.setPreferredSize(new java.awt.Dimension(390, 100));

        javax.swing.GroupLayout pnlMaHangMayBayLayout = new javax.swing.GroupLayout(pnlMaHangMayBay);
        pnlMaHangMayBay.setLayout(pnlMaHangMayBayLayout);
        pnlMaHangMayBayLayout.setHorizontalGroup(
            pnlMaHangMayBayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        pnlMaHangMayBayLayout.setVerticalGroup(
            pnlMaHangMayBayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.add(pnlMaHangMayBay, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("QUẢN LÝ MÁY BAY");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 11, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Tìm kiếm");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, -1, -1));

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiem.setForeground(new java.awt.Color(0, 153, 153));
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });
        jPanel1.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 196, -1));

        txtMaMayBay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaMayBay.setForeground(new java.awt.Color(0, 153, 153));
        txtMaMayBay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtMaMayBay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaMayBayKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMaMayBayKeyTyped(evt);
            }
        });
        jPanel1.add(txtMaMayBay, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 360, -1));

        lblLoiMaMayBay.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiMaMayBay.setText("Mã máy bay không chinh xác");
        jPanel1.add(lblLoiMaMayBay, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 220, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("Mã máy bay");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("Mã hãng máy bay");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, -1, -1));

        txtMaHangMB.setEditable(false);
        txtMaHangMB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaHangMB.setForeground(new java.awt.Color(0, 153, 153));
        txtMaHangMB.setBorder(null);
        jPanel1.add(txtMaHangMB, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, 360, -1));
        jPanel1.add(sptMaMB, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 390, 10));

        lblLoiMaHangMB.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiMaHangMB.setText("Mã hãng bay không chính xác");
        jPanel1.add(lblLoiMaHangMB, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 180, -1));

        btnChonCBX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/down.png"))); // NOI18N
        btnChonCBX.setBorder(null);
        btnChonCBX.setBorderPainted(false);
        btnChonCBX.setContentAreaFilled(false);
        btnChonCBX.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChonCBX.setDefaultCapable(false);
        btnChonCBX.setFocusPainted(false);
        btnChonCBX.setFocusable(false);
        btnChonCBX.setIconTextGap(0);
        btnChonCBX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonCBXActionPerformed(evt);
            }
        });
        jPanel1.add(btnChonCBX, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 90, -1, -1));

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
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, -1, -1));

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
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, -1, -1));

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
        jPanel1.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, -1, -1));

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
        jPanel1.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, -1, -1));

        splTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                splTableMouseClicked(evt);
            }
        });

        tblMayBay.setAutoCreateRowSorter(true);
        tblMayBay.setForeground(new java.awt.Color(0, 153, 153));
        tblMayBay.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMayBay.setAutoscrolls(false);
        tblMayBay.setFocusable(false);
        tblMayBay.setGridColor(new java.awt.Color(255, 255, 255));
        tblMayBay.setOpaque(false);
        tblMayBay.setRequestFocusEnabled(false);
        tblMayBay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMayBayMouseClicked(evt);
            }
        });
        splTable.setViewportView(tblMayBay);

        jPanel1.add(splTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 940, 250));

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
        jPanel1.add(btnTroVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 180, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        new MayBayDAO().tim(tblMayBay, txtTimKiem.getText().trim());
        editColumnWidth();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtMaMayBayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaMayBayKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaMayBayKeyReleased

    private void txtMaMayBayKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaMayBayKeyTyped
        if (txtMaMayBay.getText().length() > 9) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMaMayBayKeyTyped

    private void btnChonCBXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonCBXActionPerformed
        showHideCBX();
    }//GEN-LAST:event_btnChonCBXActionPerformed

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
        if(tblMayBay.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "Chọn nhân viên cần cập nhật.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }else{
            capNhat();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        moi();
        tblMayBay.clearSelection();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void tblMayBayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMayBayMouseClicked
        // TODO add your handling code here:
        txtTimKiem.setText(null);
        dong = tblMayBay.getSelectedRow();
        hienThi(dong);
    }//GEN-LAST:event_tblMayBayMouseClicked

    private void splTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_splTableMouseClicked
        // TODO add your handling code here:
        txtTimKiem.setText(null);
        dong = tblMayBay.getSelectedRow();
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
            java.util.logging.Logger.getLogger(MayBay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MayBay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MayBay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MayBay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MayBay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnChonCBX;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTroVe;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblLoiMaHangMB;
    private javax.swing.JLabel lblLoiMaMayBay;
    private javax.swing.JPanel pnlMaHangMayBay;
    private javax.swing.JScrollPane splTable;
    private javax.swing.JSeparator sptMaMB;
    private javax.swing.JTable tblMayBay;
    private javax.swing.JTextField txtMaHangMB;
    private javax.swing.JTextField txtMaMayBay;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
