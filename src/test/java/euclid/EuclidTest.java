package euclid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EuclidTest {
    Euclid e = null;
    @BeforeEach
    public void setUp() {
        e = new Euclid();
    }
    @Test
    public void greatestCommonDivisorIterative() {
        assertEquals(2, e.iterativeGreatestCommonDivisor(10, 2));
        assertEquals(2, e.iterativeGreatestCommonDivisor(22, 6));
    }

    @Test
    public void greatestCommonDivisorRecursive() {
        assertEquals(2, e.recursiveGreatestCommonDivisor(10, 2));
        assertEquals(2, e.recursiveGreatestCommonDivisor(22, 6));
    }

    @Test
    public void whenParametersAreTheSame_thenTheResultsAreTheSame() {
        assertEquals(e.recursiveGreatestCommonDivisor(102, 74), e.iterativeGreatestCommonDivisor(102, 74));
    }
}