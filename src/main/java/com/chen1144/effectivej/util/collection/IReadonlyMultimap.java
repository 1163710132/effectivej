package com.chen1144.effectivej.util.collection;

public interface IReadonlyMultimap<TKey, TValue> {
    IReadonlyCollection<TValue> getAll(TKey key);
    boolean containsKey(TKey key);
    int countKey();
    int countValue(TKey key);
}
