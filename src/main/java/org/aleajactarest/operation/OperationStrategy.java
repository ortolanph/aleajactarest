package org.aleajactarest.operation;

@FunctionalInterface
public interface OperationStrategy<T> {
    T compute(T x, T y);
}
