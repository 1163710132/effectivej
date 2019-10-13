package com.chen1144.effectivej.util.collection;

public interface IMultimap<TKey, TValue> extends IReadonlyMultimap<TKey, TValue> {
    void put(TKey key, TValue value);
}
