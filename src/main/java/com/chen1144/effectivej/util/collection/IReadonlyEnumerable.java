package com.chen1144.effectivej.util.collection;

import java.util.*;
import java.util.function.*;

public interface IReadonlyEnumerable<T> {
    IReadonlyEnumerator<T> getEnumerator();

    static <T> IReadonlyEnumerable<T> fromIterable(Iterable<T> iterable){
        return new IReadonlyEnumerable<T>() {
            @Override
            public IReadonlyEnumerator<T> getEnumerator() {
                return new IReadonlyEnumerator<T>() {
                    private final Iterator<T> iterator = iterable.iterator();
                    private T value;

                    @Override
                    public boolean moveNext() {
                        if (iterator.hasNext()) {
                            value = iterator.next();
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public T value() {
                        return value;
                    }
                };
            }

            @Override
            public Iterable<T> toIterable() {
                return iterable;
            }
        };
    }

    default Iterable<T> toIterable(){
        return () -> getEnumerator().toIterator();
    }

    default <TOut> IReadonlyEnumerable<TOut> select(Function<T, TOut> mapper){
        return () -> new IReadonlyEnumerator<TOut>() {
            private IReadonlyEnumerator<T> inner = getEnumerator();

            @Override
            public boolean moveNext() {
                return inner.moveNext();
            }

            @Override
            public TOut value() {
                return mapper.apply(inner.value());
            }
        };
    }

    default IReadonlyEnumerable<T> where(Predicate<T> predicate){
        return () -> new IReadonlyEnumerator<T>() {
            private IReadonlyEnumerator<T> inner = getEnumerator();

            @Override
            public boolean moveNext() {
                while (inner.moveNext()){
                    if(predicate.test(inner.value())){
                        return true;
                    }
                }
                return false;
            }

            @Override
            public T value() {
                return inner.value();
            }
        };
    }

    default List<T> toList(Supplier<List<T>> ctor){
        return collect(ctor);
    }

    default List<T> toList(){
        return toList(ArrayList::new);
    }

    default T[] toArray(IntFunction<T[]> ctor){
        List<T> list = toList(ArrayList::new);
        T[] array = ctor.apply(list.size());
        for(int i = 0;i < array.length;i++){
            array[i] = list.get(i);
        }
        return array;
    }

    default Set<T> toSet(Supplier<Set<T>> ctor){
        return collect(ctor);
    }

    /*default Set<T> toSet(){
        return toSet(HashSet::new);
    }*/

    default <TCollection extends Collection<T>> TCollection collect(Supplier<TCollection> ctor){
        TCollection collection = ctor.get();
        forEach(collection::add);
        return collection;
    }

    default void forEach(Consumer<T> consumer){
        IReadonlyEnumerator<T> IReadonlyEnumerator = getEnumerator();
        while (IReadonlyEnumerator.moveNext()){
            consumer.accept(IReadonlyEnumerator.value());
        }
    }

    default int count(){
        int count = 0;
        IReadonlyEnumerator<T> IReadonlyEnumerator = getEnumerator();
        while (IReadonlyEnumerator.moveNext()){
            ++count;
        }
        return count;
    }
}
