package com.chen1144.effectivej.util.collection.persistent;

public interface IPersistentMap<TKey, TValue> {
    IPersistentMap<TKey, TValue> put(TKey key, TValue value);
    TValue get(TKey key);
    boolean containsKey(TKey key);
    int size();
}
