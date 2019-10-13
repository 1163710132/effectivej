package com.chen1144.effectivej.util.collection;

public interface IEnumerator<T> extends IReadonlyEnumerator<T> {
    @Override
    boolean moveNext();
    @Override
    T value();

    void setValue(T value);
}
