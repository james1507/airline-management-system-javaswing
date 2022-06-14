/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import DAO.HanhKhachDAO;
import DungChung.DungChung;
import Model.HanhKhachmdl;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author conne
 */
public class HanhKhach extends javax.swing.JFrame {

    /**
     * Creates new form HanhKhach
     */
    
    String chucVu = DangNhap.vt;
    int dong = -1;

    private void an() {
        new DungChung().hideLBLError(new JLabel[]{lblLoiCMND, lblLoiHoTen, lblLoiGioiTinh, lblLoiSoDT, lblLoiEmail, lblLoiDiaChi});
    }

    private boolean check() {
        String regex_CMND = "[0-9]{9}";
        String regex_SDT = "0[0-9]{9}";
        String regex_email = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        if (txtCMND.getText().trim().isEmpty() || !txtCMND.getText().trim().matches(regex_CMND)) {
            lblLoiCMND.setVisible(true);
            txtCMND.requestFocus();
            return false;
        }
        if (txtHoten.getText().trim().isEmpty()) {
            lblLoiHoTen.setVisible(true);
            txtHoten.requestFocus();
            return false;
        }
        if (txtSDT.getText().trim().isEmpty() || !txtSDT.getText().trim().matches(regex_SDT)) {
            lblLoiSoDT.setVisible(true);
            txtSDT.requestFocus();
            return false;
        }
        if(!rdbNam.isSelected() && !rdbNu.isSelected()){
            lblLoiGioiTinh.setVisible(true);
            return false;
        }
        if (txtEmail.getText().trim().isEmpty() || !txtEmail.getText().trim().matches(regex_email)) {
            lblLoiEmail.setVisible(true);
            txtEmail.requestFocus();
            return false;
        }
        if (txtDiaChi.getText().trim().isEmpty()) {
            lblLoiDiaChi.setVisible(true);
            txtDiaChi.requestFocus();
            return false;
        }
        return true;
    }

    private void moi() {
        new DungChung().reset(new JTextField[]{txtCMND, txtHoten, txtSDT, txtEmail, txtDiaChi});
        btnGroupGT.clearSelection();
        an();
        dong = -1;
    }

    private void bang() {
        new HanhKhachDAO().loadTable(tblHanhKhach);
    }

    private void editColumnWidth() {
        int[] col = new int[]{40, 100, 200, 100, 100, 200, 200};
        new DungChung().editColumnWidth(col, tblHanhKhach);
    }

    private void them() {
        if (check()) {
            String cmnd = txtCMND.getText().trim();
            String hoten = txtHoten.getText().trim();
            String sdt = txtSDT.getText().trim();
            boolean gt = rdbNam.isSelected();
            String email = txtEmail.getText().trim();
            String diachi = txtDiaChi.getText().trim();
            int kt = new HanhKhachDAO().them(new HanhKhachmdl(cmnd, hoten, gt, sdt, email, diachi));
            if (kt == 1) {
                bang();
                HanhKhachmdl hk = new HanhKhachDAO().timHKToDen(cmnd);
                for (int i = 0; i < tblHanhKhach.getRowCount(); i++) {
                    String ma = String.valueOf(tblHanhKhach.getValueAt(i, 1));
                    if (ma.equals(hk.getCmnd())) {
                        tblHanhKhach.setRowSelectionInterval(i, i);
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
        if (!txtCMND.getText().isEmpty()) {
            String cmnd = txtCMND.getText().trim();
            String hoten = txtHoten.getText().trim();
            String sdt = txtSDT.getText().trim();
            boolean gt = rdbNam.isSelected();
            String email = txtEmail.getText().trim();
            String diachi = txtDiaChi.getText().trim();
            int kt = new HanhKhachDAO().sua(new HanhKhachmdl(cmnd, hoten, gt, sdt, email, diachi));
            if (kt == 1) {
                bang();
                editColumnWidth();
                tblHanhKhach.setRowSelectionInterval(dong, dong);
                an();

            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void xoa() {
        if (dong >= 0) {
            int r = JOptionPane.showConfirmDialog(this, "Bạn cần xóa hành khách " + tblHanhKhach.getValueAt(dong, 2) + "?", "Thông báo", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                String ma = txtCMND.getText().trim();
                int kt = new HanhKhachDAO().xoa(new HanhKhachmdl(ma));
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
        HanhKhachmdl hk = new HanhKhachmdl();
        new HanhKhachDAO().hienThi(tblHanhKhach, hk, row);
        txtCMND.setText(hk.getCmnd());
        txtHoten.setText(hk.getHoTen());
        if (hk.isGioiTinh()) {
            rdbNam.setSelected(true);
        } else {
            rdbNu.setSelected(true);
        }
        txtSDT.setText(hk.getSoDT());
        txtEmail.setText(hk.getEmail());
        txtDiaChi.setText(hk.getDiaChi());
        txtCMND.setEditable(false);
    }

     public HanhKhach() {
        initComponents();
        setLocationRelativeTo(this);
        new DungChung().transTXT(new JTextField[]{txtCMND, txtHoten, txtSDT, txtEmail, txtDiaChi, txtTimKiem});
        an();
        bang();
        editColumnWidth();
        splTable.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
    }

    public HanhKhach(String vt) {
        initComponents();
        setLocationRelativeTo(this);
        new DungChung().transTXT(new JTextField[]{txtCMND, txtHoten, txtSDT, txtEmail, txtDiaChi, txtTimKiem});
        an();
        bang();
        editColumnWidth();
        splTable.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        chucVu = vt;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupGT = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtHoten = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        rdbNam = new javax.swing.JRadioButton();
        rdbNu = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        lblLoiCMND = new javax.swing.JLabel();
        lblLoiSoDT = new javax.swing.JLabel();
        lblLoiHoTen = new javax.swing.JLabel();
        lblLoiDiaChi = new javax.swing.JLabel();
        lblLoiEmail = new javax.swing.JLabel();
        lblLoiGioiTinh = new javax.swing.JLabel();
        txtCMND = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        splTable = new javax.swing.JScrollPane();
        tblHanhKhach = new javax.swing.JTable();
        btnTroVe = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("QUẢN LÝ HÀNH KHÁCH");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 11, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Tìm kiếm");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, -1, -1));

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiem.setForeground(new java.awt.Color(0, 153, 153));
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });
        jPanel1.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 196, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("Chứng minh nhân dân");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("Giới tính");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, -1, -1));

        txtHoten.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoten.setForeground(new java.awt.Color(0, 153, 153));
        txtHoten.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtHoten, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, 390, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("Họ tên");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, -1, -1));

        btnGroupGT.add(rdbNam);
        rdbNam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbNam.setForeground(new java.awt.Color(0, 153, 153));
        rdbNam.setText("Nam");
        rdbNam.setBorder(null);
        jPanel1.add(rdbNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 190, -1, -1));

        btnGroupGT.add(rdbNu);
        rdbNu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbNu.setForeground(new java.awt.Color(0, 153, 153));
        rdbNu.setText("Nữ");
        rdbNu.setBorder(null);
        jPanel1.add(rdbNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 190, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 153));
        jLabel6.setText("Số điện thoại");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 153));
        jLabel7.setText("Địa chỉ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 250, -1, -1));

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(0, 153, 153));
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 360, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 153));
        jLabel8.setText("Email");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiaChi.setForeground(new java.awt.Color(0, 153, 153));
        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 280, 390, 20));

        lblLoiCMND.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiCMND.setText("Chứng minh nhân dân không chinh xác");
        jPanel1.add(lblLoiCMND, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 220, -1));

        lblLoiSoDT.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiSoDT.setText("Số điện thoại không chính xác");
        jPanel1.add(lblLoiSoDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 190, -1));

        lblLoiHoTen.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiHoTen.setText("Họ tên không chính xác");
        jPanel1.add(lblLoiHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 150, -1));

        lblLoiDiaChi.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiDiaChi.setText("Địa chỉ không chính xác");
        jPanel1.add(lblLoiDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 300, 150, -1));

        lblLoiEmail.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiEmail.setText("Email không chính xác");
        jPanel1.add(lblLoiEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 150, -1));

        lblLoiGioiTinh.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiGioiTinh.setText("Chưa chọn giới tính");
        jPanel1.add(lblLoiGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, 150, -1));

        txtCMND.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCMND.setForeground(new java.awt.Color(0, 153, 153));
        txtCMND.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtCMND.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCMNDKeyTyped(evt);
            }
        });
        jPanel1.add(txtCMND, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 360, 20));

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSDT.setForeground(new java.awt.Color(0, 153, 153));
        txtSDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSDTKeyTyped(evt);
            }
        });
        jPanel1.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 178, 360, 20));

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
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, -1, -1));

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
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 350, -1, -1));

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
        jPanel1.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 350, -1, -1));

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
        jPanel1.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 350, -1, -1));

        splTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                splTableMouseClicked(evt);
            }
        });

        tblHanhKhach.setAutoCreateRowSorter(true);
        tblHanhKhach.setForeground(new java.awt.Color(0, 153, 153));
        tblHanhKhach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "CMND", "Họ Tên", "Giới Tính", "Điện Thoại", "Email", "Địa Chỉ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblHanhKhach.setAutoscrolls(false);
        tblHanhKhach.setFocusable(false);
        tblHanhKhach.setGridColor(new java.awt.Color(255, 255, 255));
        tblHanhKhach.setOpaque(false);
        tblHanhKhach.setRequestFocusEnabled(false);
        tblHanhKhach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHanhKhachMouseClicked(evt);
            }
        });
        splTable.setViewportView(tblHanhKhach);

        jPanel1.add(splTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 940, 250));

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
        jPanel1.add(btnTroVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 350, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1016, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed

    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        new HanhKhachDAO().tim(tblHanhKhach, txtTimKiem.getText().trim());
        editColumnWidth();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtCMNDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCMNDKeyTyped
        if (txtCMND.getText().length() > 8) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCMNDKeyTyped

    private void txtSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased
        txtSDT.setText(txtSDT.getText().replaceFirst("[a-zA-Z ]", ""));
    }//GEN-LAST:event_txtSDTKeyReleased

    private void txtSDTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyTyped
        if (txtSDT.getText().length() > 9) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSDTKeyTyped

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
        if(tblHanhKhach.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "Chọn nhân viên cần cập nhật.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }else{
            capNhat();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        moi();
        tblHanhKhach.clearSelection();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void tblHanhKhachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHanhKhachMouseClicked
        // TODO add your handling code here:
        txtTimKiem.setText(null);
        dong = tblHanhKhach.getSelectedRow();
        hienThi(dong);
    }//GEN-LAST:event_tblHanhKhachMouseClicked

    private void splTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_splTableMouseClicked
        // TODO add your handling code here:
        txtTimKiem.setText(null);
        dong = tblHanhKhach.getSelectedRow();
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
            java.util.logging.Logger.getLogger(HanhKhach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HanhKhach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HanhKhach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HanhKhach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HanhKhach().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.ButtonGroup btnGroupGT;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTroVe;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblLoiCMND;
    private javax.swing.JLabel lblLoiDiaChi;
    private javax.swing.JLabel lblLoiEmail;
    private javax.swing.JLabel lblLoiGioiTinh;
    private javax.swing.JLabel lblLoiHoTen;
    private javax.swing.JLabel lblLoiSoDT;
    private javax.swing.JRadioButton rdbNam;
    private javax.swing.JRadioButton rdbNu;
    private javax.swing.JScrollPane splTable;
    private javax.swing.JTable tblHanhKhach;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoten;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
