package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.util.Random;

public class Rectangle {
    double x1;
    double y1;
    double x3;
    double y3;

    Rectangle(double x1, double y1, double x3, double y3){
        this.x1 = x1;
        this.y1 = y1;
        this.x3 = x3;
        this.y3 = y3;
    }

    static Rectangle getRandomRectangle(){
        Random r = new Random();
        double nx1 = (double) r.nextInt(50) / 25 - 1;
        double ny1 = (double) r.nextInt(50) / 25 - 1;
        double nx3 = nx1;
        while(nx1 == nx3) nx3 = (double) r.nextInt(50) / 25 - 1;
        double ny3 = ny1;
        while(ny1 == ny3) ny3 = (double) r.nextInt(50) / 25 - 1;
        return new Rectangle(nx1, ny1, nx3, ny3);
    }
    public void render(GL2 gl) {
        gl.glLineWidth(3);
        gl.glColor3d(0.0, 1.0, 0.0);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2d(x1, y1);
        gl.glVertex2d(x1, y3);
        gl.glVertex2d(x1, y3);
        gl.glVertex2d(x3, y3);
        gl.glVertex2d(x3, y3);
        gl.glVertex2d(x3, y1);
        gl.glVertex2d(x3, y1);
        gl.glVertex2d(x1, y1);
        gl.glEnd();
        gl.glPointSize(1);
    }
}
