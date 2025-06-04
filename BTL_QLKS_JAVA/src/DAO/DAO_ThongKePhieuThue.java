/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import GUI.thongbao;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
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
                   "GROUP BY nv.tennhanvien " +
                   "ORDER BY so_phieu DESC " +
                   "LIMIT 5";

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

    // Tạo biểu đồ
    JFreeChart barChart = ChartFactory.createBarChart(
            "Top 5 Nhân Viên Có Nhiều Phiếu Thuê Nhất",
            "Nhân Viên", "Số Phiếu Thuê",
            dataset, PlotOrientation.VERTICAL,
            true, true, false
    );

    // ==== TÙY CHỈNH GIAO DIỆN ====
    CategoryPlot plot = barChart.getCategoryPlot();
    plot.setBackgroundPaint(Color.white);
    plot.setRangeGridlinePaint(Color.gray);
    plot.setOutlineVisible(false);

    // BarRenderer để chỉnh cột
    BarRenderer renderer = (BarRenderer) plot.getRenderer();
    renderer.setSeriesPaint(0, new Color(52, 152, 219)); // Màu xanh đẹp
    renderer.setMaximumBarWidth(0.1); // Độ rộng cột

    // Phông chữ đẹp hơn
    Font titleFont = new Font("Arial", Font.BOLD, 18);
    Font labelFont = new Font("Arial", Font.PLAIN, 14);

    barChart.getTitle().setFont(titleFont);
    plot.getDomainAxis().setLabelFont(labelFont);
    plot.getDomainAxis().setTickLabelFont(labelFont);
    plot.getRangeAxis().setLabelFont(labelFont);
    plot.getRangeAxis().setTickLabelFont(labelFont);

    barChart.getLegend().setItemFont(labelFont);

    // ==== Hiển thị ====
    ChartPanel chart = new ChartPanel(barChart);
    chart.setPreferredSize(new Dimension(chartPanel.getWidth(), chartPanel.getHeight()));
    chartPanel.removeAll();
    chartPanel.setLayout(new BorderLayout());
    chartPanel.add(chart, BorderLayout.CENTER);
    chartPanel.revalidate();
    chartPanel.repaint();
}

}
