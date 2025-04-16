/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LOQ
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle; // Sử dụng Rectangle để định nghĩa vùng bao

/**
 * Lớp trừu tượng đại diện cho một hình vẽ có thể di chuyển.
 */
public abstract class Shape {
    // Thuộc tính vị trí (có thể dùng double để di chuyển mượt hơn)
    protected double x;
    protected double y;

    // Thuộc tính vận tốc (velocity)
    protected double vx;
    protected double vy;

    // Thuộc tính màu sắc
    protected Color color;

    /**
     * Hàm khởi tạo cơ sở.
     * @param x Vị trí x ban đầu
     * @param y Vị trí y ban đầu
     * @param vx Vận tốc theo trục x
     * @param vy Vận tốc theo trục y
     * @param color Màu sắc
     */
    public Shape(double x, double y, double vx, double vy, Color color) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.color = color;
    }

    /**
     * Phương thức di chuyển hình vẽ.
     * Kiểm tra va chạm với biên của panel.
     * @param panelWidth Chiều rộng của khu vực vẽ
     * @param panelHeight Chiều cao của khu vực vẽ
     */
    public void move(int panelWidth, int panelHeight) {
        // Cập nhật vị trí
        x += vx;
        y += vy;

        // Kiểm tra va chạm biên và đổi hướng nếu cần
        Rectangle bounds = getBounds(); // Lấy vùng bao của hình

        // Va chạm biên trái hoặc phải
        if (bounds.getMinX() <= 0 && vx < 0) { // Đụng biên trái khi đang đi sang trái
            vx = -vx; // Đổi hướng x
            x = 0; // Điều chỉnh lại vị trí để không bị kẹt vào biên (cho hình chữ nhật)
                       // Đối với hình tròn, cần điều chỉnh khác một chút dựa trên tâm và bán kính
        } else if (bounds.getMaxX() >= panelWidth && vx > 0) { // Đụng biên phải khi đang đi sang phải
            vx = -vx; // Đổi hướng x
            x = panelWidth - bounds.width; // Điều chỉnh vị trí
        }

        // Va chạm biên trên hoặc dưới
        if (bounds.getMinY() <= 0 && vy < 0) { // Đụng biên trên khi đang đi lên
            vy = -vy; // Đổi hướng y
            y = 0; // Điều chỉnh vị trí
        } else if (bounds.getMaxY() >= panelHeight && vy > 0) { // Đụng biên dưới khi đang đi xuống
            vy = -vy; // Đổi hướng y
            y = panelHeight - bounds.height; // Điều chỉnh vị trí
        }
        // Cập nhật lại vị trí sau khi có thể đã điều chỉnh do va chạm biên
        updatePositionFromBounds(bounds);
    }
    
    // Phương thức này có thể cần thiết nếu cách bạn định nghĩa x, y và bounds khác nhau
    // Ví dụ: nếu x,y là tâm của hình tròn thay vì góc trên trái.
    // Mặc định là không làm gì, lớp con có thể override nếu cần.
    protected void updatePositionFromBounds(Rectangle bounds) {
         // Nếu x,y là góc trên trái của bounds thì không cần làm gì
         // Nếu x,y là tâm, thì cần cập nhật x, y = bounds.getCenterX(), bounds.getCenterY() sau khi điều chỉnh bounds.x, bounds.y
         // Trong ví dụ này, ta giả định x,y là góc trên trái cho Rectangle, 
         // và sẽ điều chỉnh trong Circle nếu cần.
    }


    /**
     * Phương thức trừu tượng để vẽ hình. Lớp con phải triển khai.
     * @param g Đối tượng Graphics để vẽ.
     */
    public abstract void draw(Graphics g);

    /**
     * Phương thức trừu tượng để lấy vùng bao (bounding box) của hình.
     * Dùng cho việc kiểm tra va chạm.
     * @return Một đối tượng Rectangle đại diện cho vùng bao.
     */
    public abstract Rectangle getBounds();

    // --- Getters ---
    public double getX() { return x; }
    public double getY() { return y; }
    public Color getColor() { return color; }

    // (Có thể thêm các setters nếu cần)
}