/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import GUI.thongbao;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author huynh
 */
public class DAO_ThongKePhieuThue {
    public void drawPhieuThueBarChart(JPanel chartPanel) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String query = "SELECT nv.tennhanvien, COUNT(pt.maphieuThue) AS so_phieu " +
                       "FROM nhanvien nv " +
                       "LEFT JOIN phieuthue pt ON nv.manhanvien = pt.maNV " +
                       "GROUP BY nv.tennhanvien";

        ResultSet rs = connection.Getdata(query);

        try {
            while (rs != null && rs.next()) {
                String tenNV = rs.getString("tennhanvien");
                int soPhieu = rs.getInt("so_phieu");
                dataset.addValue(soPhieu, "Số Phiếu", tenNV);
            }
            if (rs != null) rs.close();
        } catch (SQLException e) {
            thongbao.thongbao("Lỗi tạo dữ liệu biểu đồ: " + e.getMessage(), "Lỗi");
            return;
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Số Lượng Phiếu Thuê Theo Nhân Viên",
                "Nhân Viên", "Số Phiếu Thuê",
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
