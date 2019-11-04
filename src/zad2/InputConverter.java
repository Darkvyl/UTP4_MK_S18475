package zad2;

import java.io.*;
import java.util.List;
import java.util.function.Function;

public class InputConverter<T> {
    private FileInputStream in;
    private T list;

    public InputConverter(String fname) throws IOException {
        in = new FileInputStream(fname);
    }

    public InputConverter(T list){
        this.list = list;
    }

    public <R> R convertBy(Function<BufferedReader, R> flines){
        return flines.apply(new BufferedReader(new InputStreamReader(in)));
    }

    public <R> String convertBy(Function<BufferedReader, R> flines, Function<R, String> join) throws IOException {
        in.getChannel().position(0);
        return join.apply(flines.apply(new BufferedReader(new InputStreamReader(in))));
    }

    public <R> List<Integer> convertBy(Function<BufferedReader, R> flines,
                                       Function<R, String> join,
                                       Function<R, List<Integer>> collectInts) throws IOException {
        in.getChannel().position(0);
        return collectInts.apply(flines.apply(new BufferedReader(new InputStreamReader(in))));
    }

    public <R> Integer convertBy(Function<BufferedReader, R> flines,
                                       Function<R, String> join,
                                       Function<R, List<Integer>> collectInts,
                                       Function<List<Integer>, Integer> sum) throws IOException {
        if(flines == null){
            return sum.apply(collectInts.apply((R) list));
        }
        in.getChannel().position(0);
        return sum.apply(collectInts.apply(flines.apply(new BufferedReader(new InputStreamReader(in)))));
    }

    public Integer convertBy(Function... functions){
        Object res = 0;
        if(functions.length == 3)
            res = list;
            for (int i = 1; i < functions.length; i++)
                res = functions[i].apply(res);
        return (int)res;
    }
}
