package com.contracts.dtos;

public class MemberDTO extends MasterFileDTO{
    private String registrationNumber;
    private String medicalCardNumber;
    private String syndicate;
    private String whatsappNumber;
    private String phone1;
    private String phone2;
    private String governorate;
    private String address;
    private RegionDTO region;
    private String remarks;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getMedicalCardNumber() {
        return medicalCardNumber;
    }

    public void setMedicalCardNumber(String medicalCardNumber) {
        this.medicalCardNumber = medicalCardNumber;
    }

    public String getSyndicate() {
        return syndicate;
    }

    public void setSyndicate(String syndicate) {
        this.syndicate = syndicate;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

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

    public String getGovernorate() {
        return governorate;
    }

    public void setGovernorate(String governorate) {
        this.governorate = governorate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public RegionDTO getRegion() {
        return region;
    }

    public void setRegion(RegionDTO region) {
        this.region = region;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
