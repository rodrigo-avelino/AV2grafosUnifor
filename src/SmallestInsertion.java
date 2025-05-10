/* *****************************************************************************
 *  VOCÊ NÃO PRECISA MODIFICAR ESTE ARQUIVO
 *
 *  Compilação:  javac SmallestInsertion.java
 *  Execução:    java SmallestInsertion < data/file.txt
 *  Dependências: Tour.java Point.java StdIn.java StdDraw.java
 *
 *  Executa a heurística de inserção pelo menor aumento para o problema
 *  do caixeiro viajante e plota os resultados.
 *
 *  Exemplo de execução:
 *  % java SmallestInsertion < data/tsp1000.txt
 *
 **************************************************************************** */

 import edu.princeton.cs.algs4.StdIn;
 import edu.princeton.cs.algs4.StdOut;
 import edu.princeton.cs.algs4.StdDraw;
 
 public class SmallestInsertion {
 
     public static void main(String[] args) {
 
         // obter dimensões
         int width = StdIn.readInt();
         int height = StdIn.readInt();
         int border = 20;
         StdDraw.setCanvasSize(width, height + border);
         StdDraw.setXscale(0, width);
         StdDraw.setYscale(-border, height);
 
         // ativar modo de animação
         StdDraw.enableDoubleBuffering();
 
         // executar a heurística de inserção pelo menor aumento
         Tour tour = new Tour();
         while (!StdIn.isEmpty()) {
             double x = StdIn.readDouble();
             double y = StdIn.readDouble();
             Point p = new Point(x, y);
             tour.insertSmallest(p);
 
             // descomente as 4 linhas abaixo para animar
             // StdDraw.clear();
             // tour.draw();
             // StdDraw.textLeft(20, 0, "comprimento = " + tour.length());
             // StdDraw.show();
             // StdDraw.pause(50);
         }
 
         // desenhar no quadro padrão
         tour.twoOpt();
         tour.draw();
         StdDraw.show();
 
         // imprimir o ciclo no terminal padrão
         StdOut.println(tour);
         StdOut.printf("Comprimento do ciclo = %.4f\n", tour.length());
         StdOut.printf("Número de pontos = %d\n", tour.size());
     }
 }
 