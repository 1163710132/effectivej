package com.chen1144.effectivej.util.collection;

public interface IMap<TKey, TValue> extends IReadonlyMap<TKey, TValue>, IIndexed<TKey, TValue> {
    boolean removeKey(TKey key);
}
