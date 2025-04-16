import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Rectangle extends Shape { // Đổi tên lớp nếu bạn đã có lớp Rectangle khác
    private int width;
    private int height;

    public Rectangle(double x, double y, double vx, double vy, Color color, int width, int height) {
        // Chú ý: x, y ở đây là góc trên trái của hình chữ nhật
        super(x, y, vx, vy, color);
        this.width = width;
        this.height = height;
    }

    public int getRectWidth() { // Tránh trùng tên với getWidth() của Component
        return width;
    }

    public int getRectHeight() { // Tránh trùng tên với getHeight() của Component
        return height;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        // Trả về chính hình chữ nhật này (vì x,y là góc trên trái)
        return new Rectangle((int) x, (int) y, width, height);
    }
    
    // Không cần override move() vì logic trong lớp Shape đã xử lý đúng cho trường hợp x,y là góc trên trái
    // và getBounds trả về đúng hình chữ nhật đó.
     // Không cần override updatePositionFromBounds
}