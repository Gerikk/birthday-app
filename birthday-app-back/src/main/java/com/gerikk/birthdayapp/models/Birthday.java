package com.gerikk.birthdayapp.models;

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

    private LocalDate date;

    private String firstname;

    private String lastname;

    public Birthday() {
        super();
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
