package org.liang.domain.entity;

import org.liang.domain.BaseEntity;

public class City extends BaseEntity<Long> {

    private static final long serialVersionUID = -8165330582491668500L;

    private String name;

    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
