package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс задачи
 */
public class Problem {
    /**
     * текст задачи
     */
    public static final String PROBLEM_TEXT = "ПОСТАНОВКА ЗАДАЧИ:\n" +
            "На плоскости задано множество точек, и параллельный прямоугольник.\n" +
            "Найти такую прямую, которая пересекает указанный прямоугольник,\n" +
            "и при этом длина отрезка прямой внутри прямоугольника максимальна.";

    /**
     * заголовок окна
     */
    public static final String PROBLEM_CAPTION = "Итоговый проект ученика 10-2 Соловьяненко Егора";

    /**
     * путь к файлу
     */
    private static final String FILE_NAME = "points.txt";

    /**
     * список точек
     */
    private ArrayList<Point> points;
    private Rectangle rectangle;
    private Line line;

    /**
     * Конструктор класса задачи
     */
    public Problem() {
        points = new ArrayList<>();
    }

    /**
     * Добавить точку
     *
     * @param x      координата X точки
     * @param y      координата Y точки
     */
    public void addPoint(double x, double y) {
        Point point = new Point(x, y);
        points.add(point);
    }

    /**
     * Добавить "параллельный" прямоугольник
     *
     * @param x1    координата X первой вершины прямоугольника
     * @param y1    координата Y первой вершины прямоугольника
     * @param x3    координата X третьей вершины прямоугольника
     * @param y3    координата Y третьей вершины прямоугольника
     */
    public void addRectangle(double x1, double y1, double x3, double y3){
        Rectangle rectangle = new Rectangle(x1, y1, x3, y3);
    }
    /**
     * Решить задачу
     */
    public void solve() {
        // перебираем пары точек
        for (Point p : points) {
            for (Point p2 : points) {
                // если точки являются разными
                if (p != p2) {
                    // если координаты у них совпадают
                    if (Math.abs(p.x - p2.x) < 0.0001 && Math.abs(p.y - p2.y) < 0.0001) {
                        //p.isSolution = true;
                        //p2.isSolution = true;
                    }
                }
            }
        }
    }

    /**
     * Загрузить задачу из файла
     */
    public void loadFromFile() {
        points.clear();
        try {
            File file = new File(FILE_NAME);
            Scanner sc = new Scanner(file);
            // пока в файле есть непрочитанные строки
            while (sc.hasNextLine()) {
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                sc.nextLine();
                Point point = new Point(x, y);
                points.add(point);
            }
        } catch (Exception ex) {
            System.out.println("Ошибка чтения из файла: " + ex);
        }
    }

    /**
     * Сохранить задачу в файл
     */
    public void saveToFile() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME));
            for (Point point : points) {
                out.printf("%.2f %.2f %d\n", point.x, point.y);
            }
            out.close();
        } catch (IOException ex) {
            System.out.println("Ошибка записи в файл: " + ex);
        }
    }

    /**
     * Добавить заданное число случайных точек
     *
     * @param n кол-во точек
     */
    public void addRandomPoints(int n) {
        for (int i = 0; i < n; i++) {
            Point p = Point.getRandomPoint();
            points.add(p);
        }
    }
    /**
     * Добавить случайный прямоугольник
     *
     */
    public void addRandomRectangle(){
        Rectangle rectangle = Rectangle.getRandomRectangle();
    }

    /**
     * Очистить задачу
     */
    public void clear() {
        points.clear();
        Rectangle rectangle = null;
    }

    /**
     * Нарисовать задачу
     *
     * @param gl переменная OpenGL для рисования
     */
    public void render(GL2 gl) {
        for (Point point : points) {
            point.render(gl);
        }
        //rectangle.render(gl);
        /*gl.glPointSize(5);
        gl.glBegin(GL.GL_TRIANGLE_FAN);

        gl.glColor3d(1,0,0);
        gl.glVertex2d(0.1,0.3);

        gl.glColor3d(1,1,0);
        gl.glVertex2d(-0.1,0.3);

        gl.glColor3d(0,0,1);
        gl.glVertex2d(-0.3,-0.3);

        gl.glColor3d(1,1,1);
        gl.glVertex2d(0.2,0.7);

        gl.glColor3d(1,0,0);
        gl.glVertex2d(0.1,0.3);


        gl.glColor3d(1,0,0);
        gl.glVertex2d(-0.1,0.3);


        gl.glEnd();*/
    }
}