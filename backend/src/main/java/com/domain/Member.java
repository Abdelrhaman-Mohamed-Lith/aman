package com.domain;

import com.Persistence.Persister;
import com.utilities.ObjectChecker;
import com.utilities.Result;
import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
public class Member extends MasterFile {
    private String registrationNumber;
    private String medicalCardNumber;
    private Syndicate syndicate;
    private String whatsappNumber;
    private String phone1;
    private String phone2;
    private Governorate governorate;
    private String address;

    private Region region;
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

    @Enumerated(EnumType.STRING)
    public Syndicate getSyndicate() {
        return syndicate;
    }

    public void setSyndicate(Syndicate syndicate) {
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

    @Enumerated(EnumType.STRING)
    public Governorate getGovernorate() {
        return governorate;
    }

    public void setGovernorate(Governorate governorate) {
        this.governorate = governorate;
    }

    @Nationalized
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id")
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public void updateCalculatedFields(Result result) {
        setCode(getRegistrationNumber() + "_" + calcSyndicatePrefix(getSyndicate()));
    }

    private String calcSyndicatePrefix(Syndicate syndicate) {
        if (ObjectChecker.areEqual(syndicate, Syndicate.Medical)) return "1";
        if (ObjectChecker.areEqual(syndicate, Syndicate.Pharmaceutical)) return "2";
        if (ObjectChecker.areEqual(syndicate, Syndicate.Dentists)) return "3";
        return "4";
    }

    @Transient
    @Override
    public void isValidForCommit(Result result) {
        super.isValidForCommit(result);
        if (getId()==null) {
            int count = Persister.countOf(getClass().getSimpleName(), " where  registrationNumber=:registrationNo and syndicate=:syndicate", Persister.params("registrationNo", getRegistrationNumber(), "syndicate", ObjectChecker.toStringOrEmpty(getSyndicate())));
            if (count > 0) result.failure("This member already exists");
        }
    }
}
