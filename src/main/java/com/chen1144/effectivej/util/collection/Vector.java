package com.chen1144.effectivej.util.collection;

import java.util.Arrays;
import java.util.function.Predicate;

public class Vector<T> implements IList<T> {
    private Object[] values;
    private GrowthStrategy growthStrategy;
    private int size;

    public Vector(int initialCapacity) {
        values = new Object[initialCapacity];
        growthStrategy = (oldCapacity, require)->{
            while (oldCapacity < require){
                oldCapacity = oldCapacity * 2;
            }
            return oldCapacity;
        };
    }

    public void add(T value){
        requireCapacity(size+1);
        values[size++] = value;
    }

    public void removeAt(int index){
        for(int i = index+1;i < size;i++){
            values[i-1] = values[i];
        }
        requireCapacity(--size);
    }

    public int indexOf(T value){
        return indexMatch(value::equals);
    }

    @SuppressWarnings("rawtypes")
    public int indexMatch(Predicate<T> predicate){
        for(int i = 0;i < size;i++){
            if(predicate.test((T)values[i])){
                return i;
            }
        }
        return -1;
    }

    public void remove(T value){
        int index = indexOf(value);
        if(index > 0){
            removeAt(index);
        }
    }

    @Override
    public boolean contains(T t) {
        return indexOf(t) != -1;
    }

    public int size(){
        return size;
    }

    @Override
    public T get(int index) {
        return (T)values[index];
    }

    @Override
    public void set(int index, T t) {
        values[index] = t;
    }

    private void requireCapacity(int require){
        int newCapacity = growthStrategy.growth(values.length, require);
        if(newCapacity != values.length){
            values = Arrays.copyOf(values, newCapacity);
        }
    }

    @Override
    public IEnumerator<T> getEnumerator() {
        return new IEnumerator<T>() {
            private int index;

            @Override
            public boolean moveNext() {
                return ++index < size;
            }

            @Override
            public T value() {
                return get(index);
            }

            @Override
            public void setValue(T value) {
                set(index, value);
            }
        };
    }
}
