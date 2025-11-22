package com.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class DeliveryMan extends MasterFile {
    private String phone1;
    private String phone2;
    private List<DeliveryManRegionLine> regions;

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "deliveryMan", fetch = FetchType.EAGER)
    @OrderColumn(name = "line_number")
    public List<DeliveryManRegionLine> getRegions() {
        return regions;
    }

    public void setRegions(List<DeliveryManRegionLine> regions) {
        this.regions = regions;
    }
}
