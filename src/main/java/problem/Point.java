package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.util.Random;

/**
 * Класс точки
 */
public class Point {
    /**
     * x - координата точки
     */
    double x;
    /**
     * y - координата точки
     */
    double y;

    /**
     * Конструктор точки
     *
     * @param x         координата x
     * @param y         координата y
     */
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Получить случайную точку
     *
     * @return случайная точка
     */
    static Point getRandomPoint() {
        Random r = new Random();
        double nx = (double) r.nextInt(50) / 25 - 1;
        double ny = (double) r.nextInt(50) / 25 - 1;
        return new Point(nx, ny);
    }

    /**
     * Рисование точки
     *
     * @param gl переменная OpenGl для рисования
     */
    void render(GL2 gl) {
        gl.glColor3d(1.0, 0.0, 0.0);
        gl.glPointSize(3);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2d(x, y);
        gl.glEnd();
        gl.glPointSize(1);
    }

    /**
     * Получить строковое представление точки
     *
     * @return строковое представление точки
     */
    @Override
    public String toString() {
        return "Точка с координатами: {" + x + "," + y + "}";
    }
}
