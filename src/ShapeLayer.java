import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Kế thừa JPanel, implements ActionListener (cho Timer), KeyListener (cho bàn phím)
public class ShapeLayer extends JPanel implements ActionListener, KeyListener {

    private List<Shape> shapes; // Danh sách các hình vẽ
    private Timer animationTimer; // Timer để tạo vòng lặp animation
    private final int DELAY = 16; // Khoảng thời gian giữa các khung hình (ms), ~60 FPS
    private Random random; // Để tạo giá trị ngẫu nhiên

    public ShapeLayer() {
        shapes = new ArrayList<>();
        random = new Random();

        // Thiết lập Panel
        setBackground(Color.BLACK); // Màu nền
        setPreferredSize(new Dimension(800, 600)); // Kích thước ưa thích

        // Khởi tạo và bắt đầu Timer
        animationTimer = new Timer(DELAY, this);
        animationTimer.start();

        // Thêm KeyListener để nhận sự kiện bàn phím
        addKeyListener(this);
        setFocusable(true); // !!! Quan trọng: Để Panel có thể nhận focus và sự kiện bàn phím
        requestFocusInWindow(); // Yêu cầu focus ngay khi cửa sổ hiển thị
    }

    // Phương thức được gọi bởi Timer sau mỗi khoảng DELAY
    @Override
    public void actionPerformed(ActionEvent e) {
        // Di chuyển tất cả các hình
        for (Shape shape : shapes) {
            // Cần lấy kích thước hiện tại của panel vì cửa sổ có thể bị resize
            shape.move(getWidth(), getHeight());
        }

        // Yêu cầu vẽ lại Panel -> sẽ gọi paintComponent
        repaint();
    }

    // Override phương thức paintComponent để vẽ các hình
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Vẽ nền và các thành phần cơ sở của JPanel

        // Chuyển sang Graphics2D để có thể dùng khử răng cưa (optional)
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ từng hình trong danh sách
        // Dùng bản sao để tránh lỗi ConcurrentModificationException nếu có thao tác xóa/thêm trong lúc duyệt
        // Hoặc sử dụng iterator có hỗ trợ remove. Cách đơn giản là duyệt trên bản sao:
        List<Shape> shapesToDraw = new ArrayList<>(shapes); 
        for (Shape shape : shapesToDraw) {
            shape.draw(g2d); // Gọi phương thức draw của từng hình
        }
    }

    // --- Xử lý sự kiện bàn phím ---

    @Override
    public void keyTyped(KeyEvent e) {
        // Có thể dùng keyTyped cho các ký tự thông thường
        char keyChar = e.getKeyChar();

        if (keyChar == 'c' || keyChar == 'C') {
            addRandomCircle();
        } else if (keyChar == 'r' || keyChar == 'R') {
            addRandomRectangle();
        }
         // Thêm các phím khác nếu muốn (ví dụ: xóa hình,...)
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Có thể dùng keyPressed cho các phím chức năng hoặc khi cần phản ứng ngay khi nhấn
        // Ví dụ: int keyCode = e.getKeyCode(); if (keyCode == KeyEvent.VK_DELETE) { ... }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Thường không cần xử lý khi nhả phím trong trường hợp này
    }

    // --- Phương thức tiện ích để thêm hình ngẫu nhiên ---

    private void addRandomCircle() {
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        if (panelWidth <= 0 || panelHeight <= 0) return; // Chưa có kích thước thì chưa thêm

        int radius = random.nextInt(30) + 10; // Bán kính từ 10 đến 39
        // Đảm bảo vị trí ban đầu nằm hoàn toàn trong panel
        double x = random.nextDouble() * (panelWidth - 2 * radius) + radius;
        double y = random.nextDouble() * (panelHeight - 2 * radius) + radius;
        // Vận tốc ngẫu nhiên (khác 0)
        double vx = (random.nextDouble() - 0.5) * 6; // Vận tốc x từ -3 đến +3
         if(Math.abs(vx) < 0.5) vx = (vx >=0 ? 0.5 : -0.5); // Đảm bảo không quá chậm/đứng yên
        double vy = (random.nextDouble() - 0.5) * 6; // Vận tốc y từ -3 đến +3
         if(Math.abs(vy) < 0.5) vy = (vy >=0 ? 0.5 : -0.5);

        Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

        shapes.add(new Circle(x, y, vx, vy, color, radius));
        System.out.println("Added Circle. Total shapes: " + shapes.size()); // Log
        requestFocusInWindow(); // Yêu cầu lại focus phòng trường hợp bị mất
    }

    private void addRandomRectangle() {
       int panelWidth = getWidth();
        int panelHeight = getHeight();
        if (panelWidth <= 0 || panelHeight <= 0) return;

        int width = random.nextInt(60) + 15; // Chiều rộng 15-74
        int height = random.nextInt(60) + 15; // Chiều cao 15-74
        // Đảm bảo vị trí ban đầu (góc trên trái) nằm trong panel
         double x = random.nextDouble() * (panelWidth - width);
        double y = random.nextDouble() * (panelHeight - height);
         // Vận tốc ngẫu nhiên (khác 0)
        double vx = (random.nextDouble() - 0.5) * 6; // Vận tốc x từ -3 đến +3
         if(Math.abs(vx) < 0.5) vx = (vx >=0 ? 0.5 : -0.5);
        double vy = (random.nextDouble() - 0.5) * 6; // Vận tốc y từ -3 đến +3
         if(Math.abs(vy) < 0.5) vy = (vy >=0 ? 0.5 : -0.5);

        Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

        shapes.add(new Rectangle(x, y, vx, vy, color, width, height));
         System.out.println("Added Rectangle. Total shapes: " + shapes.size()); // Log
        requestFocusInWindow();
    }

    // TODO: Cài đặt chức năng xóa hình trùng nhau (nếu cần)
    // TODO: Cài đặt va chạm giữa các hình (nếu cần)
}