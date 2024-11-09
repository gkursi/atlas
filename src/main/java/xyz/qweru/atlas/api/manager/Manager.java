package xyz.qweru.atlas.api.manager;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Manager<T> {
    List<T> list = new ArrayList<>();
    public void add(T item) {
        list.add(item);
    }

    public List<T> getAll() {
        return list;
    }

    public @Nullable T getByClass(Class<?> c) {
        for (T t : list) {
            if(t.getClass() == c) return t;
        }
        return null;
    }

    public boolean contains(T obj) {
        return list.contains(obj);
    }
}
