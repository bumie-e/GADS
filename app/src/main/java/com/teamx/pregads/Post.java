package com.teamx.pregads;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("entry.1877115667")
    private String firstname;
    @SerializedName("entry.2006916086")
    private String lastname;
    @SerializedName("entry.1824927963")
    private String emailaddress;
    @SerializedName("entry.284483984")
    private String projectlink;



    public Post(String firstname, String lastname, String emailaddress, String projectlink) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailaddress = emailaddress;
        this.projectlink = projectlink;
    }
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public String getProjectlink() {
        return projectlink;
    }

}
