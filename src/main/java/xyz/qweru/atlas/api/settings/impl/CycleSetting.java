package xyz.qweru.atlas.api.settings.impl;

import xyz.qweru.atlas.api.settings.Setting;

import java.util.ArrayList;
import java.util.List;

public class CycleSetting<T extends Enum<?>> extends Setting {

    List<T> values;
    T current;
    List<String> displayValues;
    int index = 0;

    public CycleSetting(String name, String description, T defaultValue) {
        super(name, description);

        values = new ArrayList<>(List.of((T[]) defaultValue.getDeclaringClass().getEnumConstants()));
        set(defaultValue);
        displayValues = new ArrayList<>();
        for (T value : values) {
            displayValues.add(value.name());
        }
    }

    public List<T> getList() {
        return values;
    }

    public List<String> getDisplayList() {
        return displayValues;
    }

    public void next() {
        index++;
        if(index >= values.size()) index = 0;
        current = values.get(index);
        valueChanged();
    }

    public void set(T obj) {
        for (int i = 0; i < values.size(); i++) {
            if(values.get(i) == obj)  {
                index = i;
                current = values.get(i);
                valueChanged();
                return;
            }
        }

        throw new RuntimeException("Object is not a possible value!");
    }

    public void set(String name) {
        for (int i = 0; i < values.size(); i++) {
            if(values.get(i).name().equalsIgnoreCase(name))  {
                index = i;
                current = values.get(i);
                valueChanged();
                return;
            }
        }

        throw new RuntimeException("Name is not a possible value!");
    }

    @Override
    public void onBind() {
        next();
    }

    public T getCurrent() {
        return current;
    }
}
