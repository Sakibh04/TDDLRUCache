import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
    private final LinkedHashMap<Integer, Object> map;
    private final int capacity;

    public LRUCache(int size) {
        if (size <= 0) throw new IllegalArgumentException();
        this.capacity = size;
        this.map = new LinkedHashMap<Integer, Object>(size, 0.75f, true);
    }

    public int put(Object obj) {
        int hash = obj.hashCode();
        map.put(hash, obj);
        return hash;
    }

    public Object get(int hash) {
        Object val = map.get(hash);
        if (val == null) throw new IllegalStateException();
        return val;
    }
}
