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
    public void searchTest() {
        assertEquals(-1, z.search("cc".toCharArray(), "dfabfabds".toCharArray()));
        assertEquals(2, z.search("ab".toCharArray(), "dfabfabds".toCharArray()));
        assertEquals(12, z.search("name".toCharArray(), "I knew your name already.".toCharArray()));
    }

    @Test
    public void searchAllTest() {
        assertArrayEquals(new int[]{0,0,0,0,0,0,0,0,0}, z.searchAll("cc".toCharArray(), "dfabfabds".toCharArray()));
        assertArrayEquals(new int[]{0,0,2,0,0,2,0,0,0}, z.searchAll("ab".toCharArray(), "dfabfabds".toCharArray()));
        assertArrayEquals(new int[]{0,0,0,4,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0}, z.searchAll("name".toCharArray(), "My name is my name.".toCharArray()));
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