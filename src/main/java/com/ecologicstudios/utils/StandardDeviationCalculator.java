package com.ecologicstudios.utils;

import java.util.Optional;
import java.util.Collection;
import java.util.List;
import java.util.LinkedList;

/**
 * The StandardDeviationCalculator class provides methods to calculate the mean,
 * variance, and standard deviation of a collection of numerical values.
 * It implements the Calculator interface for numerical data.
 * 
 * <p>This class is designed to handle numerical data efficiently and provides
 * methods to insert values, compute statistical measures, and clear stored data.</p>
 * 
 * @author Ecologic Studios
 * @version 1.0
 */
public class StandardDeviationCalculator implements Calculator<Number> {
    private final List<Number> values;

    /**
     * Constructs a new StandardDeviationCalculator with an empty list of values.
     */
    public StandardDeviationCalculator() {
        this.values = new LinkedList<>();
    }

    /**
     * Inserts a single numerical value into the calculator.
     *
     * @param value The numerical value to be added.
     */
    @Override
    public void insertValue(Number value) {
        this.values.add(value);
    }

    /**
     * Inserts a collection of numerical values into the calculator.
     *
     * @param list The collection of numerical values to be added.
     */
    @Override
    public void insertValues(Collection<? extends Number> list) {
        this.values.addAll(list);
    }

    /**
     * Calculates the standard deviation of the stored values.
     *
     * @return An Optional containing the standard deviation, or Optional.empty()
     *         if fewer than 2 values are present.
     */
    @Override
    public Optional<Number> calculate() {
        if (values.size() < 2) return Optional.empty();
        return Optional.of(getStandardDeviation());
    }

    /**
     * Clears all stored values in the calculator.
     */
    @Override
    public void clear() {
        this.values.clear();
    }

    /**
     * Computes the mean (average) of the stored values.
     *
     * @return The mean of the stored values, or 0 if no values are present.
     */
    public double getMean() {
        if (this.values.isEmpty()) return 0;
        return this.values.stream()
                .mapToDouble(Number::doubleValue)
                .average()
                .orElse(0);
    }

    /**
     * Computes the variance of the stored values.
     *
     * @return The variance of the stored values, or 0 if fewer than 2 values are present.
     */
    public double getVariance() {
        int n = this.values.size();
        if (n < 2) return 0.0;

        double mean = getMean();
        double sumSq = 0.0;
        for (Number v : values) {
            double d = v.doubleValue() - mean;
            sumSq += d * d;
        }
        return sumSq / (n - 1);
    }

    /**
     * Computes the standard deviation of the stored values.
     *
     * @return The standard deviation of the stored values.
     */
    public double getStandardDeviation() {
        return Math.sqrt(getVariance());
    }
}
