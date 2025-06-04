/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import GUI.thongbao;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author huynh
 */
public class DAO_KhachHang_ThongKe {
    public void viewCustomerByType(JPanel chartPanel) {
    DefaultPieDataset dataset = new DefaultPieDataset();

    String query = "SELECT lk.tenloaiKH, COUNT(kh.maKH) AS so_luong " +
                   "FROM loaiKH lk " +
                   "LEFT JOIN KH kh ON lk.maloaiKH = kh.maloaiKH " +
                   "GROUP BY lk.maloaiKH, lk.tenloaiKH " +
                   "ORDER BY lk.maloaiKH";

    ResultSet rs = connection.Getdata(query);

    try {
        while (rs != null && rs.next()) {
            String tenLoaiKH = rs.getString("tenloaiKH");
            int soLuong = rs.getInt("so_luong");

            // ✅ Bỏ qua loại KH không có ai
            if (soLuong > 0) {
                dataset.setValue(tenLoaiKH, soLuong);
            }
        }
        if (rs != null) rs.close();
    } catch (SQLException e) {
        thongbao.thongbao("Lỗi truy vấn dữ liệu: " + e.getMessage(), "Lỗi");
    }

    JFreeChart pieChart = ChartFactory.createPieChart(
            "Phân Bố Khách Hàng Theo Loại",
            dataset,
            true, true, false
    );

    ChartPanel chart = new ChartPanel(pieChart);
    chartPanel.removeAll();
    chartPanel.setLayout(new java.awt.BorderLayout());
    chartPanel.add(chart, java.awt.BorderLayout.CENTER);
    chartPanel.revalidate();
    chartPanel.repaint();
}

    
    public void viewCustomerByGender(JPanel chartPie) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        String query = "SELECT gioitinh, COUNT(*) AS soluong FROM KH GROUP BY gioitinh";
        ResultSet rs = connection.Getdata(query);

        try {
            while (rs != null && rs.next()) {
                String gioitinh = rs.getString("gioitinh");
                int soluong = rs.getInt("soluong");
                dataset.setValue(gioitinh != null ? gioitinh : "Không rõ", soluong);
            }
            if (rs != null) rs.close();
        } catch (SQLException e) {
            thongbao.thongbao("Lỗi khi lấy dữ liệu giới tính: " + e.getMessage(), "Lỗi");
            return;
        }

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Tỷ Lệ Giới Tính Khách Hàng",
                dataset,
                true, true, false
        );

        ChartPanel chart = new ChartPanel(pieChart);
        chartPie.removeAll();
        chartPie.setLayout(new java.awt.BorderLayout());
        chartPie.add(chart, java.awt.BorderLayout.CENTER);
        chartPie.revalidate();
        chartPie.repaint();
    }
}
