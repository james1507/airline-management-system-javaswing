/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import DAO.SanBayDAO;
import DungChung.DungChung;
import Model.SanBaymdl;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author conne
 */
public class SanBay extends javax.swing.JFrame {

    /**
     * Creates new form SanBay
     */
    
    String chucVu = DangNhap.vt;
    int dong = -1;

    private void an() {
        new DungChung().hideLBLError(new JLabel[]{lblLoiMaSB, lblLoiTen, lblLoiQuocGia, lblLoiDiaDiem});
    }

    private boolean check() {
        JLabel[] lbl = new JLabel[]{lblLoiMaSB, lblLoiTen, lblLoiQuocGia, lblLoiDiaDiem};
        JTextField[] txt = new JTextField[]{txtMaSanBay, txtTenSanBay, txtQuocGia, txtDiaDiem};
        return new DungChung().check(lbl, txt);
    }

    private void moi() {
        new DungChung().reset(new JTextField[]{txtMaSanBay, txtTenSanBay, txtQuocGia, txtDiaDiem});
        an();
        dong = -1;
    }

    private void bang() {
        new SanBayDAO().loadTable(tblSanBay);
    }

    private void editColumnWidth() {
        int[] col = new int[]{40, 225, 225, 225, 225};
        new DungChung().editColumnWidth(col, tblSanBay);
    }

    private void them() {
        if (check()) {
            String ma = txtMaSanBay.getText().trim();
            String ten = txtTenSanBay.getText().trim();
            String quocgia = txtQuocGia.getText().trim();
            String diadiem = txtDiaDiem.getText().trim();
            int kt = new SanBayDAO().them(new SanBaymdl(ma, ten, quocgia, diadiem));
            if (kt == 1) {
                bang();
                SanBaymdl sb = new SanBayDAO().timSBToDen(ma);
                for (int i = 0; i < tblSanBay.getRowCount(); i++) {
                    String id = String.valueOf(tblSanBay.getValueAt(i, 1));
                    if (id.equals(sb.getMaSanBay())) {
                        tblSanBay.setRowSelectionInterval(i, i);
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
        if (!txtMaSanBay.getText().isEmpty()) {
            String ma = txtMaSanBay.getText().trim();
            String ten = txtTenSanBay.getText().trim();
            String quocgia = txtQuocGia.getText().trim();
            String diadiem = txtDiaDiem.getText().trim();
            int kt = new SanBayDAO().sua(new SanBaymdl(ma, ten, quocgia, diadiem));
            if (kt == 1) {
                bang();
                tblSanBay.setRowSelectionInterval(dong, dong);
                an();
                editColumnWidth();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void xoa() {
        if (dong >= 0) {
            int r = JOptionPane.showConfirmDialog(this, "Bạn cần xóa sân bay " + tblSanBay.getValueAt(dong, 2) + "?", "Thông báo", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                String ma = txtMaSanBay.getText().trim();
                int kt = new SanBayDAO().xoa(new SanBaymdl(ma));
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
        SanBaymdl sb = new SanBaymdl();
        new SanBayDAO().hienThi(tblSanBay, sb, row);
        txtMaSanBay.setText(sb.getMaSanBay());
        txtTenSanBay.setText(sb.getTenSanbay());
        txtQuocGia.setText(sb.getQuocGia());
        txtDiaDiem.setText(sb.getDiaDiem());
        txtMaSanBay.setEditable(false);
    }

    public SanBay() {
        initComponents();
        setLocationRelativeTo(this);
        new DungChung().transTXT(new JTextField[]{txtMaSanBay, txtTenSanBay, txtQuocGia, txtDiaDiem, txtTimKiem});
        an();
        bang();
        editColumnWidth();
        splTable.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
    }
    
    public SanBay(String vt) {
        initComponents();
        setLocationRelativeTo(this);
        new DungChung().transTXT(new JTextField[]{txtMaSanBay, txtTenSanBay, txtQuocGia, txtDiaDiem, txtTimKiem});
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

        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        txtMaSanBay = new javax.swing.JTextField();
        lblLoiMaSB = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTenSanBay = new javax.swing.JTextField();
        lblLoiTen = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtQuocGia = new javax.swing.JTextField();
        lblLoiQuocGia = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtDiaDiem = new javax.swing.JTextField();
        lblLoiDiaDiem = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnTroVe = new javax.swing.JButton();
        splTable = new javax.swing.JScrollPane();
        tblSanBay = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 153, 153));
        jLabel15.setText("QUẢN LÝ SÂN BAY");
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

        txtMaSanBay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaSanBay.setForeground(new java.awt.Color(0, 153, 153));
        txtMaSanBay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtMaSanBay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaSanBayKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMaSanBayKeyTyped(evt);
            }
        });
        jPanel8.add(txtMaSanBay, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 360, -1));

        lblLoiMaSB.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiMaSB.setText("Mã hãng máy bay không chinh xác");
        jPanel8.add(lblLoiMaSB, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 220, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 153, 153));
        jLabel17.setText("Mã sân bay");
        jPanel8.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 153, 153));
        jLabel18.setText("Tên sân bay");
        jPanel8.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, -1, -1));

        txtTenSanBay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenSanBay.setForeground(new java.awt.Color(0, 153, 153));
        txtTenSanBay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel8.add(txtTenSanBay, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, 390, -1));

        lblLoiTen.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiTen.setText("Tên hãng máy bay không chính xác");
        jPanel8.add(lblLoiTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 240, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 153, 153));
        jLabel19.setText("Quốc gia");
        jPanel8.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        txtQuocGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtQuocGia.setForeground(new java.awt.Color(0, 153, 153));
        txtQuocGia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtQuocGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuocGiaKeyReleased(evt);
            }
        });
        jPanel8.add(txtQuocGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 360, -1));

        lblLoiQuocGia.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiQuocGia.setText("Quốc gia không chính xác");
        jPanel8.add(lblLoiQuocGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 190, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 153, 153));
        jLabel20.setText("Địa điểm");
        jPanel8.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, -1, -1));

        txtDiaDiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiaDiem.setForeground(new java.awt.Color(0, 153, 153));
        txtDiaDiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtDiaDiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiaDiemKeyReleased(evt);
            }
        });
        jPanel8.add(txtDiaDiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 390, -1));

        lblLoiDiaDiem.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiDiaDiem.setText("Địa điểm không chính xác");
        jPanel8.add(lblLoiDiaDiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 200, -1));

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
        jPanel8.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, -1, -1));

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
        jPanel8.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, -1, -1));

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
        jPanel8.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, -1, -1));

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
        jPanel8.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 260, -1, -1));

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
        jPanel8.add(btnTroVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 260, -1, -1));

        splTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                splTableMouseClicked(evt);
            }
        });

        tblSanBay.setAutoCreateRowSorter(true);
        tblSanBay.setForeground(new java.awt.Color(0, 153, 153));
        tblSanBay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Sân Bay", "Tên Sân Bay", "Quốc Gia", "Địa Điểm"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblSanBay.setAutoscrolls(false);
        tblSanBay.setFocusable(false);
        tblSanBay.setGridColor(new java.awt.Color(255, 255, 255));
        tblSanBay.setOpaque(false);
        tblSanBay.setRequestFocusEnabled(false);
        tblSanBay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanBayMouseClicked(evt);
            }
        });
        splTable.setViewportView(tblSanBay);

        jPanel8.add(splTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 940, 250));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        new SanBayDAO().tim(tblSanBay, txtTimKiem.getText().trim());
        editColumnWidth();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtMaSanBayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaSanBayKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSanBayKeyReleased

    private void txtMaSanBayKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaSanBayKeyTyped
        if (txtMaSanBay.getText().length() > 9) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMaSanBayKeyTyped

    private void txtQuocGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuocGiaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuocGiaKeyReleased

    private void txtDiaDiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiaDiemKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaDiemKeyReleased

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
        if(tblSanBay.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "Chọn nhân viên cần cập nhật.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }else{
            capNhat();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        moi();
        tblSanBay.clearSelection();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnTroVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTroVeActionPerformed
        // TODO add your handling code here:
        new MainForm().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTroVeActionPerformed

    private void tblSanBayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanBayMouseClicked
        // TODO add your handling code here:
        txtTimKiem.setText(null);
        dong = tblSanBay.getSelectedRow();
        hienThi(dong);
    }//GEN-LAST:event_tblSanBayMouseClicked

    private void splTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_splTableMouseClicked
        // TODO add your handling code here:
        txtTimKiem.setText(null);
        dong = tblSanBay.getSelectedRow();
        hienThi(dong);
    }//GEN-LAST:event_splTableMouseClicked

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
            java.util.logging.Logger.getLogger(SanBay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SanBay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SanBay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SanBay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        SanBay sb = new SanBay();
        sb.setLocationRelativeTo(null);
        sb.setVisible(true);
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTroVe;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblLoiDiaDiem;
    private javax.swing.JLabel lblLoiMaSB;
    private javax.swing.JLabel lblLoiQuocGia;
    private javax.swing.JLabel lblLoiTen;
    private javax.swing.JScrollPane splTable;
    private javax.swing.JTable tblSanBay;
    private javax.swing.JTextField txtDiaDiem;
    private javax.swing.JTextField txtMaSanBay;
    private javax.swing.JTextField txtQuocGia;
    private javax.swing.JTextField txtTenSanBay;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
