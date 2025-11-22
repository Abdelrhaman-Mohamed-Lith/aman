package com.contracts.dtos;


import java.util.List;

public class DeliveryManDTO extends MasterFileDTO{
    private String phone1;
    private String phone2;
    private List<DeliveryManRegionLineDTO> regions;

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

    public List<DeliveryManRegionLineDTO> getRegions() {
        return regions;
    }

    public void setRegions(List<DeliveryManRegionLineDTO> regions) {
        this.regions = regions;
    }
}
