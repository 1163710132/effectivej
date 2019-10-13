package com.chen1144.effectivej.util.collection;

public interface IIndexed<TIndex, TValue> extends IReadonlyIndexed<TIndex, TValue> {
    void set(TIndex index, TValue value);
}
