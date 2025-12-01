import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LRUCacheTest {
    // Test 1: Constructor with valid size creates cache
    @Test
    void constructorWithValidSizeCreatesCache() {
        LRUCache newCache = new LRUCache(5);
        assertNotNull(newCache);
    }

     //Test 2: Constructor with invalid size throws exception (Test 1 and 2 can be run together)
    @Test
    void constructorWithZeroSizeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LRUCache(0);
        });
    }

    @Test
    void constructorWithNegativeSizeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LRUCache(-1);
        });
    }
}