package fer.fpn.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;

import java.util.List;

@Entity
public class Dish {

    @Id
    @GeneratedValue
    @NotNull
    private Long idDish;

    @NotNull
    private String name;

    private Integer calories;

    private List<String> ingredients;

    public Dish(){}

    public Dish(String name, Integer calories, List<String> ingredients) {
        this.name = name;
        this.calories = calories;
        this.ingredients = ingredients;
    }

    public Long getIdDish() {
        return idDish;
    }

    public void setIdDish(Long idDish) {
        this.idDish = idDish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
