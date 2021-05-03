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
    boolean inside;

    Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        k = (y2 - y1) / (x2 - x1);
        b = y1 - k * x1;
    }

    void render(GL2 gl) {
        if(inside){
            gl.glColor3d(1, 0.0, 0.0);
        }
        else{
            gl.glColor3d(1, 1, 1);
        }
            gl.glLineWidth(3);
            gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(x1, y1);
            gl.glVertex2d(x2, y2);
            gl.glEnd();
    }
}
