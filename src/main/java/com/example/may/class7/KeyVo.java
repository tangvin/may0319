package com.example.may.class7;

import java.util.Objects;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/27   21:22
 * @version: 1.0
 * @modified:
 */
public class KeyVo {

    private final int id;
    private final String Name;

    public KeyVo(int id, String name) {
        this.id = id;
        Name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyVo keyVo = (KeyVo) o;
        return id == keyVo.id &&
                Objects.equals(Name, keyVo.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Name);
    }
}
