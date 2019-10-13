package com.chen1144.effectivej.util.collection;

public interface IReadonlyCollection<TValue> extends IReadonlyEnumerable<TValue> {
    int size();
    boolean contains(TValue value);

    default boolean isEmpty(){
        return size() == 0;
    }

    default boolean notEmpty(){
        return size() != 0;
    }
}
