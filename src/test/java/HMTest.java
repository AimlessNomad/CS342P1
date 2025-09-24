import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;

class HMTest {

    static MyHashMap<Integer> map;

    @BeforeAll
    static void setup() {
        map = new MyHashMap<>("One", 1);
    }

    @Test
    void constructorTest() {
        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
        assertTrue(map.contains("One"));
    }

    @Test
    void putTest() {
        // without replacement
        map.put("Two", 2);
        map.put("Three", 3);
        map.put("3", 3);
        map.put("Four", 4);

        assertEquals(5, map.size());
        assertTrue(map.contains("Two"));
        assertTrue(map.contains("Three"));
        assertTrue(map.contains("3"));
        assertEquals(4, map.get("Four"));

        // with replacement
        map.put("Three", 47);
        assertEquals(5, map.size());
        assertEquals(47, map.get("Three"));
    }

    @Test
    void replaceTest() {
        map.put("Two", 2);
        map.put("Three", 3);
        map.put("3", 3);
        map.put("Four", 4);

        assertEquals(2, map.replace("Two", 22));
        assertEquals(4, map.replace("Four", 44));
        assertNull(map.replace("Twenty", 20));

        assertEquals(5, map.size());
        assertEquals(22, map.get("Two"));
        assertEquals(44, map.get("Four"));
        assertFalse(map.contains("Twenty"));
    }
}
