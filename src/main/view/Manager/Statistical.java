package view.Manager;
import javax.swing.*;
import java.awt.*;
import java.util.Map;
import controller.StatisticsService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Statistical extends JPanel {

    private JPanel contentPanel;
    private JPanel chartPanel;

    private JComboBox<String> timeComboBox;
    private JToggleButton btnBar;
    private JToggleButton btnLine;
    private JToggleButton btnPie;
    private ButtonGroup chartButtonGroup;

    public Statistical() {
        setLayout(new BorderLayout());

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(255, 245, 204));

        JLabel title = new JLabel("Thống Kê Doanh Thu", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        contentPanel.add(title, BorderLayout.NORTH);

        JPanel controlPanel = new JPanel();

        // ComboBox chọn thời gian
        timeComboBox = new JComboBox<>(new String[]{"WEEK", "MONTH", "YEAR"});
        controlPanel.add(new JLabel("Thống kê theo: "));
        controlPanel.add(timeComboBox);

        // Nhóm nút chọn loại biểu đồ
        btnBar = new JToggleButton("Bar Chart");
        btnLine = new JToggleButton("Line Chart");
        btnPie = new JToggleButton("Pie Chart");

        chartButtonGroup = new ButtonGroup();
        chartButtonGroup.add(btnBar);
        chartButtonGroup.add(btnLine);
        chartButtonGroup.add(btnPie);

        // Mặc định chọn Bar Chart
        btnBar.setSelected(true);

        controlPanel.add(new JLabel("Chọn biểu đồ: "));
        controlPanel.add(btnBar);
        controlPanel.add(btnLine);
        controlPanel.add(btnPie);

        contentPanel.add(controlPanel, BorderLayout.SOUTH);

        chartPanel = new JPanel(new BorderLayout());
        contentPanel.add(chartPanel, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);

        // Lắng nghe sự kiện thay đổi thời gian hoặc đổi nút biểu đồ
        timeComboBox.addActionListener(e -> updateChart());
        btnBar.addActionListener(e -> updateChart());
        btnLine.addActionListener(e -> updateChart());
        btnPie.addActionListener(e -> updateChart());

        // Hiển thị chart lần đầu
        updateChart();
    }

    private void updateChart() {
        String timeUnit = (String) timeComboBox.getSelectedItem();
        String chartType;
        if (btnBar.isSelected()) {
            chartType = "BAR";
        } else if (btnLine.isSelected()) {
            chartType = "LINE";
        } else {
            chartType = "PIE";
        }

        showChart(timeUnit, chartType);
    }

    private void showChart(String timeUnit, String chartType) {
        StatisticsService service = new StatisticsService();
        Map<String, Double> revenueData = service.calculateRevenueBy(timeUnit);

        JFreeChart chart;
        switch (chartType) {
            case "BAR" -> chart = ChartFactory.createBarChart("Doanh thu theo " + timeUnit.toLowerCase(), timeUnit, "VND", createCategoryDataset(revenueData));
            case "LINE" -> chart = ChartFactory.createLineChart("Doanh thu theo " + timeUnit.toLowerCase(), timeUnit, "VND", createCategoryDataset(revenueData));
            case "PIE" -> chart = ChartFactory.createPieChart("Doanh thu theo " + timeUnit.toLowerCase(), createPieDataset(revenueData), true, true, false);
            default -> {
                System.out.println("Loại biểu đồ không hợp lệ!");
                return;
            }
        }

        chartPanel.removeAll();
        chartPanel.add(new ChartPanel(chart), BorderLayout.CENTER);
        chartPanel.revalidate();
        chartPanel.repaint();
    }

    private DefaultCategoryDataset createCategoryDataset(Map<String, Double> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        data.forEach((label, value) -> dataset.addValue(value, "Doanh thu", label));
        return dataset;
    }

    private DefaultPieDataset<String> createPieDataset(Map<String, Double> data) {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        data.forEach(dataset::setValue);
        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Thống Kê Doanh Thu");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new Statistical());
            frame.setVisible(true);
        });
    }
}
