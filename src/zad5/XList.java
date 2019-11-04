package zad5;

import java.util.*;
import java.util.stream.Collectors;

public class XList<T> implements List {
    List<T> list = new ArrayList<>();

    public XList(T... values) {
        for (T value : values){
            list.add(value);
        }
    }

    public static<T> XList<T> of(T... values) {
        XList<T> xList = new XList<>();
        for (T value : values){
            xList.list.add(value);
        }
        return xList;
    }

    public static <T> XList<T> of(Set<T> values) {
        XList<T> xList = new XList<>();
        for (T value : values){
            xList.list.add(value);
        }
        return xList;
    }

    public static XList<String> charsOf(String napis) {
        XList<String> xList = new XList<>();
        for (char value : napis.toCharArray()){
            xList.list.add(String.valueOf(value));
        }
        return xList;
    }

    public static XList<String> tokensOf(String napis) {
        XList<String> xList = new XList<>();
        xList.list.add(napis);
        return xList;
    }

    public static XList<String> tokensOf(String napis, String separator) {
        XList<String> xList = new XList<>();
        xList.list.addAll(Arrays.asList(napis.split(separator)));
        return xList;
    }

    public XList<T> union(XList<T> list2) {
        list.addAll(list2.list);
        return this;
    }

    public XList<T> union(Object[] list2) {
        for(Object value : list2){
            list.add((T)value);
        }
        return this;
    }

    public XList<T> union(Set<T> list2) {
        list.addAll(list2);
        return this;
    }

    public XList<T> diff(Set<T> set) {
        list.removeAll(set);
        return this;
    }

    public XList<T> diff(XList<T> list1) {
        list.removeAll((Collection<?>) list1);
        return this;
    }

    public XList<T> unique() {
        return new XList(list.stream().distinct().collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
