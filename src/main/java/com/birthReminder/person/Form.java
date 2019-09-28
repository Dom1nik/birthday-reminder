package com.birthReminder.person;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class Form {

    @NotNull(message = "please fill in this information")
    @Pattern(regexp = "^[a-zA-ZÀ-ž\\s]+$", message = "first name must not contain numbers")
    @Length(min = 1, max = 25)
    private String firstName;

    @NotNull(message = "please fill in this information")
    @Pattern(regexp = "^[a-zA-ZÀ-ž\\s]+$", message = "last name must not contain numbers")
    @Length(min = 1, max = 25)
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "please fill in this information")
    private LocalDate birthDate;

    private Boolean userSaved;

    public Form() {
        // Default constructor
    }

    public Form(Boolean userSaved) {
        this.userSaved = userSaved;
    }

    public Form(String firstName, String lastName, LocalDate birthDate) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setUserSaved(Boolean userSaved) { this.userSaved = userSaved; }

    public Boolean getUserSaved() { return userSaved; }

    @Override
    public String toString() {
        return "Form{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", userSaved=" + userSaved +
                '}';
    }
}
