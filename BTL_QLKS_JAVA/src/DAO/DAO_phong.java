/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;

/**
 *
 * @author ADMIN
 */
public class DAO_phong {

    public static ResultSet laydulieuPhong() {
        String cautruyvan = "SELECT * FROM `phong`";
        return connection.Getdata(cautruyvan);
    }

    public static ResultSet laydulieu_Theoten(String tenPhong) {
        String cautruyvan = "SELECT * FROM `phong` WHERE `tenphong` = N'" + tenPhong + "'";
        return connection.Getdata(cautruyvan);
    }
}