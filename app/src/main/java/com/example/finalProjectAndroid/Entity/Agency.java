package com.example.finalProjectAndroid.Entity;

public class Agency {
    private String id;
    private String code;
    private String agencyName;
    private String agencyDetails;
    private String owner;
//    private long createdDate;
//    private long updatedDate;

    public Agency(String name, String details, String userId) {
        this.agencyName = name;
        this.agencyDetails = details;
        this.owner = userId;
    }

    public Agency(String id, String code, String name, String details, String userId, long createdDate, long updatedDate) {
        this.id = id;
        this.code = code;
        this.agencyName = name;
        this.agencyDetails = details;
        this.owner = userId;
//        this.createdDate = createdDate;
//        this.updatedDate = updatedDate;
    }

    public Agency() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyDetails() {
        return agencyDetails;
    }

    public void setAgencyDetails(String agencyDetails) {
        this.agencyDetails = agencyDetails;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

//    public long getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(long createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    public long getUpdatedDate() {
//        return updatedDate;
//    }
//
//    public void setUpdatedDate(long updatedDate) {
//        this.updatedDate = updatedDate;
//    }

}
