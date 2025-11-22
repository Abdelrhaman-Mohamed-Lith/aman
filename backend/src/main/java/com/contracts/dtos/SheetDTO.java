package com.contracts.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class SheetDTO extends DocumentFileDTO {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private String status;
    private List<SheetLineDTO> details;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SheetLineDTO> getDetails() {
        return details;
    }

    public void setDetails(List<SheetLineDTO> details) {
        this.details = details;
    }
}
