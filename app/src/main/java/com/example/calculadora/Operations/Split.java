package com.example.calculadora.Operations;

import com.example.calculadora.Interfaces.Operator;

public class Split implements Operator {
    @Override
    public double Calculate(double firstValue, double lastValue) {
        return firstValue / lastValue;
    }
}
