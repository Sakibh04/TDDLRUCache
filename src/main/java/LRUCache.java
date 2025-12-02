import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A simple Least Recently Used (LRU) cache implementation
 * This cache stores objects with their hash codes as keys and
 * automatically evicts the least recently used object when capacity is exceeded.
 * Accessing or re-putting an object updates it recentness.
 */
public class LRUCache {
    private final LinkedHashMap<Integer, Object> map;
    private final int capacity;

    /**
     * Constructs an LRUCache with the specified capacity.
     *
     * @param size the maximum number of objects the cache can hold
     * @throws IllegalArgumentException if size is zero or negative
     */
    public LRUCache(int size) {
        if (size <= 0) throw new IllegalArgumentException();
        this.capacity = size;
        this.map = new LinkedHashMap<Integer, Object>(size, 0.75f, true);
    }

    /**
     * Adds an object to the cache. If the object already exists, its recentness is updated.
     * Ensures capacity by removing the least recently used item.
     *
     * @param obj the object to add to the cache
     * @return the hash code of the object
     */
    public int put(Object obj) {
        int hash = obj.hashCode();
        map.put(hash, obj);
        if (map.size() > capacity) {
            Integer lruKey = map.keySet().iterator().next();
            map.remove(lruKey);
        }
        return hash;
    }

    /**
     * Retrieves an object from the cache by its hash code
     * Accessing an object updates its recentness in the cache
     *
     * @param hash the hash code of the object to retrieve
     * @return the cached object corresponding to the hash code
     * @throws IllegalArgumentException if no object with the given hash exists in the cache
     */
    public Object get(int hash) {
        Object val = map.get(hash);
        if (val == null) {
            throw new IllegalArgumentException("No such hash in cache");
        }
        return val;
    }
}
