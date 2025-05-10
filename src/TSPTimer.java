/* *****************************************************************************
 *  VOCÊ NÃO PRECISA MODIFICAR ESTE ARQUIVO
 *
 *  Compilação:  javac TSPTimer.java
 *  Execução:    java -Xint TSPTimer n
 *  Dependências: Tour.java Point.java Stopwatch.java StdOut.java
 *
 *  Mede o tempo das duas heurísticas gerando instâncias aleatórias de tamanho n.
 *
 *  Exemplo de execução:
 *  % java -Xint TSPTimer 1000
 *
 *  Observação: os arquivos de entrada (caso utilizados) devem estar na pasta data/.
 *
 **************************************************************************** */

 import edu.princeton.cs.algs4.StdRandom;
 import edu.princeton.cs.algs4.Stopwatch;
 import edu.princeton.cs.algs4.StdOut;
 
 public class TSPTimer {
 
     public static void main(String[] args) {
         double lo = 0.0;
         double hi = 600.0;
         int n = Integer.parseInt(args[0]);
 
         // gerar dados e executar a heurística de inserção pelo vizinho mais próximo
         Stopwatch timer1 = new Stopwatch();
         Tour tour1 = new Tour();
         for (int i = 0; i < n; i++) {
             double x = StdRandom.uniformDouble(lo, hi);
             double y = StdRandom.uniformDouble(lo, hi);
             Point p = new Point(x, y);
             tour1.insertNearest(p);
         }
         //StdOut.println("Comecando otimizacao");
         //tour1.twoOpt();
         double length1 = tour1.length();
         double elapsed1 = timer1.elapsedTime();
         StdOut.println("Comprimento do ciclo = " + length1);
         StdOut.println("Inserção pelo vizinho mais próximo: " + elapsed1 + " segundos");
         StdOut.println();
 
         // gerar dados e executar a heurística de inserção pelo menor aumento
         Stopwatch timer2 = new Stopwatch();
         Tour tour2 = new Tour();
         for (int i = 0; i < n; i++) {
             double x = StdRandom.uniformDouble(lo, hi);
             double y = StdRandom.uniformDouble(lo, hi);
             Point p = new Point(x, y);
             tour2.insertSmallest(p);
         }
         double length2 = tour2.length();
         double elapsed2 = timer2.elapsedTime();
         StdOut.println("Comprimento do ciclo = " + length2);
         StdOut.println("Inserção pelo menor aumento: " + elapsed2 + " segundos");
     }
 }
 