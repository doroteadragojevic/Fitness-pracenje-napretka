package fer.fpn.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Date;

@Entity
public class Progress {


    @Id
    @GeneratedValue
    private Long idProgress;

    @NotNull
    private Date date;

    @NotNull
    private Float bodyWeight;

    private String comment;

    @NotNull
    @ManyToOne
    private User user;

    public Progress(){}

    public Progress(Date date, Float bodyWeight, String comment, User user) {
        this.date = date;
        this.bodyWeight = bodyWeight;
        this.comment = comment;
        this.user = user;
    }

    public Long getIdProgress() {
        return idProgress;
    }

    public void setIdProgress(Long idProgress) {
        this.idProgress = idProgress;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(Float bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
