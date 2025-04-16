import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Circle extends Shape {
    private int radius;

    public Circle(double x, double y, double vx, double vy, Color color, int radius) {
        // Chú ý: x, y ở đây là tâm của hình tròn
        super(x, y, vx, vy, color);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        // Tọa độ cho fillOval là góc trên trái của hình chữ nhật bao quanh oval
        int topLeftX = (int) (x - radius);
        int topLeftY = (int) (y - radius);
        g.fillOval(topLeftX, topLeftY, 2 * radius, 2 * radius);
    }

    @Override
    public Rectangle getBounds() {
        // Trả về hình chữ nhật bao quanh hình tròn
        int topLeftX = (int) (x - radius);
        int topLeftY = (int) (y - radius);
        return new Rectangle(topLeftX, topLeftY, 2 * radius, 2 * radius);
    }

    // Override move để xử lý va chạm biên cho tâm hình tròn
     @Override
    public void move(int panelWidth, int panelHeight) {
        // Cập nhật vị trí tâm
        x += vx;
        y += vy;

        // Kiểm tra va chạm biên dựa trên tâm và bán kính
        // Va chạm biên trái hoặc phải
        if (x - radius <= 0 && vx < 0) { // Đụng biên trái
            vx = -vx;
            x = radius; // Đặt tâm cách biên trái đúng bằng bán kính
        } else if (x + radius >= panelWidth && vx > 0) { // Đụng biên phải
            vx = -vx;
            x = panelWidth - radius; // Đặt tâm cách biên phải đúng bằng bán kính
        }

        // Va chạm biên trên hoặc dưới
        if (y - radius <= 0 && vy < 0) { // Đụng biên trên
            vy = -vy;
            y = radius; // Đặt tâm cách biên trên đúng bằng bán kính
        } else if (y + radius >= panelHeight && vy > 0) { // Đụng biên dưới
            vy = -vy;
            y = panelHeight - radius; // Đặt tâm cách biên dưới đúng bằng bán kính
        }
    }
    
    // Không cần override updatePositionFromBounds vì move đã xử lý trực tiếp x,y là tâm
}