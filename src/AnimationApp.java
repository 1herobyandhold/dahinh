import javax.swing.*;
import java.awt.*;

public class AnimationApp {

    public static void main(String[] args) {
        // Quan trọng: Chạy việc tạo và hiển thị GUI trên Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Shape Animation"); // Tiêu đề cửa sổ
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Thoát ứng dụng khi đóng cửa sổ

        // Tạo và thêm ShapeLayer vào frame
        ShapeLayer shapeLayer = new ShapeLayer();
        frame.add(shapeLayer);

        frame.pack(); // Tự động điều chỉnh kích thước frame dựa trên PreferredSize của ShapeLayer
        frame.setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        frame.setVisible(true); // Hiển thị cửa sổ

        // Yêu cầu focus cho panel sau khi frame hiển thị
        shapeLayer.requestFocusInWindow();
    }
}