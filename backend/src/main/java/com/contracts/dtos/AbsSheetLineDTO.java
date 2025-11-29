package com.contracts.dtos;

public abstract class AbsSheetLineDTO {
    private Long id;
    private SheetDTO sheet;
    private OrderDTO order;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SheetDTO getSheet() {
        return sheet;
    }

    public void setSheet(SheetDTO sheet) {
        this.sheet = sheet;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
