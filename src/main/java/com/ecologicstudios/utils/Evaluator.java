package com.ecologicstudios.utils;

import java.util.Collection;
import java.util.Optional;

public interface Evaluator<T extends Number> {
    void insertValue(T value);
    void insertValues(Collection<? extends T> list);
    Optional<T> evaluate();
    void clear();
}
