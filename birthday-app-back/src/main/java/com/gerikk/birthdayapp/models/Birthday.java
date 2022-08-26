package com.gerikk.birthdayapp.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "birthday", schema = "public")
public class Birthday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd ")
    private LocalDate date;

    private String firstname;

    private String lastname;

    public Birthday() {
        super();
    }

    public Birthday(Long birthdayId, LocalDate date, String firstname, String lastname, User user) {
        super();
        this.id = birthdayId;
        this.date = date;
        this.firstname = firstname;
        this.lastname = lastname;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Birthday{" +
                "id=" + id +
                ", user=" + user +
                ", date=" + date +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
