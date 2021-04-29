package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public class Line {
    double x1;
    double y1;
    double x2;
    double y2;
    double k;
    double b;

    Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        k = (y2 - y1) / (x2 - x1);
        b = y1 - k * x1;
    }

    void render(GL2 gl) {
        gl.glLineWidth(5);
        gl.glBegin(GL.GL_LINES);
        gl.glColor3d(1, 0.5, 0);
        gl.glVertex2d(x1, y1);
        gl.glVertex2d(x2, y2);
        gl.glEnd();
    }
}
