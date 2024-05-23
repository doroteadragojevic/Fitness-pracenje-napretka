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
    private UserFPN user;

    @NotNull
    @ManyToOne
    private Dish dish;

    public FoodEntry(){}

    public FoodEntry(Date date, Float weightGrams, UserFPN user, Dish dish) {
        this.date = date;
        this.weightGrams = weightGrams;
        this.user = user;
        this.dish = dish;
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

    public UserFPN getUser() {
        return user;
    }

    public void setUser(UserFPN user) {
        this.user = user;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
