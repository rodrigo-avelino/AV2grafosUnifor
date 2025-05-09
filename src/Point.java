import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdDraw;

public class Point {
    private final double x;   // coordenada cartesiana x
    private final double y;   // coordenada cartesiana y

    // cria e inicializa um ponto com (x, y) dados
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // retorna a distância euclidiana entre dois pontos
    public double distanceTo(Point that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // desenha este ponto na tela padrão
    public void draw() {
        StdDraw.point(x, y);
    }

    // desenha o segmento de linha entre este ponto e outro na tela padrão
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // retorna uma representação em string deste ponto
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // Method to access x or y coordinate by index (0 = x, 1 = y)
    public double get(int axis) {
        if (axis == 0) {
            return x;  // x-coordinate
        } else if (axis == 1) {
            return y;  // y-coordinate
        } else {
            throw new IllegalArgumentException("Invalid axis: " + axis);
        }
    }

    // lê um arquivo TSP da entrada padrão
    // e plota os pontos na tela padrão
    public static void main(String[] args) {

        // obtém as dimensões
        int width = StdIn.readInt();
        int height = StdIn.readInt();
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.setPenRadius(0.005);

        // lê e plota os pontos um por vez
        while (!StdIn.isEmpty()) {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            Point p = new Point(x, y);
            p.draw();
        }
    }
}