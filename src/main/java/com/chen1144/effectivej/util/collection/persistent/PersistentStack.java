package com.chen1144.effectivej.util.collection.persistent;

import com.chen1144.effectivej.util.collection.IReadonlyEnumerator;

import java.util.Objects;

public class PersistentStack<T> implements IPersistentCollection<T> {
    private PersistentStack<T> parent;
    private T value;
    private final int size;

    private PersistentStack(PersistentStack<T> parent, T value) {
        this.parent = parent;
        this.value = value;
        if(parent == null){
            size = 0;
        }else{
            size = parent.size + 1;
        }
    }

    public static <T> PersistentStack<T> empty(){
        return new PersistentStack<>(null, null);
    }

    @Override
    public PersistentStack<T> add(T value) {
        return new PersistentStack<>(this, value);
    }

    @Override
    public PersistentStack<T> remove(T value) {
        if(size == 0){
            return this;
        }
        if(Objects.equals(this.value, value)){
            return parent.remove(value);
        }else{
            return new PersistentStack<>(parent.remove(value), this.value);
        }
    }

    @Override
    public boolean contains(T value) {
        if(size == 0){
            return false;
        }
        if(Objects.equals(this.value, value)){
            return true;
        }else{
            return parent.contains(value);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public IReadonlyEnumerator<T> getEnumerator() {
        return new IReadonlyEnumerator<T>() {
            private PersistentStack<T> current = new PersistentStack<>(PersistentStack.this, null);

            @Override
            public boolean moveNext() {
                if(current.parent.isEmpty()){
                    return false;
                }else{
                    current = current.parent;
                    return true;
                }
            }

            @Override
            public T value() {
                return current.value;
            }
        };
    }
}
