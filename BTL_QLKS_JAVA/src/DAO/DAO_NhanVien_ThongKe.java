/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import GUI.thongbao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author huynh
 */
public class DAO_NhanVien_ThongKe {
    private static final DecimalFormat df = new DecimalFormat("#,### VND");

    public void loadNhanVienData(JTable table, JTextField tongLuongField, JTextField trungBinhLuongField) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã NV");
        model.addColumn("Tên Nhân Viên");
        model.addColumn("Lương");

        String query = "SELECT manhanvien, tennhanvien, luong FROM nhanvien";
        ResultSet rs = connection.Getdata(query);
        try {
            while (rs != null && rs.next()) {
                int maNV = rs.getInt("manhanvien");
                String tenNV = rs.getString("tennhanvien");
                double luong = rs.getDouble("luong");
                model.addRow(new Object[]{maNV, tenNV, df.format(luong)});
            }
            table.setModel(model);
            if (rs != null) rs.close();
        } catch (SQLException e) {
            thongbao.thongbao("Lỗi khi lấy dữ liệu nhân viên: " + e.getMessage(), "Lỗi");
        }

        // Tổng lương
        rs = connection.Getdata("SELECT SUM(luong) AS tongluong FROM nhanvien");
        try {
            if (rs != null && rs.next()) {
                tongLuongField.setText(df.format(rs.getDouble("tongluong")));
            }
            if (rs != null) rs.close();
        } catch (SQLException e) {
            thongbao.thongbao("Lỗi khi tính tổng lương: " + e.getMessage(), "Lỗi");
        }

        // Lương trung bình
        rs = connection.Getdata("SELECT AVG(luong) AS trungbinhluong FROM nhanvien");
        try {
            if (rs != null && rs.next()) {
                trungBinhLuongField.setText(df.format(rs.getDouble("trungbinhluong")));
            }
            if (rs != null) rs.close();
        } catch (SQLException e) {
            thongbao.thongbao("Lỗi khi tính lương trung bình: " + e.getMessage(), "Lỗi");
        }
    }
    
    public void drawLuongNhanVienChart(JPanel chartPanel) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    // ✅ Lấy 10 nhân viên có lương cao nhất
    String query = "SELECT tennhanvien, luong FROM nhanvien ORDER BY luong DESC LIMIT 5";
    ResultSet rs = connection.Getdata(query);

    try {
        while (rs != null && rs.next()) {
            String tenNV = rs.getString("tennhanvien");
            double luong = rs.getDouble("luong");
            dataset.addValue(luong, "Lương", tenNV);
        }
        if (rs != null) rs.close();
    } catch (SQLException e) {
        thongbao.thongbao("Lỗi tạo dữ liệu biểu đồ: " + e.getMessage(), "Lỗi");
        return;
    }

    JFreeChart barChart = ChartFactory.createBarChart(
            "Top 5 Nhân Viên Lương Cao Nhất",
            "Tên Nhân Viên", "Lương (VND)",
            dataset, PlotOrientation.VERTICAL,
            true, true, false
    );

    ChartPanel chart = new ChartPanel(barChart);
    chartPanel.removeAll();
    chartPanel.setLayout(new java.awt.BorderLayout());
    chartPanel.add(chart, java.awt.BorderLayout.CENTER);
    chartPanel.revalidate();
    chartPanel.repaint();
}

}
