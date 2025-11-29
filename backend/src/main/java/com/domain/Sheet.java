package com.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Sheet extends DocumentFile {
    private FiscalYear fiscalYear;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private Status status;
    private List<SheetLine> details;
    private List<SheetLine> problems;

    @ManyToOne
    @JoinColumn(name = "fiscal_year_id")
    public FiscalYear getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(FiscalYear fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

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

    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true)

    public List<SheetLine> getDetails() {
        return details;
    }

    public void setDetails(List<SheetLine> details) {
        this.details = details;
    }

    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<SheetLine> getProblems() {
        return problems;
    }

    public void setProblems(List<SheetLine> problems) {
        this.problems = problems;
    }
}
