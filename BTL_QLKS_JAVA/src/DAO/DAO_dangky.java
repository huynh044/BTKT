/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import GUI.thongbao;

/**
 *
 * @author ADMIN
 */
public class DAO_dangky {

    public static int DAO_dangky(DTO.DTO_user DN1) {
        String cautruyvan = "INSERT INTO `user` "
                + "           (`tenuser`, "
                + "           `password`, "
                + "           `hovaten`, "
                + "           `email`) "
                + "     VALUES "
                + "           (N'" + DN1.getTendangnhap() + "' "
                + "           ,N'" + DN1.getMk() + "' "
                + "           ,N'" + DN1.getHovaten() + "' "
                + "           ,N'" + DN1.getEmail() + "')";
        int kq = connection.ExecuteTruyVan(cautruyvan);
        if (kq > 0) {
            thongbao.thongbao("đăng ký thành công", "thông báo");
        } else {
            thongbao.thongbao("đăng ký không thành công", "thông báo");
        }
        return kq;
    }
}