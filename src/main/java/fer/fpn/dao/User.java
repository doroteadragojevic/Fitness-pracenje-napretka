package fer.fpn.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long userId;

    @NotNull
    private String role;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String email;

    private String password;

    private Float dailyCalGoal;

    //ovo je id trenera ovog korisnika
    @ManyToOne
    private User trainer;

    public User(){}

    public User(String role, String name, String surname, String email, String password, Float dailyCalGoal, User trainer) {
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.dailyCalGoal = dailyCalGoal;
        this.trainer = trainer;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getDailyCalGoal() {
        return dailyCalGoal;
    }

    public void setDailyCalGoal(Float dailyCalGoal) {
        this.dailyCalGoal = dailyCalGoal;
    }

    public User getTrainer() {
        return trainer;
    }

    public void setTrainer(User trainer) {
        this.trainer = trainer;
    }
}
