/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.DTO_KH;
import DTO.mycombobox;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class GUI_khachhang extends javax.swing.JFrame {

    /**
     * Creates new form GUI_khachhang
     */
    public GUI_khachhang() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_ten = new javax.swing.JTextField();
        txt_sdt = new javax.swing.JTextField();
        txt_cmnd = new javax.swing.JTextField();
        spn_tuoi = new javax.swing.JSpinner();
        cbb_gioitinh = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_KH = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        cbb_loaiKH = new javax.swing.JComboBox<>();
        txt_timkiem = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Khách hàng");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Khách hàng");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 19, -1, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Tên khách hàng:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Tuổi:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Giới tính:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Số điện thoại:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Số CMND:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, -1, -1));
        getContentPane().add(txt_ten, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 95, 242, 35));
        getContentPane().add(txt_sdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 251, 242, 37));
        getContentPane().add(txt_cmnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 302, 242, 34));

        spn_tuoi.setModel(new javax.swing.SpinnerNumberModel(1, null, null, 1));
        getContentPane().add(spn_tuoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 152, 242, 28));

        cbb_gioitinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", "Khác" }));
        getContentPane().add(cbb_gioitinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 201, 242, 32));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(51, 51, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 255, 204));
        jButton1.setText("Thêm");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 130, 43));

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Xóa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 130, 40));

        btn_sua.setBackground(new java.awt.Color(51, 204, 255));
        btn_sua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_sua.setText("Sửa");
        btn_sua.setEnabled(false);
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_sua, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 131, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(496, 95, 195, 258));

        tbl_KH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã KH", "Loại KH", "Tên", "Tuổi", "Giới tính", "Số điện thoại", "Số CMND"
            }
        ));
        tbl_KH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_KHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_KH);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 455, 731, 211));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Loại KH:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, -1, -1));

        getContentPane().add(cbb_loaiKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 354, 242, 35));

        txt_timkiem.setName("Tên"); // NOI18N
        txt_timkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timkiemKeyReleased(evt);
            }
        });
        getContentPane().add(txt_timkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 281, 33));
        txt_timkiem.getAccessibleContext().setAccessibleName("");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Tìm kiếm");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 414, -1, 22));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", " " }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 410, 90, 30));

        jButton3.setText("Lọc");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 410, -1, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        loadData_loaiKH();

        BLL.BLL_dodulieuKH.dodulieuKH(tbl_KH);
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DTO.DTO_KH kh = new DTO_KH();
        kh.setTenKH(txt_ten.getText());
        kh.setTuoi(Integer.parseInt(spn_tuoi.getModel().getValue().toString()));
        kh.setGioitinh(cbb_gioitinh.getSelectedItem().toString());
        kh.setSdt(txt_sdt.getText());
        kh.setCMND(txt_cmnd.getText());
        DTO.mycombobox mb = (DTO.mycombobox) cbb_loaiKH.getSelectedItem();
        String maloaiKH = mb.value.toString();
        boolean kt = BLL.BLL_KH.ktKH(kh);
        if (kt) {
            DAO.DAO_khachhang.themKH(kh, maloaiKH);
            BLL.BLL_dodulieuKH.dodulieuKH(tbl_KH);
        } else {

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbl_KHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_KHMouseClicked
        int dongchon = tbl_KH.getSelectedRowCount();
        if (dongchon == 1) {
            btn_sua.setEnabled(true);
            int vitri = tbl_KH.getSelectedRow();

            // Gán tên
            Object tenKHVal = tbl_KH.getValueAt(vitri, 3);
            txt_ten.setText(tenKHVal != null ? tenKHVal.toString() : "");

            // Gán tuổi cho Spinner (phải ép int và kiểm tra null)
            Object tuoiVal = tbl_KH.getValueAt(vitri, 4);
            try {
                int tuoi = (tuoiVal != null) ? Integer.parseInt(tuoiVal.toString()) : 18;
                spn_tuoi.setValue(tuoi);
            } catch (Exception e) {
                spn_tuoi.setValue(18); // fallback an toàn
            }

            // Gán giới tính
            Object gtVal = tbl_KH.getValueAt(vitri, 5);
            cbb_gioitinh.setSelectedItem(gtVal != null ? gtVal.toString() : "");

            // Gán số điện thoại
            Object sdtVal = tbl_KH.getValueAt(vitri, 6);
            txt_sdt.setText(sdtVal != null ? sdtVal.toString() : "");

            // Gán CMND
            Object cmndVal = tbl_KH.getValueAt(vitri, 7);
            txt_cmnd.setText(cmndVal != null ? cmndVal.toString() : "");

            // Chọn loại khách hàng từ ComboBox
            Object loaiKHVal = tbl_KH.getValueAt(vitri, 2); // cột loại KH
            if (loaiKHVal != null) {
                String tenLoaiKH = loaiKHVal.toString();
                DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbb_loaiKH.getModel();
                for (int i = 0; i < cbb_loaiKH.getItemCount(); i++) {
                    DTO.mycombobox mb = (mycombobox) cbbModel.getElementAt(i);
                    if (mb.text.toString().equals(tenLoaiKH)) {
                        cbb_loaiKH.setSelectedIndex(i);
                        break;
                    }
                }
            }
        } else {
            btn_sua.setEnabled(false);
        }

    }//GEN-LAST:event_tbl_KHMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int nutbam = JOptionPane.showConfirmDialog(new JFrame(), "bạn chắc chắn xóa?", "xóA", JOptionPane.YES_NO_OPTION);
        if (nutbam == JOptionPane.YES_OPTION) {
            int cacdong[] = tbl_KH.getSelectedRows();
            for (int i = 0; i < cacdong.length; i++) {
                int maKH = Integer.parseInt(tbl_KH.getValueAt(cacdong[i], 1).toString());
                DAO.DAO_khachhang.xoaKH(maKH);
            }
            BLL.BLL_dodulieuKH.dodulieuKH(tbl_KH);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        if (tbl_KH.getSelectedRowCount() != 1) {
            thongbao.thongbao("chỉ chọn 1 dòng để sửa", "thông báo");
            return;
        } else {
            int vitri = tbl_KH.getSelectedRow();

            DTO.DTO_KH kh_sua = new DTO_KH();
            kh_sua.setTenKH(txt_ten.getText());
            kh_sua.setTuoi(Integer.parseInt(spn_tuoi.getModel().getValue().toString()));
            kh_sua.setGioitinh(cbb_gioitinh.getSelectedItem().toString());
            kh_sua.setSdt(txt_sdt.getText());
            kh_sua.setCMND(txt_cmnd.getText());
            DTO.mycombobox mb = (DTO.mycombobox) cbb_loaiKH.getSelectedItem();
            String maloaiKH = mb.value.toString();
            String maKH=tbl_KH.getValueAt(vitri, 1).toString();
            boolean kt = BLL.BLL_KH.ktKH(kh_sua);
            if (kt) {
                DAO.DAO_khachhang.suaKH(kh_sua, maloaiKH, maKH);
                BLL.BLL_dodulieuKH.dodulieuKH(tbl_KH);
            } else {

            }
        }
    }//GEN-LAST:event_btn_suaActionPerformed

    private void txt_timkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timkiemKeyReleased
      String tuKhoa=txt_timkiem.getText();
      BLL.BLL_dodulieuKH.dodulieuTimKiem(tbl_KH, tuKhoa);
    }//GEN-LAST:event_txt_timkiemKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String gender = jComboBox1.getSelectedItem().toString();
        DAO.DAO_khachhang.filterAllCustomerByGender(gender, tbl_KH);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(GUI_khachhang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_khachhang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_khachhang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_khachhang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_khachhang().setVisible(true);
            }
        });
    }

    private void loadData_loaiKH() {
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbb_loaiKH.getModel();
        cbbModel.removeAllElements();

        ResultSet rs = DAO.DAO_khachhang.loaiKH();
        try {
            while (rs.next()) {
                int maLoai = rs.getInt("maloaiKH");
                String tenLoai = rs.getString("tenloaiKH");

                mycombobox mb = new mycombobox(maLoai, tenLoai);
                cbbModel.addElement(mb);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            thongbao.thongbao("Lỗi khi tải loại khách hàng: " + ex.getMessage(), "Lỗi");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_sua;
    private javax.swing.JComboBox<String> cbb_gioitinh;
    private javax.swing.JComboBox<String> cbb_loaiKH;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spn_tuoi;
    public javax.swing.JTable tbl_KH;
    private javax.swing.JTextField txt_cmnd;
    private javax.swing.JTextField txt_sdt;
    private javax.swing.JTextField txt_ten;
    private javax.swing.JTextField txt_timkiem;
    // End of variables declaration//GEN-END:variables
}
