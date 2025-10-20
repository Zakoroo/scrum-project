package com.ecologicstudios;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.ecologicstudios.utils.Calculator;
import com.ecologicstudios.utils.StandardDeviationCalculator;
import com.ecologicstudios.utils.ScoreRescaler;

public class AppTest {
    @Test
    public void ScoreScaler_test_NaN() { // not-a-number
        assertThrows(IllegalArgumentException.class, () -> ScoreRescaler.evaluate(Double.NaN, 0.1, 0.2));
    }

    @Test
    public void ScoreScaler_test_positive_infinity() { // positive infinity
        assertThrows(IllegalArgumentException.class, () -> ScoreRescaler.evaluate(Double.POSITIVE_INFINITY, 0.1, 0.2)); 
    }

    @Test
    public void ScoreScaler_test_negative_infinity() { // negative infinity
        assertThrows(IllegalArgumentException.class, () -> ScoreRescaler.evaluate(Double.NEGATIVE_INFINITY, 0.1, 0.2)); 
    }

    @Test
    public void ScoreScaler_test_equal_limits() { // best == worst
        assertThrows(IllegalArgumentException.class, () -> ScoreRescaler.evaluate(11.1, 9.0, 9.0));
    }

    @Test
    public void ScoreScaler_test_correct_value() { // correct values
        assertEquals(10, ScoreRescaler.evaluate(10, 100, 0));
        assertEquals(10, ScoreRescaler.evaluate(20, 200, 0));
        assertEquals(10, ScoreRescaler.evaluate(30, 300, 0));
        assertEquals(10, ScoreRescaler.evaluate(40, 400, 0));
        assertEquals(10, ScoreRescaler.evaluate(50, 500, 0));
    }
}
