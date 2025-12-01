import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LRUCacheTest {
    private LRUCache cache;

    @BeforeEach
    void setUp() {
        // Common setup - each test gets a fresh cache
        cache = new LRUCache(3);
    }

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

    // Test 3: Put returns hash code of added object
    @Test
    void putReturnsHashCodeOfAddedObject() {
        Object obj = new Object();
        int hash = cache.put(obj);
        assertEquals(obj.hashCode(), hash);
    }

    // Test 4: Get retrieves object by hash code
    @Test
    void getRetrievesObjectByHashCode() {
        Object obj = "TestString";
        int hash = cache.put(obj);
        Object retrieved = cache.get(hash);
        assertEquals(obj, retrieved);
    }

    // Test 5: Get throws exception for non-existent hash
    @Test
    void getThrowsExceptionForNonExistentHash() {
        assertThrows(IllegalArgumentException.class, () -> {
            cache.get(99999);
        });
    }

    // Test 6: Put same object twice updates recentness
    @Test
    void putSameObjectTwiceUpdatesRecentness() {
        Object obj = "TestObject";
        int hash1 = cache.put(obj);
        int hash2 = cache.put(obj);
        assertEquals(hash1, hash2);
        assertEquals(obj, cache.get(hash1));
    }

}


