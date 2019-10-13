package com.chen1144.effectivej.util.collection;

public interface IReadonlyMap<TKey, TValue> extends IReadonlyIndexed<TKey, TValue> {
    boolean containsKey(TKey key);
    int size();
}
