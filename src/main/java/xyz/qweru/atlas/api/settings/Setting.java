package xyz.qweru.atlas.api.settings;

import java.util.ArrayList;

public abstract class Setting {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    protected void valueChanged() {
        list.forEach(changeListener -> changeListener.run(this));
    }

    public Setting(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    ArrayList<ChangeListener> list = new ArrayList<>();
    public void addChangeListener(ChangeListener listener) {
        list.add(listener);
    }

    private int bind = -1;
    public void bind(int bind) {
        this.bind = bind;
    }

    public int getBind() {
        return bind;
    }

    public abstract void onBind();

    @FunctionalInterface
    public interface ChangeListener {
        void run(Setting setting);
    }
}
