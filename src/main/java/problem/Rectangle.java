package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public class Rectangle {
    double x1;
    double y1;
    double x3;
    double y3;
    double x2;
    double y2;
    double x4;
    double y4;

    Rectangle(double x1, double y1, double x3, double y3){
        this.x1 = x1;
        this.y1 = y1;
        this.x3 = x3;
        this.y3 = y3;
        this.x2 = x1;
        this.y2 = y3;
        this.x4 = x3;
        this.y4 = y1;
    }

    void render(GL2 gl) {
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2d(x1, y1);
        gl.glVertex2d(x2, y2);
        gl.glVertex2d(x3, y3);
        gl.glVertex2d(x3, y3);
        gl.glVertex2d(x4, y4);
        gl.glVertex2d(x1, y1);
        gl.glEnd();
    }
}
