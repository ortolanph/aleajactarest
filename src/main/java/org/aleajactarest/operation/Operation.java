package org.aleajactarest.operation;

import lombok.Getter;

import java.util.Arrays;

public enum Operation implements OperationStrategy<Integer> {
    ADDITION("+", Integer::sum),
    SUBTRACTION("-", (x, y) -> x - y),
    MULTIPLICATION("*", (x, y) -> x * y),
    DIVISION("÷", (x, y) -> x / y);

    private final OperationStrategy<Integer> operationStrategy;
    @Getter
    private final String symbol;

    Operation(String symbol, OperationStrategy<Integer> operationStrategy) {
        this.symbol = symbol;
        this.operationStrategy = operationStrategy;
    }

    public static Operation getOperationBySymbol(String symbol) {
        return Arrays
            .stream(values())
            .filter(op -> op.getSymbol().equals(symbol))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException(symbol)
            );
    }

    @Override
    public Integer compute(Integer x, Integer y) {
        return operationStrategy.compute(x, y);
    }

}
