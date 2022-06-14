/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import DAO.NhanVienDAO;
import DungChung.DungChung;
import Model.NhanVienmdl;
import java.awt.Cursor;
import java.awt.Dimension;
import static java.awt.Frame.HAND_CURSOR;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author conne
 */
public class NhanVien extends javax.swing.JFrame {

    /**
     * Creates new form NhanVien
     */
    
    
    String chucVu = DangNhap.vt;
    int showHidePass = 0;
    int dong = -1;
    ArrayList<String> arr = new ArrayList<>();

    private void showHidePassword() {
        showHidePass++;
        if (showHidePass % 2 == 0) {
            Image img = getToolkit().createImage("src//Icon//eyeBlack.png");
            txtPassword.setEchoChar((char) 0);
            
        } else {
            Image img = getToolkit().createImage("src//Icon//eyeHideBlack.png");
            txtPassword.setEchoChar('\u25cf');
        }
    }

    private void an() {
        new DungChung().hideLBLError(new JLabel[]{lblLoiMaNV, lblLoiMatKhau, lblLoiVaiTro, lblLoiHoTen, lblLoiGioiTinh, lblLoiSDT, lblLoiEmail, lblLoiDiaChi, lblLoiHinh});
    }

    private boolean check() {
        String regex_SDT = "0[0-9]{9}";
        String regex_email = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        if (tFMaNV.getText().trim().isEmpty()) {
            lblLoiMaNV.setVisible(true);
            tFMaNV.requestFocus();
            return false;
        }
        if(!rdbQuanTri.isSelected() && !rdbNhanVien.isSelected()){
            lblLoiVaiTro.setVisible(true);
            return false;
        }
        if(!rdbNam.isSelected() && !rdbNu.isSelected()){
            lblLoiGioiTinh.setVisible(true);
            return false;
        }
        if (txtPassword.getText().trim().isEmpty()) {
            lblLoiMatKhau.setVisible(true);
            txtPassword.requestFocus();
            return false;
        }
        if (txtHoTen.getText().trim().isEmpty()) {
            lblLoiHoTen.setVisible(true);
            txtHoTen.requestFocus();
            return false;
        }
        if (txtDienThoai.getText().trim().isEmpty() || !txtDienThoai.getText().trim().matches(regex_SDT)) {
            lblLoiSDT.setVisible(true);
            txtDienThoai.requestFocus();
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
        new DungChung().reset(new JTextField[]{tFMaNV, txtPassword, txtHoTen, txtDienThoai, txtEmail, txtDiaChi});
        btnGroupVaiTro.clearSelection();
        btnGroupGioiTinh.clearSelection();
        lblHinh.setIcon(null);
        an();
        dong = -1;
    }

    private void bang() {
        new NhanVienDAO().loadTable(tblNhanVien);
    }

    private void editColumnWidth() {
        int[] col = new int[]{40, 60, 70, 100, 130, 70, 110, 170, 200, 0};
        new DungChung().editColumnWidth(col, tblNhanVien);
    }

    private void them() {
        if (check()) {
            String manv = tFMaNV.getText().trim();
            String matkhau = txtPassword.getText().trim();
            String vaitro = rdbQuanTri.isSelected() ? "Trưởng phòng" : "Nhân viên";
            String hoten = txtHoTen.getText().trim();
            boolean gioitinh = rdbNam.isSelected();
            String sdt = txtDienThoai.getText().trim();
            String email = txtEmail.getText().trim();
            String diachi = txtDiaChi.getText().trim();
            String hinh = "";
            if (arr.size() == 0) {
                hinh = "defaultIMG.png";
            } else {
                hinh = arr.get(arr.size() - 1);
            }
            int kt = new NhanVienDAO().them(new NhanVienmdl(manv, matkhau, vaitro, hoten, gioitinh, sdt, email, diachi, hinh));
            if (kt == 1) {
                bang();
                NhanVienmdl hk = new NhanVienDAO().timNVToDen(manv);
                for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
                    String ma = String.valueOf(tblNhanVien.getValueAt(i, 1));
                    if (ma.equals(hk.getMaNhanVien())) {
                        tblNhanVien.setRowSelectionInterval(i, i);
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
        if (check()) {
            String manv = tFMaNV.getText().trim();
            String matkhau = txtPassword.getText().trim();
            String vaitro = rdbQuanTri.isSelected() ? "Trưởng phòng" : "Nhân viên";
            String hoten = txtHoTen.getText().trim();
            boolean gioitinh = rdbNam.isSelected();
            String sdt = txtDienThoai.getText().trim();
            String email = txtEmail.getText().trim();
            String diachi = txtDiaChi.getText().trim();
            String hinh = "";
            if (arr.size() == 0) {
                hinh = "defaultIMG.png";
            } else {
                hinh = arr.get(arr.size() - 1);
            }
            int kt = new NhanVienDAO().sua(new NhanVienmdl(manv, matkhau, vaitro, hoten, gioitinh, sdt, email, diachi, hinh));
            if (kt == 1) {
                bang();
                tblNhanVien.setRowSelectionInterval(dong, dong);
                an();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
        editColumnWidth();
    }

    private void xoa() {
        if (dong >= 0) {
            int r = JOptionPane.showConfirmDialog(this, "Bạn cần xóa nhân viên " + tblNhanVien.getValueAt(dong, 4) + "?", "Thông báo", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                String ma = tFMaNV.getText().trim();
                int kt = new NhanVienDAO().xoa(new NhanVienmdl(ma));
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
        NhanVienmdl hk = new NhanVienmdl();
        new NhanVienDAO().hienThi(tblNhanVien, hk, row);
        tFMaNV.setText(hk.getMaNhanVien());
        txtPassword.setText(hk.getMatKhau());
        if (hk.getVaiTro().trim().equals("Trưởng phòng")) {
            rdbQuanTri.setSelected(true);
        } else {
            rdbNhanVien.setSelected(true);
        }
        txtHoTen.setText(hk.getHoten());
        if (hk.isGioiTinh()) {
            rdbNam.setSelected(true);
        } else {
            rdbNu.setSelected(true);
        }
        txtDienThoai.setText(hk.getSoDT());
        txtEmail.setText(hk.getEmail());
        txtDiaChi.setText(hk.getDiaChi());
        Image img = getToolkit().createImage("src//HinhDuLieu//" + tblNhanVien.getValueAt(row, 9));
        Image anh = img.getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(anh);
        lblHinh.setIcon(icon);
        tFMaNV.setEditable(false);
    }

    private void chonAnh() {
        JFileChooser filec = new JFileChooser();
        int r = filec.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            File fl = filec.getSelectedFile();
            try {
                BufferedImage bfi = ImageIO.read(fl);
                String[] s = String.valueOf(fl).split("\\\\");
                for (int i = 0; i < s.length; i++) {
                    arr.add(s[i]);
                }
                ImageIO.write(bfi, "jpg", new File("src//HinhDuLieu//" + arr.get(arr.size() - 1)));
                ImageIO.write(bfi, "png", new File("src//HinhDuLieu//" + arr.get(arr.size() - 1)));
                Image anh = bfi.getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(anh);
                lblHinh.setIcon(icon);
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(this, "file bạn chọn không phải là file ảnh!");
            }
        }
    }

    public NhanVien() {
        initComponents();
        setLocationRelativeTo(this);
        new DungChung().transTXT(new JTextField[]{tFMaNV, txtPassword, txtHoTen, txtDienThoai, txtEmail, txtDiaChi, txtTimKiem});
        an();
        bang();
        editColumnWidth();
        splTable.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
    }
    
    public NhanVien(String vt) {
        initComponents();
        setLocationRelativeTo(this);
        new DungChung().transTXT(new JTextField[]{tFMaNV, txtPassword, txtHoTen, txtDienThoai, txtEmail, txtDiaChi, txtTimKiem});
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

        btnGroupVaiTro = new javax.swing.ButtonGroup();
        btnGroupGioiTinh = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        lblLoiMaNV = new javax.swing.JLabel();
        tFMaNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblLoiMatKhau = new javax.swing.JLabel();
        rdbQuanTri = new javax.swing.JRadioButton();
        rdbNhanVien = new javax.swing.JRadioButton();
        lblLoiVaiTro = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        rdbNam = new javax.swing.JRadioButton();
        rdbNu = new javax.swing.JRadioButton();
        lblLoiGioiTinh = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDienThoai = new javax.swing.JTextField();
        lblLoiSDT = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblLoiHoTen = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblLoiEmail = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        lblLoiHinh = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel12 = new javax.swing.JLabel();
        lblHinh = new javax.swing.JLabel();
        lblLoiDiaChi = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        splTable = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        btnTroVe = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");
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

        lblLoiMaNV.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiMaNV.setText("Mã nhân viên không chinh xác");
        jPanel1.add(lblLoiMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 180, -1));

        tFMaNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tFMaNV.setForeground(new java.awt.Color(0, 153, 153));
        tFMaNV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        tFMaNV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tFMaNVKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tFMaNVKeyTyped(evt);
            }
        });
        jPanel1.add(tFMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 220, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("Mã nhân viên");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 153));
        jLabel6.setText("Mật khẩu");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        lblLoiMatKhau.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiMatKhau.setText("Mật khẩu không chính xác");
        jPanel1.add(lblLoiMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 150, -1));

        btnGroupVaiTro.add(rdbQuanTri);
        rdbQuanTri.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbQuanTri.setForeground(new java.awt.Color(0, 153, 153));
        rdbQuanTri.setText("Trưởng phòng");
        rdbQuanTri.setBorder(null);
        jPanel1.add(rdbQuanTri, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, -1, -1));

        btnGroupVaiTro.add(rdbNhanVien);
        rdbNhanVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbNhanVien.setForeground(new java.awt.Color(0, 153, 153));
        rdbNhanVien.setText("Nhân viên");
        rdbNhanVien.setBorder(null);
        rdbNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbNhanVienActionPerformed(evt);
            }
        });
        jPanel1.add(rdbNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, -1, -1));

        lblLoiVaiTro.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiVaiTro.setText("Chưa chọn vai trò");
        jPanel1.add(lblLoiVaiTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, 150, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 153));
        jLabel7.setText("Vai trò");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, -1, -1));

        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTen.setForeground(new java.awt.Color(0, 153, 153));
        txtHoTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 220, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("Giới tính");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 60, -1, -1));

        btnGroupGioiTinh.add(rdbNam);
        rdbNam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbNam.setForeground(new java.awt.Color(0, 153, 153));
        rdbNam.setText("Nam");
        rdbNam.setBorder(null);
        jPanel1.add(rdbNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 90, -1, -1));

        btnGroupGioiTinh.add(rdbNu);
        rdbNu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbNu.setForeground(new java.awt.Color(0, 153, 153));
        rdbNu.setText("Nữ");
        rdbNu.setBorder(null);
        jPanel1.add(rdbNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 90, -1, -1));

        lblLoiGioiTinh.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiGioiTinh.setText("Chưa chọn giới tính");
        jPanel1.add(lblLoiGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 110, 150, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 153));
        jLabel8.setText("Điện thoại");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 150, -1, -1));

        txtDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDienThoai.setForeground(new java.awt.Color(0, 153, 153));
        txtDienThoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtDienThoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDienThoaiKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDienThoaiKeyTyped(evt);
            }
        });
        jPanel1.add(txtDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 180, 220, 20));

        lblLoiSDT.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiSDT.setText("Số điện thoại không chính xác");
        jPanel1.add(lblLoiSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 200, 190, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 153));
        jLabel9.setText("Họ tên");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, -1, -1));

        lblLoiHoTen.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiHoTen.setText("Họ tên không chính xác");
        jPanel1.add(lblLoiHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 200, 150, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 153));
        jLabel10.setText("Email");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(0, 153, 153));
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 220, 20));

        lblLoiEmail.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiEmail.setText("Email không chính xác");
        jPanel1.add(lblLoiEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 150, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 153, 153));
        jLabel11.setText("Hình");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 250, -1, -1));

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiaChi.setForeground(new java.awt.Color(0, 153, 153));
        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 280, 220, 20));

        lblLoiHinh.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiHinh.setText("Nhấp chọn hình");
        jPanel1.add(lblLoiHinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 280, 100, -1));

        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(0, 153, 153));
        txtPassword.setBorder(null);
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPasswordKeyTyped(evt);
            }
        });
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 220, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 153, 153));
        jLabel12.setText("Địa chỉ");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, -1, -1));

        lblHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblHinh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHinhMouseEntered(evt);
            }
        });
        jPanel1.add(lblHinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 280, 110, 140));

        lblLoiDiaChi.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiDiaChi.setText("Địa chỉ không chính xác");
        jPanel1.add(lblLoiDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 300, 150, -1));

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
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, -1, -1));

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
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, -1, -1));

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
        jPanel1.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 350, -1, -1));

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
        jPanel1.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 350, -1, -1));

        splTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                splTableMouseClicked(evt);
            }
        });

        tblNhanVien.setAutoCreateRowSorter(true);
        tblNhanVien.setForeground(new java.awt.Color(0, 153, 153));
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Nhân Viên", "Mật Khẩu", "Vai Trò", "Họ Tên", "Giới Tính", "Điện Thoại", "Email", "Địa Chỉ", "Hình"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblNhanVien.setAutoscrolls(false);
        tblNhanVien.setFocusable(false);
        tblNhanVien.setGridColor(new java.awt.Color(255, 255, 255));
        tblNhanVien.setOpaque(false);
        tblNhanVien.setRequestFocusEnabled(false);
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        splTable.setViewportView(tblNhanVien);

        jPanel1.add(splTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, 950, 250));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 220, 10));

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
        jPanel1.add(btnTroVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 350, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        new NhanVienDAO().tim(tblNhanVien, txtTimKiem.getText().trim());
        editColumnWidth();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void rdbNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbNhanVienActionPerformed

    private void txtDienThoaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDienThoaiKeyReleased
        new DungChung().xetSo(txtDienThoai);
    }//GEN-LAST:event_txtDienThoaiKeyReleased

    private void txtDienThoaiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDienThoaiKeyTyped
        if (txtDienThoai.getText().length() > 9) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDienThoaiKeyTyped

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        chonAnh();
    }//GEN-LAST:event_lblHinhMouseClicked

    private void lblHinhMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseEntered
        lblHinh.setCursor(new Cursor(HAND_CURSOR));
    }//GEN-LAST:event_lblHinhMouseEntered

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if (chucVu.equals("Trưởng phòng")) {
            xoa();
        } else {
            JOptionPane.showMessageDialog(this, "Chỉ admin được phép xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
       if(tblNhanVien.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "Chọn nhân viên cần cập nhật.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }else{
            capNhat();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
         moi();
        tblNhanVien.clearSelection();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        them();
    }//GEN-LAST:event_btnThemActionPerformed

    private void splTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_splTableMouseClicked
        // TODO add your handling code here:
        txtTimKiem.setText(null);
        dong = tblNhanVien.getSelectedRow();
        hienThi(dong);
    }//GEN-LAST:event_splTableMouseClicked

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        txtTimKiem.setText(null);
        dong = tblNhanVien.getSelectedRow();
        hienThi(dong);
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void txtPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyTyped
        if (txtPassword.getText().length() > 19) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPasswordKeyTyped

    private void tFMaNVKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tFMaNVKeyTyped
        if (tFMaNV.getText().length() > 9) {
            evt.consume();
        }
    }//GEN-LAST:event_tFMaNVKeyTyped

    private void tFMaNVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tFMaNVKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tFMaNVKeyReleased

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
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.ButtonGroup btnGroupGioiTinh;
    private javax.swing.ButtonGroup btnGroupVaiTro;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTroVe;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblLoiDiaChi;
    private javax.swing.JLabel lblLoiEmail;
    private javax.swing.JLabel lblLoiGioiTinh;
    private javax.swing.JLabel lblLoiHinh;
    private javax.swing.JLabel lblLoiHoTen;
    private javax.swing.JLabel lblLoiMaNV;
    private javax.swing.JLabel lblLoiMatKhau;
    private javax.swing.JLabel lblLoiSDT;
    private javax.swing.JLabel lblLoiVaiTro;
    private javax.swing.JRadioButton rdbNam;
    private javax.swing.JRadioButton rdbNhanVien;
    private javax.swing.JRadioButton rdbNu;
    private javax.swing.JRadioButton rdbQuanTri;
    private javax.swing.JScrollPane splTable;
    private javax.swing.JTextField tFMaNV;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDienThoai;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
