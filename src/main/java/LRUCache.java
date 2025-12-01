import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private final Map<Integer, Object> map = new HashMap<>();

    public LRUCache(int i) {
        if (i <= 0) throw new IllegalArgumentException();
    }

    public int put(Object obj) {
        int hash = obj.hashCode();
        map.put(hash, obj);
        return hash;
    }

    public Object get(int hash) {
        if (!map.containsKey(hash))
            throw new IllegalArgumentException();
        return map.get(hash);
    }
}
