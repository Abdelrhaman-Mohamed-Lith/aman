package com.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Sheet extends DocumentFile {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private Status status;
    private List<SheetLine> details;

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @OneToMany(mappedBy = "sheet")

    public List<SheetLine> getDetails() {
        return details;
    }

    public void setDetails(List<SheetLine> details) {
        this.details = details;
    }
}
