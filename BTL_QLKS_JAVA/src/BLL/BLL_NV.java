/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import GUI.thongbao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author ADMIN
 */
public class BLL_NV {

    public static boolean ktNV(DTO.DTO_NV nv) {
    if (nv.getTen().trim().isEmpty() || nv.getNgaysinh() == null || nv.getNgaylam() == null) {
        thongbao.thongbao("Không để trống tên, ngày sinh hoặc ngày làm", "Thông báo");
        return false;
    }

    if (nv.getTen().length() < 3) {
        thongbao.thongbao("Tên phải có ít nhất 3 ký tự", "Thông báo");
        return false;
    }

    String regexSDT = "\\d{10}";
    if (!nv.getSdt().matches(regexSDT)) {
        thongbao.thongbao("Số điện thoại phải đủ 10 chữ số", "Thông báo");
        return false;
    }

    // Kiểm tra định dạng ngày hợp lệ theo yyyy-MM-dd (nếu cần)
    if ( !isValidDateFormat(nv.getNgaylam())) {
        thongbao.thongbao(" ngày làm sai định dạng yyyy-MM-dd", "Thông báo");
        return false;
    }
    
    // Sau khi kiểm tra định dạng ngày
if (!isValidDateFormat(nv.getNgaysinh())) {
    thongbao.thongbao("Ngày sinh sai định dạng yyyy-MM-dd", "Thông báo");
    return false;
}

// ➕ Kiểm tra nhân viên >= 22 tuổi
try {
    LocalDate ngaysinh = LocalDate.parse(nv.getNgaysinh());
    LocalDate today = LocalDate.now();
    int tuoi = Period.between(ngaysinh, today).getYears();
    if (tuoi < 22) {
        thongbao.thongbao("Nhân viên phải từ 22 tuổi trở lên", "Thông báo");
        return false;
    }
} catch (Exception e) {
    thongbao.thongbao("Ngày sinh không hợp lệ", "Thông báo");
    return false;
}

    if (ChuyenDoi.DinhDangSo(nv.getLuong()).trim().isEmpty()) {
        thongbao.thongbao("Nhập lương cho nhân viên", "Thông báo");
        return false;
    }

    return true;
}
    private static boolean isValidDateFormat(String dateStr) {
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        sdf.parse(dateStr);
        return true;
    } catch (ParseException e) {
        return false;
    }
}

    public static boolean ktNV_sua(DTO.DTO_NV nv_sua){
        return ktNV(nv_sua);
    }
           
}
