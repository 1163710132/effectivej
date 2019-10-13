package com.chen1144.effectivej.util.collection;

public interface IReadonlyIndexed<TIndex, TValue> {
    TValue get(TIndex index);
}
