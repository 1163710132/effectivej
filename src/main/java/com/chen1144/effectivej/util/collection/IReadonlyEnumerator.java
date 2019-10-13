package com.chen1144.effectivej.util.collection;

import java.util.Iterator;

public interface IReadonlyEnumerator<T> {
    boolean moveNext();
    T value();

    default Iterator<T> toIterator(){
        return new Iterator<T>() {
            private boolean hasValue = false;
            private boolean terminated = false;

            @Override
            public boolean hasNext() {
                if(hasValue){
                    return true;
                }else if(terminated){
                    return false;
                }else{
                    hasValue = moveNext();
                    if(!hasValue){
                        terminated = true;
                    }
                    return hasValue;
                }
            }

            @Override
            public T next() {
                if(hasValue){
                    hasValue = false;
                    return value();
                }else if(moveNext()){
                    return value();
                }else{
                    terminated = true;
                    throw new RuntimeException("Enumerator has not more values.");
                }
            }
        };
    }
}
