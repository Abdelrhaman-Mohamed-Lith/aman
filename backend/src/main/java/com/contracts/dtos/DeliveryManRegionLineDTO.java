package com.contracts.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DeliveryManRegionLineDTO {
    private Long id;
    private RegionDTO region;
    @JsonIgnore
    private DeliveryManDTO deliveryMan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegionDTO getRegion() {
        return region;
    }

    public void setRegion(RegionDTO region) {
        this.region = region;
    }

    public DeliveryManDTO getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(DeliveryManDTO deliveryMan) {
        this.deliveryMan = deliveryMan;
    }
}
