package zad1;

import java.io.*;
import java.util.List;
import java.util.function.Function;

public class InputConverter<T> {
    private FileInputStream in;
    private T list;

    public InputConverter(String fname) {
        try {
            in = new FileInputStream(fname);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public InputConverter(T list){
        this.list = list;
    }

    public <R> R convertBy(Function<BufferedReader, R> flines){
        return flines.apply(new BufferedReader(new InputStreamReader(in)));
    }

    public <R> String convertBy(Function<BufferedReader, R> flines, Function<R, String> join){
        try {
            in.getChannel().position(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return join.apply(flines.apply(new BufferedReader(new InputStreamReader(in))));
    }

    public <R> List<Integer> convertBy(Function<BufferedReader, R> flines,
                                       Function<R, String> join,
                                       Function<R, List<Integer>> collectInts){
        try {
            in.getChannel().position(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return collectInts.apply(flines.apply(new BufferedReader(new InputStreamReader(in))));
    }

    public <R> Integer convertBy(Function<BufferedReader, R> flines,
                                       Function<R, String> join,
                                       Function<R, List<Integer>> collectInts,
                                       Function<List<Integer>, Integer> sum){
        if(flines == null){
            return sum.apply(collectInts.apply((R) list));
        }
        try {
            in.getChannel().position(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
