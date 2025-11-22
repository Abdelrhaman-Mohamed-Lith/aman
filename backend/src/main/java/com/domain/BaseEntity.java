package com.domain;

import com.utilities.Result;
import com.utilities.ValidatorUtil;
import jakarta.persistence.*;
import com.utilities.IEntity;

import java.time.LocalDate;

@MappedSuperclass
public abstract class BaseEntity implements IEntity {
    private Long id;
    private String code;
    private LocalDate creationDate;

    public BaseEntity() {
        creationDate = LocalDate.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Transient
    public void isValidForCommit(Result result) {
        ValidatorUtil.isEmptyRequired(getCode(), "Code", result);
    }
}
