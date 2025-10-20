package com.ecologicstudios.utils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * The PerformanceEvaluator class evaluates the performance of numerical values
 * by calculating their percentile based on a baseline using statistical methods.
 * It uses a StandardDeviationCalculator to compute the mean and standard deviation
 * of the baseline values.
 * 
 * @author Ecologic Studios
 * @version 1.0
 */
public class PerformanceEvaluator implements Evaluator<Number> {
    private final List<Number> values = new LinkedList<>();
    private final StandardDeviationCalculator calculator = new StandardDeviationCalculator();

    /**
     * Default constructor for PerformanceEvaluator.
     */
    public PerformanceEvaluator() {}

    /**
     * Inserts a single numerical value into the evaluator.
     *
     * @param value The numerical value to be added.
     */
    @Override
    public void insertValue(Number value) {
        values.add(value);
    }

    /**
     * Inserts a collection of numerical values into the evaluator.
     *
     * @param list The collection of numerical values to be added.
     */
    @Override
    public void insertValues(Collection<? extends Number> list) {
        values.addAll(list);
    }

    /**
     * Clears all stored values and resets the internal calculator.
     */
    @Override
    public void clear() {
        values.clear();
        calculator.clear();
    }

    /**
     * Evaluates the latest value against the baseline (all-but-last) and returns
     * the percentile (0..100) using the standard normal CDF Φ(z).
     *
     * <p>Semantics match the original StandardDeviationCalculator class:</p>
     * <ul>
     *   <li>Baseline = values[0..n-2], Latest = values[n-1]</li>
     *   <li>Sample SD on baseline (divide by n-1 of the baseline)</li>
     *   <li>Zero-SD rules:
     *     <ul>
     *       <li>latest == mean -> 50.0</li>
     *       <li>latest  > mean -> 100.0</li>
     *       <li>else           -> 0.0</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return An Optional containing the percentile value, or Optional.empty() if
     *         fewer than 3 total values are present.
     */
    @Override
    public Optional<Number> evaluate() {
        int n = values.size();
        if (n < 3) return Optional.empty();

        List<Number> baseline = values.subList(0, n - 1);
        double latest = values.get(n - 1).doubleValue();

        calculator.clear();
        calculator.insertValues(baseline);

        double mean = calculator.getMean();
        double sd   = calculator.getStandardDeviation();

        if (sd == 0.0) {
            if (Double.compare(latest, mean) == 0) return Optional.of(50.0);
            return Optional.of(latest > mean ? 100.0 : 0.0);
        }

        double z = (latest - mean) / sd;
        double percentile = 100.0 * (0.5 * (1.0 + erf(z / Math.sqrt(2.0))));
        return Optional.of(percentile);
    }

    /**
     * Computes the error function (erf) for a given value.
     *
     * @param x The input value for which to compute the error function.
     * @return The computed error function value.
     */
    private static double erf(double x) {
        int sign = x < 0 ? -1 : 1;
        x = Math.abs(x);

        double a1 = 0.254829592;
        double a2 = -0.284496736;
        double a3 = 1.421413741;
        double a4 = -1.453152027;
        double a5 = 1.061405429;
        double p = 0.3275911;

        double t = 1.0 / (1.0 + p * x);
        double y = 1.0 - (((((a5 * t + a4) * t + a3) * t + a2) * t + a1) * t) * Math.exp(-x * x);

        return sign * y;
    }
}
