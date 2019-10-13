package com.chen1144.effectivej.util.collection;

public interface IEnumerable<T> extends IReadonlyEnumerable<T> {
    @Override
    IEnumerator<T> getEnumerator();
}
