package zad4;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {
    private T value = null;

    private Maybe(T value) {
        this.value = value;
    }

    public static <T> Maybe<T> of(T s) {
        return new Maybe<>(s);
    }

    public void ifPresent(Consumer cons){
        if(value!=null)
            cons.accept(value);
    }

    public <S> Maybe<S> map(Function<T, S> func){
        if(value==null)
            return new Maybe<S>(null);
        return new Maybe<S>(func.apply(value));
    }


    public T get() throws NoSuchElementException {
        if(value != null)
            return value;
        throw new NoSuchElementException();
    }

    public T orElse(T defVal) {
        if(value!=null)
            return value;
        return defVal;
    }

    public Maybe<T> filter(Predicate<T> pred){
        if(value == null || pred.test(value)){
            return this;
        }
        else {
            return new Maybe(null);
        }
    }

    public boolean isPresent(){
        return value!=null;
    }

    @Override
    public String toString() {
        if (value == null)
            return "Maybe is empty";
        return "Maybe has value " + value;
    }
}
