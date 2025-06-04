/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import GUI.thongbao;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class DAO_khachhang {

    public static ResultSet loaiKH() {
        String cautruyvan = "SELECT `maloaiKH`, "
                + "      `tenloaiKH`, "
                + "      `mota` "
                + "  FROM `loaiKH`";
        return connection.Getdata(cautruyvan);
    }

    public static ResultSet layloaitheoMa(int maloai) {
        String cautruyvan = "SELECT `maloaiKH`, "
                + "      `tenloaiKH`, "
                + "      `mota` "
                + "  FROM `loaiKH` "
                + "  WHERE `maloaiKH` = " + maloai;
        return connection.Getdata(cautruyvan);
    }

    public static ResultSet laydulieuKH() {
        String cautruyvan = "SELECT `maKH`, "
                + "      `maloaiKH`, "
                + "      `tenKH`, "
                + "      `tuoi`, "
                + "      `gioitinh`, "
                + "      `sdt`, "
                + "      `soCMND`, "
                + "      `ghichu` "
                + "  FROM `KH`";
        return connection.Getdata(cautruyvan);
    }

    public static void themKH(DTO.DTO_KH kh, String maloaiKH) {
        String cautruyvan = "INSERT INTO `KH` "
                + "           (`maloaiKH`, "
                + "           `tenKH`, "
                + "           `tuoi`, "
                + "           `gioitinh`, "
                + "           `sdt`, "
                + "           `soCMND`) "
                + "     VALUES "
                + "           (" + maloaiKH + " "
                + "           ,N'" + kh.getTenKH() + "' "
                + "           , " + kh.getTuoi()
                + "           ,N'" + kh.getGioitinh() + "' "
                + "           ,'" + kh.getSdt() + "' "
                + "           ,'" + kh.getCMND() + "' "
                + "           )";
        int kq = connection.ExecuteTruyVan(cautruyvan);
        if (kq > 0) {
            thongbao.thongbao("thêm thành công", "thông báo");
        } else {
            thongbao.thongbao("thêm không thành công", "thông báo");
        }
    }

    public static void xoaKH(int maKH) {
        String cautruyvan = "DELETE FROM `KH` "
                + "      WHERE `maKH` = " + maKH;
        int kq = connection.ExecuteTruyVan(cautruyvan);
        if (kq > 0) {
            thongbao.thongbao("xóa thành công", "thông báo");
        } else {
        }
    }

    public static void suaKH(DTO.DTO_KH kh_sua, String maloaiKH, String maKH) {
        String cautruyvan = "UPDATE `KH` "
                + "   SET `maloaiKH` = " + maloaiKH
                + "      ,`tenKH` = N'" + kh_sua.getTenKH() + "' "
                + "      ,`tuoi` = " + kh_sua.getTuoi()
                + "      ,`gioitinh` = N'" + kh_sua.getGioitinh() + "' "
                + "      ,`sdt` = '" + kh_sua.getSdt() + "' "
                + "      ,`soCMND` = N'" + kh_sua.getCMND() + "' "
                + "      ,`ghichu` = N'' "
                + " WHERE `maKH` = " + maKH;
        int kq = connection.ExecuteTruyVan(cautruyvan);
        if (kq > 0) {
            thongbao.thongbao("sửa thành công", "thông báo");
        } else {
        }
    }

    public static ResultSet timkiemKH(String tukhoa) {
        String cautruyvan = "SELECT `maKH`, "
                + "      `maloaiKH`, "
                + "      `tenKH`, "
                + "      `tuoi`, "
                + "      `gioitinh`, "
                + "      `sdt`, "
                + "      `soCMND`, "
                + "      `ghichu` "
                + "  FROM `KH` "
                + "  WHERE `tenKH` LIKE N'%" + tukhoa + "%'";
        return connection.Getdata(cautruyvan);
    }

    public static ResultSet LoaiKH() {
        String cautruyvan = "SELECT * FROM `loaiKH`";
        return connection.Getdata(cautruyvan);
    }

    public static void themLOaiKH(String tenLoai, String mota) {
        String cautruyvan = "INSERT INTO `loaiKH` "
                + "           (`tenloaiKH`, "
                + "           `mota`) "
                + "     VALUES "
                + "           (N'" + tenLoai + "', "
                + "           N'" + mota + "')";
        int kq = connection.ExecuteTruyVan(cautruyvan);
        if (kq > 0) {
            thongbao.thongbao("thêm thành công", "thông báo");
        } else {
        }
    }

    public static void xoaLoaiKH(int maLOai) {
        String cautruyvan = "DELETE FROM `loaiKH` "
                + "      WHERE `maloaiKH` = " + maLOai;
        int kq = connection.ExecuteTruyVan(cautruyvan);
        if (kq > 0) {
            thongbao.thongbao("xóa thành công", "thông báo");
        } else {
        }
    }

    public static void updateLoai(int malOai, String tenLoai, String mota) {
        String cautruyvan = "UPDATE `loaiKH` "
                + "   SET `tenloaiKH` = N'" + tenLoai + "' "
                + "      ,`mota` = N'" + mota + "' "
                + " WHERE `maloaiKH` = " + malOai;
        int kq = connection.ExecuteTruyVan(cautruyvan);
        if (kq > 0) {
            thongbao.thongbao("Sửa thành công", "thông báo");
        } else {
        }
    }
    
    public static void filterAllCustomerByGender(String gender, JTable table) {
    DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
    tblModel.setRowCount(0); // Xóa dữ liệu cũ

    Object[] obj = new Object[8];

    // Lấy dữ liệu theo giới tính
    String query = "SELECT * FROM KH WHERE gioitinh = '" + gender + "'";
    ResultSet rs = connection.Getdata(query);

    try {
        while (rs.next()) {
            obj[0] = tblModel.getRowCount(); // STT
            obj[1] = rs.getInt("maKH");

            // Lấy tên loại khách hàng
            int maloai = rs.getInt("maloaiKH");
            ResultSet rsLoai = DAO.DAO_khachhang.layloaitheoMa(maloai);
            obj[2] = (rsLoai.next()) ? rsLoai.getString("tenloaiKH") : "";

            obj[3] = rs.getString("tenKH");
            obj[4] = rs.getInt("tuoi");
            obj[5] = rs.getString("gioitinh");
            obj[6] = rs.getString("sdt");
            obj[7] = rs.getString("soCMND");

            tblModel.addRow(obj);
        }

        if (rs != null) rs.close();
    } catch (SQLException ex) {
        thongbao.thongbao("Lỗi khi lọc khách hàng: " + ex.getMessage(), "Lỗi");
    }
}

}