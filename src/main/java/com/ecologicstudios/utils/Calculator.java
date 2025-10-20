package com.ecologicstudios.utils;

import java.util.Optional;
import java.util.Collection;

public interface Calculator<T extends Number> {
    void insertValue(T value);
    void insertValues(Collection<? extends T> list);
    Optional<T> calculate();
    void clear();
}
