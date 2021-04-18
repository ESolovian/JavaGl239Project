package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public class Figures {
    public static void renderPoint(GL2 gl, Vector2 pos, float size){
        gl.glPointSize(size);
        gl.glBegin(GL.GL_POINTS);
        gl.glColor3d(0, 0.5, 1);
        gl.glVertex2d(pos.x, pos.y);
        gl.glEnd();
    }

    public static void renderLine(GL2 gl, Vector2 posA, Vector2 posB, float width){
        gl.glLineWidth(width);
        gl.glBegin(GL.GL_LINES);
        gl.glColor3d(0, 0.7, 1);
        gl.glVertex2d(posA.x, posA.y);
        gl.glVertex2d(posB.x, posB.y);
        gl.glEnd();
    }

    public static void renderTriangle(GL2 gl, Vector2 posA, Vector2 posB, Vector2 posC, boolean filled){
        gl.glBegin(GL.GL_TRIANGLES);
        if (filled) gl.glColor3d(0, 0.7, 1);
        gl.glVertex2d(posA.x, posA.y);
        gl.glVertex2d(posB.x, posB.y);
        gl.glVertex2d(posC.x, posC.y);
        gl.glEnd();
    }

    public static void renderQuad(GL2 gl, Vector2 posA, Vector2 posB, Vector2 posC, Vector2 posD, boolean filled){
        gl.glBegin(GL.GL_TRIANGLES);
        if (filled) gl.glColor3d(0, 0.7, 1);
        gl.glVertex2d(posA.x, posA.y);
        gl.glVertex2d(posB.x, posB.y);
        gl.glVertex2d(posC.x, posC.y);
        gl.glVertex2d(posC.x, posC.y);
        gl.glVertex2d(posD.x, posD.y);
        gl.glVertex2d(posA.x, posA.y);
        gl.glEnd();
    }

    public static void renderCircle(GL2 gl, Vector2 center, double rad, boolean filled){
        if (filled) gl.glColor3d(0, 0.7, 1);
        gl.glBegin(GL.GL_LINE_LOOP);
        float a;
        for(int i = 0; i < 50; i++) {
            a = (float)i / 50.0f * 3.14f * 2.0f;
            gl.glVertex2f((float)center.x + (float)Math.cos(a) * (float)rad, (float)center.y + (float)Math.sin(a) * (float)rad);
        }
        gl.glEnd();
    }
}
