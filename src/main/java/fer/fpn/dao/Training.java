package fer.fpn.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
public class Training {

    @Id
    @GeneratedValue
    private Long idTraining;

    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    @ManyToOne
    private UserFPN user;

    public Training() {
    }

    public Training(String title, String description, UserFPN user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public Long getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(Long idTraining) {
        this.idTraining = idTraining;
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
