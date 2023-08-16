package com.advance.calculator.tollsapp.utils;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.Function;
import com.fathzer.soft.javaluator.Parameters;

import java.util.Iterator;

public class ExtendedDoubleEvaluator extends DoubleEvaluator {
    private static final Function ACOSD;
    private static final Function ASIND;
    private static final Function ATAND;
    private static final Function CBRT;
    private static final Parameters PARAMS;
    private static final Function SQRT;

    static {
        Function function = new Function("sqrt", 1);
        SQRT = function;
        Function function2 = new Function("cbrt", 1);
        CBRT = function2;
        Function function3 = new Function("asind", 1);
        ASIND = function3;
        Function function4 = new Function("acosd", 1);
        ACOSD = function4;
        Function function5 = new Function("atand", 1);
        ATAND = function5;
        Parameters defaultParameters = DoubleEvaluator.getDefaultParameters();
        PARAMS = defaultParameters;
        defaultParameters.add(function);
        defaultParameters.add(function2);
        defaultParameters.add(function3);
        defaultParameters.add(function4);
        defaultParameters.add(function5);
    }

    public ExtendedDoubleEvaluator() {
        super(PARAMS);
    }

    @Override
    public Double evaluate(Function function, Iterator<Double> arguments, Object evaluationContext) {
        if (function == SQRT) {
            return Double.valueOf(Math.sqrt(arguments.next().doubleValue()));
        }
        if (function == CBRT) {
            return Double.valueOf(Math.cbrt(arguments.next().doubleValue()));
        }
        if (function == ASIND) {
            return Double.valueOf(Math.toDegrees(Math.asin(arguments.next().doubleValue())));
        }
        if (function == ACOSD) {
            return Double.valueOf(Math.toDegrees(Math.acos(arguments.next().doubleValue())));
        }
        if (function == ATAND) {
            return Double.valueOf(Math.toDegrees(Math.atan(arguments.next().doubleValue())));
        }
        return super.evaluate(function, arguments, evaluationContext);
    }
}
