package ch21.ex21_03;

import java.lang.ref.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class WeakValueMap<K, V> implements Map<K, V> {
  private Map<K, WeakReference<V>> map;

  WeakValueMap() {
    map = new HashMap<K, WeakReference<V>>();
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public boolean isEmpty() {
    return map.isEmpty();
  }

  @Override
  public boolean containsKey(Object key) {
    return map.containsKey(key);
  }

  @Override
  public boolean containsValue(Object value) {
    return map.containsValue(value);
  }

  @Override
  public V get(Object key) {
    return map.get(key).get();
  }

  @Override
  public V put(K key, V value) {
    return map.put(key, new WeakReference<V>(value)).get();
  }

  @Override
  public V remove(Object key) {
    return map.remove(key).get();
  }

  @Override
  public void putAll(Map<? extends K, ? extends V> m) {
    for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
      put(entry.getKey(), entry.getValue());
    }
  }

  @Override
  public void clear() {
    map.clear();
  }

  @Override
  public Set<K> keySet() {
    return map.keySet();
  }

  @Override
  public Collection<V> values() {
    Map<K, V> newMap = new HashMap<K, V>();
    for (Map.Entry<K, WeakReference<V>> entry : map.entrySet()) {
      if (entry.getValue() != null)
        newMap.put(entry.getKey(), entry.getValue().get());
    }
    return newMap.values();
  }

  @Override
  public Set<Map.Entry<K, V>> entrySet() {
    Map<K, V> newMap = new HashMap<K, V>();
    for (Map.Entry<K, WeakReference<V>> entry : map.entrySet()) {
      if (entry.getValue() != null)
        newMap.put(entry.getKey(), entry.getValue().get());
    }
    return newMap.entrySet();
  }
}
