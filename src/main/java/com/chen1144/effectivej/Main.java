package com.chen1144.effectivej;

import com.chen1144.effectivej.util.collection.persistent.IPersistentCollection;
import com.chen1144.effectivej.util.collection.persistent.PersistentStack;

public class Main {
    public static void main(String[] args) {
        IPersistentCollection<Integer> list = PersistentStack.empty();
        list = list.add(0);
        list = list.add(1);
        list = list.add(2);
        for(var value: list.toIterable()){
            System.out.println(value);
        }
    }
}
