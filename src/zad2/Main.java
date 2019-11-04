/**
 *
 *  @author Maneichyk Kiril S18475
 *
 */

package zad2;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/*<-- niezbędne import */
public class Main {
  public static void main(String[] args) throws IOException {
    Function<BufferedReader, List<String>> flines = (in) -> in.lines().collect(Collectors.toList());

    Function<List<String>, String> join = strings -> {
      StringBuilder res = new StringBuilder();
      strings.forEach(res::append);
      return res.toString();
    };

    Function<List<String>, List<Integer>> collectInts = strings -> {
      List<Integer> res = new ArrayList<>();
      strings.forEach(s -> {
        String[] ints = s.split("[ ,]");
        for(String str : ints){
          try{
            int a = Integer.parseInt(str);
            res.add(a);
          } catch (Exception e){}
        }
      });
      return res;
    };

    Function<List<Integer>, Integer> sum = integers -> {
      AtomicInteger sum1 = new AtomicInteger();
      integers.forEach(sum1::addAndGet);
      return sum1.get();
    };

    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

    // Zadania badawcze:
    // Operacja flines zawiera odczyt pliku, zatem może powstac wyjątek IOException
    // Wymagane jest, aby tę operację zdefiniowac jako lambda-wyrażenie
    // Ale z lambda wyrażeń nie możemy przekazywac obsługi wyjatków do otaczającego bloku
    // I wobec tego musimy pisać w definicji flines try { } catch { }
    // Jak spowodować, aby nie było to konieczne i w przypadku powstania wyjątku IOException
    // zadziałała klauzula throws metody main
  }
}
