package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

// class này có tác dụng tạo một bộ hiển thị tùy chỉnh cho các ô trong bảng
public class CustomTableCellRenderer extends DefaultTableCellRenderer {
	public CustomTableCellRenderer() {
		setHorizontalAlignment(SwingConstants.CENTER); // Căn lề giữa
	}

	// Phương thức ghi đè để tùy chỉnh cách hiển thị các ô
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		// Thiết lập màu nền
		if (isSelected) {
			cell.setBackground(new Color(column)); // Màu nền cho ô được chọn
		} else if (row % 2 == 0) {
			cell.setBackground(Color.LIGHT_GRAY); // Màu nền cho các dòng chẵn
		} else {
			cell.setBackground(Color.WHITE); // Màu nền cho các dòng lẻ
		}

		// Thiết lập màu chữ
		cell.setForeground(Color.BLACK); // Màu chữ cho tất cả các ô

		return cell;
	}
}
