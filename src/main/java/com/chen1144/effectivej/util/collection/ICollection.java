package com.chen1144.effectivej.util.collection;

public interface ICollection<TValue> extends IReadonlyCollection<TValue> {
    void add(TValue value);
    void remove(TValue value);
}
