package DAO;

import GUI.thongbao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class DAO_DoanhThu {

    private static final DecimalFormat df = new DecimalFormat("#,### VND");

    // Hiển thị dữ liệu trong JTable theo tháng và năm
   public void viewDataInTableByDateRange(String from, String to, JTable table) {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Mã Phiếu Thuê");
    model.addColumn("Tên Phòng");
    model.addColumn("Tên Khách Hàng");
    model.addColumn("Ngày Thuê");
    model.addColumn("Số Ngày Thuê");
    model.addColumn("Giá Phòng");
    model.addColumn("Thành Tiền");

    String query = "SELECT maphieuThue, tenphong, tenKH, ngaythue, soNgaythue, giaPhong " +
                   "FROM phieuthue " +
                   "WHERE ngaythue BETWEEN '" + from + "' AND '" + to + "'";

    ResultSet rs = connection.Getdata(query);
    try {
        while (rs.next()) {
            int soNgay = rs.getInt("soNgaythue");
            double gia = rs.getDouble("giaPhong");
            double thanhTien = soNgay * gia;

            model.addRow(new Object[]{
                rs.getInt("maphieuThue"),
                rs.getString("tenphong"),
                rs.getString("tenKH"),
                rs.getDate("ngaythue").toString(),
                soNgay,
                df.format(gia),
                df.format(thanhTien)
            });
        }
        rs.close();
    } catch (SQLException e) {
        thongbao.thongbao("Lỗi truy vấn dữ liệu: " + e.getMessage(), "Lỗi");
    }

    table.setModel(model);
}


    // Tính tổng doanh thu và hiển thị trong JTextField
    public void calculateTotalPriceByDateRange(String from, String to, JTextField price) {
    double total = 0;
    String query = "SELECT giaPhong, soNgaythue FROM phieuthue " +
                   "WHERE ngaythue BETWEEN '" + from + "' AND '" + to + "'";

    ResultSet rs = connection.Getdata(query);
    try {
        while (rs != null && rs.next()) {
            double giaPhong = rs.getDouble("giaPhong");
            int soNgaythue = rs.getInt("soNgaythue");
            total += soNgaythue * giaPhong;
        }
        if (rs != null) rs.close();
    } catch (SQLException e) {
        thongbao.thongbao("Lỗi tính tổng doanh thu: " + e.getMessage(), "Lỗi");
    }
    price.setText(df.format(total));
}


    // Vẽ biểu đồ đường (Line Chart) sử dụng JFreeChart
    public void viewDataInLineChartByDateRange(String from, String to, JPanel chartPanel) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    String query = "SELECT DATE(ngaythue) AS ngay, giaPhong, soNgaythue FROM phieuthue " +
                   "WHERE ngaythue BETWEEN '" + from + "' AND '" + to + "' ORDER BY ngay";

    ResultSet rs = connection.Getdata(query);
    try {
        while (rs != null && rs.next()) {
            String ngay = rs.getDate("ngay").toString(); // yyyy-MM-dd
            double giaPhong = rs.getDouble("giaPhong");
            int soNgaythue = rs.getInt("soNgaythue");
            double doanhThu = soNgaythue * giaPhong;
            dataset.addValue(doanhThu, "Doanh Thu", ngay);
        }
        if (rs != null) rs.close();
    } catch (SQLException e) {
        thongbao.thongbao("Lỗi tạo biểu đồ: " + e.getMessage(), "Lỗi");
    }

    // Tạo biểu đồ
    JFreeChart lineChart = ChartFactory.createLineChart(
            "Doanh Thu Từ " + from + " đến " + to,
            "Ngày", "Doanh Thu (VND)", dataset, PlotOrientation.VERTICAL,
            true, true, false
    );

    ChartPanel chart = new ChartPanel(lineChart);
    chartPanel.removeAll();
    chartPanel.setLayout(new java.awt.BorderLayout());
    chartPanel.add(chart, java.awt.BorderLayout.CENTER);
    chartPanel.revalidate();
    chartPanel.repaint();
}

    
    public void viewDataDefault(JTable table, JPanel chartLine) {
        // Khởi tạo mô hình bảng
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tháng/Năm");
        model.addColumn("Tổng Số Phiếu Thuê");
        model.addColumn("Tổng Doanh Thu");

        // Truy vấn dữ liệu từ bảng phieuthue, tổng hợp theo tháng với subquery
        String query = "SELECT CONCAT(month, '/', year) AS thang_nam, " +
                       "COUNT(*) AS so_phieu, " +
                       "SUM(giaPhong * COALESCE(soNgaythue, 0)) AS doanh_thu " +
                       "FROM (SELECT MONTH(ngaythue) AS month, YEAR(ngaythue) AS year, giaPhong, soNgaythue " +
                       "      FROM `phieuthue`) AS temp " +
                       "GROUP BY year, month " +
                       "ORDER BY year, month";
        ResultSet rs = connection.Getdata(query);

        try {
            while (rs != null && rs.next()) {
                String thangNam = rs.getString("thang_nam");
                int soPhieu = rs.getInt("so_phieu");
                double doanhThu = rs.getDouble("doanh_thu");

                model.addRow(new Object[]{thangNam, soPhieu, df.format(doanhThu)});
            }
            if (rs != null) rs.close();
        } catch (SQLException e) {
            thongbao.thongbao("Lỗi truy vấn dữ liệu: " + e.getMessage(), "Lỗi");
        }
        table.setModel(model);

        // Chuẩn bị dữ liệu cho biểu đồ đường
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        rs = connection.Getdata(query);

        try {
            while (rs != null && rs.next()) {
                String thangNam = rs.getString("thang_nam");
                double doanhThu = rs.getDouble("doanh_thu");
                dataset.addValue(doanhThu, "Doanh Thu", thangNam);
            }
            if (rs != null) rs.close();
        } catch (SQLException e) {
            thongbao.thongbao("Lỗi tạo biểu đồ: " + e.getMessage(), "Lỗi");
        }

        // Tạo biểu đồ đường
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Doanh Thu Theo Tháng (Tất Cả Dữ Liệu)",
                "Tháng/Năm", "Doanh Thu (VND)", dataset, PlotOrientation.VERTICAL,
                true, true, false
        );

        // Thêm biểu đồ vào ChartPanel
        ChartPanel chart = new ChartPanel(lineChart);
        chartLine.removeAll();
        chartLine.setLayout(new java.awt.BorderLayout());
        chartLine.add(chart, java.awt.BorderLayout.CENTER);
        chartLine.revalidate();
        chartLine.repaint();
    }
}