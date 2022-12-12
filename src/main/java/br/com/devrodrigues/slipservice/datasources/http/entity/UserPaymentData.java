package br.com.devrodrigues.slipservice.datasources.http.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPaymentData {

    @JsonProperty("user")
    private String user;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("people_document")
    private String peopleDocument;

    @JsonProperty("address")
    private String address;

    @JsonProperty("phone")
    private String phone;

    public UserPaymentData() {
    }

    public UserPaymentData(String user, String fullName, String peopleDocument, String address, String phone) {
        this.user = user;
        this.fullName = fullName;
        this.peopleDocument = peopleDocument;
        this.address = address;
        this.phone = phone;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPeopleDocument() {
        return peopleDocument;
    }

    public void setPeopleDocument(String peopleDocument) {
        this.peopleDocument = peopleDocument;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
