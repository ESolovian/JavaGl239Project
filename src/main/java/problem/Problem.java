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
    private ArrayList<Rectangle> rectangles;
    private ArrayList<Line> lines;

    /**
     * Конструктор класса задачи
     */
    public Problem() {
        points = new ArrayList<>();
        rectangles = new ArrayList<>();
        lines = new ArrayList<>();
    }

    /**
     * Добавить точку
     *
     * @param x координата X точки
     * @param y координата Y точки
     */
    public void addPoint(double x, double y) {
        Point point = new Point(x, y);
        points.add(point);
    }

    /**
     * Добавить "параллельный" прямоугольник
     *
     * @param x1 координата X первой вершины прямоугольника
     * @param y1 координата Y первой вершины прямоугольника
     * @param x3 координата X третьей вершины прямоугольника
     * @param y3 координата Y третьей вершины прямоугольника
     */
    public void addRectangle(double x1, double y1, double x3, double y3) {
        Rectangle rectangle = new Rectangle(x1, y1, x3, y3);
        rectangles.add(rectangle);
    }

    /**
     * Решить задачу
     */
    public void solve() {
        // перебираем пары точек
        double lmax = 0;
        double l = 0;
        int iold = 2;
        int jold = 2;
        //for (Point p : points) {
        for (int i = 0; i < points.size(); i++){
            Point p = points.get(i);
            //for (Point p2 : points) {
            for (int j = 0; j < points.size(); j++){
                Point p2 = points.get(j);
                // если точки являются разными
                if (p != p2) {
                    Rectangle rectangle = rectangles.get(0);
                    Line line =  new Line(p.x, p.y, p2.x, p2.y);
                    Line line2 = new Line(0,0,0,0);
                    // проверка на пересечение левой стороны
                    if(line.k * rectangle.x1 + line.b <= Math.max(rectangle.y1, rectangle.y3) && line.k * rectangle.x1 + line.b >= Math.min(rectangle.y1, rectangle.y3)){
                        line2.x1 = rectangle.x1;
                        line2.y1 = line.k * rectangle.x1 + line.b;
                    }
                    // проверка на пересечение правой стороны
                    if(line.k * rectangle.x3 + line.b <= Math.max(rectangle.y1, rectangle.y3) && line.k * rectangle.x3 + line.b >= Math.min(rectangle.y1, rectangle.y3)){
                        if(line2.x1 != 0 && line2.y1 != 0){
                            line2.x2 = rectangle.x3;
                            line2.y2 = line.k * rectangle.x3 + line.b;
                        }
                        else{
                            line2.x1 = rectangle.x3;
                            line2.y1 = line.k * rectangle.x3 + line.b;
                        }
                    }
                    if((line2.x1 == 0 && line2.y1 == 0) || (line2.x2 == 0 && line2.y2 == 0)){
                        // проверка на пересечение нижней стороны
                        if((rectangle.y1 - line.b) / line.k <= Math.max(rectangle.x1, rectangle.x3) && (rectangle.y1 - line.b) / line.k >= Math.min(rectangle.x1, rectangle.x3)){
                            if(line2.x1 != 0 && line2.y1 != 0){
                                line2.x2 = (rectangle.y1 - line.b) / line.k;
                                line2.y2 = rectangle.y1;
                            }
                            else{
                                line2.x1 = (rectangle.y1 - line.b) / line.k;
                                line2.y1 = rectangle.y1;
                            }
                        }
                    }
                    if((line2.x1 == 0 && line2.y1 == 0) || (line2.x2 == 0 && line2.y2 == 0)){
                        // проверка на пересечение верхней стороны
                        if((rectangle.y3 - line.b) / line.k <= Math.max(rectangle.x1, rectangle.x3) && (rectangle.y3 - line.b) / line.k >= Math.min(rectangle.x1, rectangle.x3)){
                            if(line2.x1 != 0 && line2.y1 != 0){
                                line2.x2 = (rectangle.y3 - line.b) / line.k;
                                line2.y2 = rectangle.y3;
                            }
                            else{
                                line2.x1 = (rectangle.y3 - line.b) / line.k;
                                line2.y1 = rectangle.y3;
                            }
                        }
                    }
                    if(line2.x1 != 0 && line2.y1 != 0 && line2.x2 != 0 && line2.y2 != 0){
                        l = Math.sqrt(Math.pow(Math.abs(line2.x1 - line2.x2), 2) + Math.pow(Math.abs(line2.y1 - line2.y2), 2));
                        if(l > lmax){
                            lmax = l;
                            points.get(iold).isSolution = false;
                            points.get(jold).isSolution = false;
                            iold = i;
                            jold = j;
                            line.x1 = -1;
                            line.y1 = -line.k + line.b;
                            line.x2 = 1;
                            line.y2 = line.k + line.b;
                            line.inside = false;
                            line2.inside = true;
                            lines.clear();
                            lines.add(line);
                            lines.add(line2);
                            p.isSolution = true;
                            p2.isSolution = true;
                        }
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
     */
    public void addRandomRectangle() {
        Rectangle r = Rectangle.getRandomRectangle();
        rectangles.add(r);
    }

    /**
     * Очистить задачу
     */
    public void clear() {
        points.clear();
        rectangles.clear();
        lines.clear();
    }

    /**
     * Нарисовать задачу
     *
     * @param gl переменная OpenGL для рисования
     */
    public void render(GL2 gl) {
        for(Rectangle rectangle : rectangles){
            rectangle.render(gl);
        }
        for(Line line : lines){
            line.render(gl);
        }
        for (Point point : points) {
            point.render(gl);
        }
    }
}