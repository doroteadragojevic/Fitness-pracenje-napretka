package fer.fpn.dao;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Date;

@Entity
public class FoodEntry {

    @Id
    @GeneratedValue
    private Long idFoodEntry;

    @NotNull
    private Date date;

    private Float weightGrams;

    @NotNull
    @ManyToOne
    private Long idUser;

    @NotNull
    @ManyToOne
    private Long idDish;

    public FoodEntry(){}

    public FoodEntry(Date date, Float weightGrams, Long idUser, Long idDish) {
        this.date = date;
        this.weightGrams = weightGrams;
        this.idUser = idUser;
        this.idDish = idDish;
    }

    public Long getIdFoodEntry() {
        return idFoodEntry;
    }

    public void setIdFoodEntry(Long idFoodEntry) {
        this.idFoodEntry = idFoodEntry;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getWeightGrams() {
        return weightGrams;
    }

    public void setWeightGrams(Float weightGrams) {
        this.weightGrams = weightGrams;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdDish() {
        return idDish;
    }

    public void setIdDish(Long idDish) {
        this.idDish = idDish;
    }
}
