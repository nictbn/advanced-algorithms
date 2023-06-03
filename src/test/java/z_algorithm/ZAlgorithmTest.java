package z_algorithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZAlgorithmTest {
    ZAlgorithm z;
    @BeforeEach
    public void setUp() {
        z = new ZAlgorithm();
    }

    @Test
    public void createLongArrayTest() {
        assertArrayEquals("abc$edfabc".toCharArray(), z.createLongArray("abc".toCharArray(), "edfabc".toCharArray()));
    }

    @Test
    public void createZTableTest() {
        assertArrayEquals(new int[]{0, 0, 0, 1, 2, 0, 0}, z.createZTable("ab".toCharArray(), "aabb".toCharArray()));
    }
}