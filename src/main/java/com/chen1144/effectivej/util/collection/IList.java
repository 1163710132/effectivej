package com.chen1144.effectivej.util.collection;

public interface IList<TValue> extends
        IReadonlyList<TValue>,
        ICollection<TValue>,
        IEnumerable<TValue>,
        IIndexed<Integer, TValue> {
    void set(int index, TValue value);
    void removeAt(int index);

    @Override
    default void set(Integer integer, TValue value) {
        set(integer.intValue(), value);
    }
}
