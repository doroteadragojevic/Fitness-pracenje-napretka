package fer.fpn.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
public class Exercise {

    @Id
    @GeneratedValue
    private Long idExercise;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @ManyToOne
    private UserFPN user;

    public Exercise(){}

    public Exercise(String description, String title, UserFPN user) {
        this.description = description;
        this.title = title;
        this.user = user;
    }

    public Long getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(Long idExercise) {
        this.idExercise = idExercise;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserFPN getUser() {
        return user;
    }

    public void setUser(UserFPN user) {
        this.user = user;
    }
}
