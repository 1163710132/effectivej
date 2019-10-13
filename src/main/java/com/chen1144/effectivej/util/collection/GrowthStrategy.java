package com.chen1144.effectivej.util.collection;

@FunctionalInterface
public interface GrowthStrategy {
    int growth(int oldCapacity, int require);
}
