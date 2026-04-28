package uptc.edu.swii.login.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

    private String document;

    public Customer(){}

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}