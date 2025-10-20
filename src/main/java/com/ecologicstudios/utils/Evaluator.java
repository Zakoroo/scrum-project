package com.ecologicstudios.utils;

import java.util.Collection;
import java.util.Optional;

/**
 * A generic interface for evaluating numerical data.
 *
 * @param <T> The type of numerical data to be processed, extending {@link Number}.
 * 
 * @author EcoLogic Studios
 * @version 1.0
 */
public interface Evaluator<T extends Number> {
    /**
     * Inserts a single numerical value into the evaluator.
     *
     * @param value The numerical value to be added.
     */
    void insertValue(T value);

    /**
     * Inserts a collection of numerical values into the evaluator.
     *
     * @param list The collection of numerical values to be added.
     */
    void insertValues(Collection<? extends T> list);

    /**
     * Performs the evaluation and returns the result.
     *
     * @return An {@link Optional} containing the result of the evaluation, or
     *         {@link Optional#empty()} if no evaluation can be performed.
     */
    Optional<T> evaluate();

    /**
     * Clears all stored values in the evaluator.
     */
    void clear();
}
