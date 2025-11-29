package com.domain;

import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.Nationalized;

@MappedSuperclass
public abstract class MasterFile extends BaseEntity {
    private String name;

    @Nationalized
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
