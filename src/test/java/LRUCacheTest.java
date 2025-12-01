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

    // Test 7: Cache evicts LRU item when capacity exceeded
    @Test
    void cacheEvictsLeastRecentlyUsedItemWhenFull() {
        Object obj1 = "First";
        Object obj2 = "Second";
        Object obj3 = "Third";
        Object obj4 = "Fourth";

        int hash1 = cache.put(obj1);
        int hash2 = cache.put(obj2);
        int hash3 = cache.put(obj3);

        // Cache is now full (capacity 3)
        // Adding fourth item should evict first
        cache.put(obj4);

        assertThrows(IllegalArgumentException.class, () -> {
            cache.get(hash1);
        });
    }

    // Test 8: Get updates recentness preventing eviction
    @Test
    void getUpdatesRecentnessPreventingEviction() {
        Object obj1 = "First";
        Object obj2 = "Second";
        Object obj3 = "Third";
        Object obj4 = "Fourth";

        int hash1 = cache.put(obj1);
        int hash2 = cache.put(obj2);
        int hash3 = cache.put(obj3);

        // Access obj1 to make it recently used
        cache.get(hash1);

        // Add fourth item - should evict obj2 (least recent)
        cache.put(obj4);

        // obj1 should still be accessible
        assertEquals(obj1, cache.get(hash1));

        // obj2 should be evicted
        assertThrows(IllegalArgumentException.class, () -> {
            cache.get(hash2);
        });
    }

    // Test 9: Multiple puts maintain correct order
    @Test
    void multiplePutsMaintainCorrectEvictionOrder() {
        Object obj1 = "A";
        Object obj2 = "B";
        Object obj3 = "C";

        int hash1 = cache.put(obj1);
        int hash2 = cache.put(obj2);

        // Update obj1's recentness
        cache.put(obj1);

        int hash3 = cache.put(obj3);

        // Add fourth item - obj2 should be evicted (least recent)
        Object obj4 = "D";
        cache.put(obj4);

        assertThrows(IllegalArgumentException.class, () -> {
            cache.get(hash2);
        });
    }

    // Test 10: Cache handles different object types
    @Test
    void cacheHandlesDifferentObjectTypes() {
        String str = "String";
        Integer num = 42;

        int hashStr = cache.put(str);
        int hashNum = cache.put(num);

        assertEquals(str, cache.get(hashStr));
        assertEquals(num, cache.get(hashNum));
    }

    // Test 11: Empty cache throws exception on get
    @Test
    void emptyCacheThrowsExceptionOnGet() {
        assertThrows(IllegalArgumentException.class, () -> {
            cache.get(12345);
        });
    }

    // Test 12: Cache with capacity 1 works correctly
    @Test
    void cacheWithCapacityOneWorksCorrectly() {
        LRUCache smallCache = new LRUCache(1);
        Object obj1 = "First";
        Object obj2 = "Second";

        int hash1 = smallCache.put(obj1);
        int hash2 = smallCache.put(obj2);

        // First object should be evicted
        assertThrows(IllegalArgumentException.class, () -> {
            smallCache.get(hash1);
        });

        assertEquals(obj2, smallCache.get(hash2));
    }
}


