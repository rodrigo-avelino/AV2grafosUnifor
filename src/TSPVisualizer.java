/* *****************************************************************************
 *  Grupo:
 *  Alunos integrantes:
 *
 *  Descrição:  Implementa um cliente interativo que constrói um ciclo (Tour)
 *              usando a heurística do vizinho mais próximo (vermelho)
 *              ou a heurística do menor aumento (azul).
 *
 *              Pode ser chamado com ou sem um arquivo de entrada para iniciar:
 *
 *                  java TSPVisualizer data/tsp1000.txt
 *
 *              Comandos do teclado:
 *                  - n   alterna exibição do ciclo usando a heurística do vizinho mais próximo
 *                  - s   alterna exibição do ciclo usando a heurística do menor aumento
 *                  - m   alterna correção do clique do mouse ('modo de desenho')
 *                  - q   sair (não!)
 *
 *  Dependências: Point, StdOut, StdDraw
 **************************************************************************** */

 import java.util.ArrayList;
 import edu.princeton.cs.algs4.StdOut;
 import edu.princeton.cs.algs4.StdDraw;
 import edu.princeton.cs.algs4.In;

 public class TSPVisualizer {
 
     public static void main(String[] args) {
 
         int xscale = 512;
         int yscale = 512 - 70;
 
         StdDraw.setXscale(0, 512);
         StdDraw.setYscale(-70, 512 - 70);
 
         StdDraw.textLeft(50, 400, "Comandos de teclado:");
         StdDraw.textLeft(80, 380, "- n   alterna ciclo da heurística do vizinho mais próximo");
         StdDraw.textLeft(80, 360, "- s   alterna ciclo da heurística do menor aumento");
         StdDraw.textLeft(80, 340, "- m   'modo de desenho'");
         StdDraw.textLeft(80, 320, "- q   sair");
 
         StdDraw.enableDoubleBuffering();
 
         Tour nearest = new Tour();
         Tour smallest = new Tour();
 
         ArrayList<Point> points = new ArrayList<Point>();
 
         boolean redraw = false;
 
         boolean showingNearest = true;
         boolean showingSmallest = true;
 
         boolean mouseWasUp = true;
         boolean mouseCorrect = true;
 
         // inicializa as duas estruturas de dados com pontos do arquivo
         if (args.length > 0) {
             String filename = args[0];
             In in = new In(filename);
 
             xscale = in.readInt();
             yscale = in.readInt();
 
             StdDraw.setXscale(0, xscale);
             StdDraw.setYscale(-70, yscale);
 
             // imprime as dimensões
             StdOut.println(xscale + " " + yscale);
 
             while (!in.isEmpty()) {
                 double x = in.readDouble();
                 double y = in.readDouble();
 
                 // imprime coordenadas dos novos pontos
                 StdOut.println(x + " " + y);
 
                 Point p = new Point(x, y);
 
                 points.add(p);
 
                 nearest.insertNearest(p);
                 smallest.insertSmallest(p);
             }
 
             redraw = true;
         }
         else {
             // imprime dimensões
             StdOut.println(xscale + " " + yscale);
         }
 
         // LOOP PRINCIPAL DE EVENTOS
         // ------------------------------------------------------------------
         while (true) {
 
             // verifica eventos de teclado
             if (StdDraw.hasNextKeyTyped()) {
 
                 char key = StdDraw.nextKeyTyped();
 
                 if (key == 'n') showingNearest = !showingNearest;
                 if (key == 's') showingSmallest = !showingSmallest;
                 if (key == 'm') mouseCorrect = !mouseCorrect;
                 if (key == 'q') break;
 
                 redraw = true;
             }
 
             // ao clicar com o mouse: adiciona novo ponto aos ciclos
             if (StdDraw.isMousePressed() && (!mouseCorrect || mouseWasUp)) {
                 mouseWasUp = false;
 
                 // local (x, y) do clique do mouse
                 double x = StdDraw.mouseX();
                 double y = StdDraw.mouseY();
 
                 Point p = new Point(x, y);
 
                 points.add(p);
 
                 // insere pontos nos ciclos
                 nearest.insertNearest(p);
                 smallest.insertSmallest(p);
 
                 // imprime coordenadas dos novos pontos
                 StdOut.println(x + " " + y);
 
                 // indica que o quadro precisa ser redesenhado
                 redraw = true;
             }
             else
                 mouseWasUp = !StdDraw.isMousePressed();
 
             // quando o quadro precisa ser atualizado
             if (redraw) {
                 redraw = false;
 
                 StdDraw.clear();
 
                 // desenha em vermelho o ciclo do vizinho mais próximo
                 if (showingNearest) {
                     StdDraw.setPenRadius(0.004);
                     StdDraw.setPenColor(StdDraw.RED);
                     nearest.draw();
                 }
 
                 // desenha em azul o ciclo do menor aumento
                 if (showingSmallest) {
                     StdDraw.setPenRadius(0.003);
                     StdDraw.setPenColor(StdDraw.BLUE);
                     smallest.draw();
                 }
 
                 // desenha todos os pontos
                 StdDraw.setPenColor(StdDraw.BLACK);
                 StdDraw.setPenRadius(0.005);
                 for (Point p : points)
                     p.draw();
 
                 // imprime legendas
                 StdDraw.textLeft(10, -10, "número de pontos: " + points.size());
                 StdDraw.setPenColor(StdDraw.RED);
                 StdDraw.textLeft(10, -35, "vizinho mais próximo: " + nearest.length());
                 StdDraw.setPenColor(StdDraw.BLUE);
                 StdDraw.textLeft(10, -60, "menor aumento: " + smallest.length());
                 StdDraw.setPenColor(StdDraw.BLACK);
 
                 StdDraw.show();
                 StdDraw.pause(50);
             }
         }
         System.exit(0);
     }
 }
 