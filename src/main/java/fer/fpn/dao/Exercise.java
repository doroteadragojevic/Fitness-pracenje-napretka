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
    @NotNull
    private Long idUser;

    private Exercise(){}

    public Exercise(String description, String title, Long idUser) {
        this.description = description;
        this.title = title;
        this.idUser = idUser;
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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
