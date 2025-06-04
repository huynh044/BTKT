/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import GUI.thongbao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author ADMIN
 */
public class DAO_phieuthue {

    public static void them(DTO.DTO_phieuthue pt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngayThue = sdf.format(pt.getNgaythue());
        String cautruyvan = "INSERT INTO `phieuthue` "
                + "           (`maphong`, "
                + "           `tenphong`, "
                + "           `tennhanvien`, "
                + "           `giaPhong`, "
                + "           `tenKH`, "
                + "           `tuoi`, "
                + "           `sdt`, "
                + "           `soCMND`, "
                + "           `ngaythue`) "
                + "     VALUES "
                + "           (" + pt.getMaphong()
                + "           ,N'" + pt.getTenphong() + "' "
                + "           ,N'" + pt.getTenuser() + "' "
                + "           , " + pt.getGiaphong()
                + "           ,N'" + pt.getTenKH() + "' "
                + "           , " + pt.getTuoi()
                + "           ,'" + pt.getSdt() + "' "
                + "           ,'" + pt.getCMND() + "' "
                + "           ,'" + ngayThue + "')";
        int kq = connection.ExecuteTruyVan(cautruyvan);
        if (kq > 0) {
            thongbao.thongbao(" Đã lưu dữ liệu", "");
        } else {
            thongbao.thongbao("Có lỗi xảy ra khi thêm dữ liệu", "");
        }
    }
    
    public void getTenNhanVienByID(int id, JTextField tennhanvien) {
    String query = "SELECT tennhanvien FROM nhanvien WHERE manhanvien = " + id;
    ResultSet rs = connection.Getdata(query);

    try {
        if (rs != null && rs.next()) {
            tennhanvien.setText(rs.getString("tennhanvien"));
        } else {
            tennhanvien.setText("Không tìm thấy");
        }
        if (rs != null) rs.close();
    } catch (SQLException e) {
        thongbao.thongbao("Lỗi lấy tên nhân viên: " + e.getMessage(), "Lỗi");
    }
    }
    public void getAllIdOfNhanVien(JComboBox<String> manhanvien) {
    manhanvien.removeAllItems(); // Xóa dữ liệu cũ (nếu có)
    String query = "SELECT manhanvien FROM nhanvien";
    ResultSet rs = connection.Getdata(query);

    try {
        while (rs != null && rs.next()) {
            manhanvien.addItem(String.valueOf(rs.getInt("manhanvien")));
        }
        if (rs != null) rs.close();
    } catch (SQLException e) {
        thongbao.thongbao("Lỗi lấy mã nhân viên: " + e.getMessage(), "Lỗi");
    }
}
    public void getInfoPhongByTenPhong(String tenPhong, JLabel MaPhong, JLabel GiaPhong) {
    String query = "SELECT maphong, gia FROM phong WHERE tenphong = N'" + tenPhong + "'";
    ResultSet rs = connection.Getdata(query);

    try {
        if (rs != null && rs.next()) {
            int ma = rs.getInt("maphong");
            double gia = rs.getDouble("gia");

            MaPhong.setText(String.valueOf(ma));
            GiaPhong.setText(String.format("%.0f", gia)); // Format đẹp VND
        } else {
            MaPhong.setText("Không tìm thấy");
            GiaPhong.setText("-");
        }
        if (rs != null) rs.close();
    } catch (SQLException e) {
        thongbao.thongbao("Lỗi lấy thông tin phòng: " + e.getMessage(), "Lỗi");
    }
}
public void getAllOfMaKHID(JComboBox<String> makh) {
    makh.removeAllItems();
    String query = "SELECT maKH FROM KH";

    ResultSet rs = connection.Getdata(query);
    try {
        while (rs != null && rs.next()) {
            makh.addItem(String.valueOf(rs.getInt("maKH")));
        }
        if (rs != null) rs.close();
    } catch (SQLException e) {
        thongbao.thongbao("Lỗi lấy danh sách mã khách hàng: " + e.getMessage(), "Lỗi");
    }
}
public void getInfoByMaKHID(int makh, JTextField tenKH, JTextField sđt, JTextField cmnd) {
    String query = "SELECT tenKH, sdt, soCMND FROM KH WHERE maKH = " + makh;

    ResultSet rs = connection.Getdata(query);
    try {
        if (rs != null && rs.next()) {
            tenKH.setText(rs.getString("tenKH"));
            sđt.setText(rs.getString("sdt"));
            cmnd.setText(rs.getString("soCMND"));
        } else {
            tenKH.setText("Không tìm thấy");
            sđt.setText("");
            cmnd.setText("");
        }
        if (rs != null) rs.close();
    } catch (SQLException e) {
        thongbao.thongbao("Lỗi truy vấn thông tin khách hàng: " + e.getMessage(), "Lỗi");
    }
}

public void updateStateRoom(String maPhong) {
    String query = "UPDATE phong SET trangthai = N'đang thuê' WHERE maphong = " + maPhong;
    int kq = connection.ExecuteTruyVan(query);

    if (kq > 0) {
        thongbao.thongbao("Cập nhật trạng thái phòng thành công", "Thông báo");
    } else {
        thongbao.thongbao("Không thể cập nhật trạng thái phòng", "Lỗi");
    }
}



}