package com.ecologicstudios.utils;

import java.util.Optional;
import java.util.Collection;

/**
 * A generic interface for performing calculations on numerical data.
 *
 * @param <T> The type of numerical data to be processed, extending {@link Number}.
 * 
 * @author EcoLogic Studios
 * @version 1.0
 */
public interface Calculator<T extends Number> {
    /**
     * Inserts a single numerical value into the calculator.
     *
     * @param value The numerical value to be added.
     */
    void insertValue(T value);

    /**
     * Inserts a collection of numerical values into the calculator.
     *
     * @param list The collection of numerical values to be added.
     */
    void insertValues(Collection<? extends T> list);

    /**
     * Performs the calculation and returns the result.
     *
     * @return An {@link Optional} containing the result of the calculation, or
     *         {@link Optional#empty()} if no calculation can be performed.
     */
    Optional<T> calculate();

    /**
     * Clears all stored values in the calculator.
     */
    void clear();
}
