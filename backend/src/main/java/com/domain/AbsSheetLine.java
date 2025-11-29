package com.domain;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;

@MappedSuperclass
public abstract class AbsSheetLine extends AbsBaseLine {
    private Sheet sheet;
    private MemberOrder order;
    private Status status;

    @ManyToOne
    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    @OneToOne
    @JoinColumn(name = "order_id")
    public MemberOrder getOrder() {
        return order;
    }

    public void setOrder(MemberOrder order) {
        this.order = order;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
