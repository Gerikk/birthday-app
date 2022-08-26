package com.gerikk.birthdayapp.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Birthday> birthdays;

    public User() {
        super();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String toJson() {

        try {
            // create `ObjectMapper` instance
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            // create a JSON object
            // convert `ObjectNode` to pretty-print JSON
            // without pretty-print, use `user.toString()` method
            // print json
            return mapper.writeValueAsString(this);
            //System.out.println(json);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public Set<Birthday> getBirthdays() {
        return birthdays;
    }

    public void setBirthdays(Set<Birthday> birthdays) {
        this.birthdays = birthdays;
    }
}
