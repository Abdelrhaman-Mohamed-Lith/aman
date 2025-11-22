package com.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DeliveryManRegionLine extends AbsBaseLine {
    private DeliveryMan deliveryMan;
    private Region region;

    @ManyToOne
    public DeliveryMan getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    @ManyToOne
    @JoinColumn(name = "region_id")
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
