package com.chen1144.effectivej.util.collection;

public interface IReadonlyList<TValue> extends IReadonlyEnumerable<TValue>, IReadonlyIndexed<Integer, TValue> {
    int size();
    TValue get(int index);

    @Override
    default TValue get(Integer integer) {
        return get(integer.intValue());
    }
}
