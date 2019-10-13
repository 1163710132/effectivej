package com.chen1144.effectivej.util.collection.persistent;

import com.chen1144.effectivej.util.collection.IReadonlyCollection;
import com.chen1144.effectivej.util.collection.IReadonlyEnumerable;

public interface IPersistentCollection<T> extends IReadonlyCollection<T> {
    IPersistentCollection<T> add(T value);
    IPersistentCollection<T> remove(T value);
}
