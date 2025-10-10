package com.ecologicstudios.utils;

import java.util.List;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

/**
 * Utility for computing basic sample statistics (mean, sample variance,
 * standard
 * deviation), a Z‑score and a percentile for the most recent Y value in a
 * JavaFX {@link XYChart.Data} series.
 *
 * <p>
 * Behavior:
 * - The last point in {@code graph} is treated as the "latest" value to be
 * compared against a baseline of previous rounds.
 * - All statistics (mean, variance, SD) are computed on the baseline set:
 * {@code graph.subList(0, graph.size() - 1)}.
 * - Variance uses the sample formula (divide by N-1).
 * </p>
 */
public class StandardDeviationCalculator {
    /**
     * Full chart data including the latest point.
     */
    private List<XYChart.Data<Number, Number>> graph;
    /**
     * Baseline points used for statistics (all points except the last).
     */
    private List<XYChart.Data<Number, Number>> withoutLast;

    /**
     * Create a calculator for the provided chart data.
     *
     * @param graph list of {@link XYChart.Data} where the last element is the
     *              latest round;
     *              must contain at least three points (at least two baseline points
     *              + latest)
     * @throws IllegalArgumentException if {@code graph.size() < 3}
     */
    public StandardDeviationCalculator(List<XYChart.Data<Number, Number>> graph) {
        if (graph.size() < 3) {
            throw new IllegalArgumentException(
                    "graph must contain at least two points (one for baseline and one latest)");
        }
        this.graph = graph;
        this.withoutLast = graph.subList(0, graph.size() - 1);
    }

    /**
     * Compute the arithmetic mean of Y values for the baseline points
     * ({@code withoutLast}).
     *
     * @return mean of baseline Y values
     */
    public double getMean() {
        double sum = 0.0;
        int count = 0;
        for (Data<Number, Number> point : withoutLast) {
            Number y = point.getYValue();
            sum += y.doubleValue();
            count++;
        }
        return sum / count;
    }

    /**
     * Compute the sample variance (s²) of the baseline Y values.
     * Uses the unbiased estimator dividing by (N - 1).
     *
     * @return sample variance of baseline Y values
     */
    public double getVariance() {
        double mean = getMean();
        double sumSq = 0.0;
        int count = 0;
        for (Data<Number, Number> point : withoutLast) {
            Number y = point.getYValue();
            double diff = y.doubleValue() - mean;
            sumSq += diff * diff;
            count++;
        }
        return sumSq / (count - 1);
    }

    /**
     * Compute the sample standard deviation (s) for the baseline Y values.
     *
     * @return standard deviation = sqrt(variance)
     */
    public double getStandardDeviation() {
        return Math.sqrt(getVariance());
    }

    /**
     * Compute the Z‑score of the latest Y value relative to the baseline:
     * Z = (R - μ) / s.
     *
     * <p>
     * If the standard deviation is zero (all baseline values identical),
     * the method returns 0.0.
     * </p>
     *
     * @return z‑score of the latest point (or 0.0 when SD == 0)
     */
    public double getZ() {
        Data<Number, Number> last = graph.get(graph.size() - 1);
        double latest = last.getYValue().doubleValue();
        double mean = getMean();
        double sd = getStandardDeviation();
        if (sd == 0.0) {
            return 0.0;
        }
        return (latest - mean) / sd;
    }

    /**
     * Convert the latest value's Z‑score into a percentile (0.0–100.0)
     * using the standard normal cumulative distribution function Φ(z).
     *
     * <p>
     * When the standard deviation is zero:
     * - if latest == mean → returns 50.0 (median)
     * - if latest > mean → returns 100.0
     * - otherwise → returns 0.0
     * </p>
     *
     * @return percentile in percent (0..100)
     */
    public double getPercentile() {
        Data<Number, Number> last = graph.get(graph.size() - 1);
        double latest = last.getYValue().doubleValue();

        double sd = getStandardDeviation();
        if (sd == 0.0) {
            double mean = getMean();
            if (Double.compare(latest, mean) == 0)
                return 50.0;
            return latest > mean ? 100.0 : 0.0;
        }

        double z = (latest - getMean()) / sd;
        return 100.0 * (0.5 * (1.0 + erf(z / Math.sqrt(2.0))));
    }

    /**
     * Approximate error function used to compute the normal CDF.
     * Implementation based on a common numerical approximation (Abramowitz &
     * Stegun).
     *
     * @param x input value
     * @return approximate erf(x)
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