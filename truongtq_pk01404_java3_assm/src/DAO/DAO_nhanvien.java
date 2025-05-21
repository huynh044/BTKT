/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import GUI.thongbao;
import java.sql.ResultSet;

/**
 *
 * @author ADMIN
 */
public class DAO_nhanvien {

    public static ResultSet laydulieuNV() {
        String cautruyvan = "SELECT `manhanvien`, "
                + "      `tennhanvien`, "
                + "      `gioitinh`, "
                + "      `ngaysinh`, "
                + "      `sdt`, "
                + "      `ngaylam`, "
                + "      `luong`, "
                + "      `diachi`, "
                + "      `ghichu` "
                + "  FROM `nhanvien`";
        return connection.Getdata(cautruyvan);
    }

    public static void themNV(DTO.DTO_NV nv) {
        String cautruyvan = "INSERT INTO `nhanvien` "
                + "           (`tennhanvien`, "
                + "           `gioitinh`, "
                + "           `ngaysinh`, "
                + "           `sdt`, "
                + "           `ngaylam`, "
                + "           `luong`, "
                + "           `diachi`, "
                + "           `ghichu`) "
                + "     VALUES "
                + "           (N'" + nv.getTen() + "' "
                + "           ,N'" + nv.getGioitinh() + "' "
                + "           ,'" + nv.getNgaysinh() + "' "
                + "           ,'" + nv.getSdt() + "' "
                + "           ,'" + nv.getNgaylam() + "' "
                + "           , " + nv.getLuong()
                + "           ,N'" + nv.getDiachi() + "' "
                + "           ,N'" + nv.getGhichu() + "')";
        int kq = connection.ExecuteTruyVan(cautruyvan);
        if (kq > 0) {
            thongbao.thongbao("THêm thành công", "thông báo");
        } else {
            thongbao.thongbao("THêm  KHÔNG thành công", "thông báo");
        }
    }

    public static void xoaNV(int maNV) {
        String cautruyvan = "DELETE FROM `nhanvien` "
                + "      WHERE `manhanvien` = " + maNV;
        int kq = connection.ExecuteTruyVan(cautruyvan);
        if (kq > 0) {
            thongbao.thongbao("Xóa thành công", "thông báo");
        }
    }

    public static void suaNV(DTO.DTO_NV nv_sua, String maNV) {
        String cautruyvan = "UPDATE `nhanvien` "
                + "   SET `tennhanvien` = N'" + nv_sua.getTen() + "' "
                + "      ,`gioitinh` = N'" + nv_sua.getGioitinh() + "' "
                + "      ,`ngaysinh` = '" + nv_sua.getNgaysinh() + "' "
                + "      ,`sdt` = '" + nv_sua.getSdt() + "' "
                + "      ,`ngaylam` ='" + nv_sua.getNgaylam() + "' "
                + "      ,`luong` = " + nv_sua.getLuong()
                + "      ,`diachi` = N'" + nv_sua.getDiachi() + "' "
                + "      ,`ghichu` = N'" + nv_sua.getGhichu() + "' "
                + " WHERE `manhanvien` = " + maNV;
        int kq = connection.ExecuteTruyVan(cautruyvan);
        if (kq > 0) {
            thongbao.thongbao("sửa thành công", "thông báo");
        } else {
        }
    }

    public static ResultSet timkiemNV(String tuKhoa) {
        String cautruyvan = "SELECT `manhanvien`, "
                + "      `tennhanvien`, "
                + "      `gioitinh`, "
                + "      `ngaysinh`, "
                + "      `sdt`, "
                + "      `ngaylam`, "
                + "      `luong`, "
                + "      `diachi`, "
                + "      `ghichu` "
                + "  FROM `nhanvien` "
                + "  WHERE `tennhanvien` LIKE N'%" + tuKhoa + "%' "
                + "  OR `sdt` LIKE '%" + tuKhoa + "%' "
                + "  OR `diachi` LIKE N'%" + tuKhoa + "%'";
        return connection.Getdata(cautruyvan);
    }
}