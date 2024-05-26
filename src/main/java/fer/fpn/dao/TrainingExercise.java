package fer.fpn.dao;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TrainingExercise {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Training training;
    @ManyToOne
    private Exercise exercise;
    private Integer reps;
    private Integer sets;
    private Float weight;

    public TrainingExercise() {
    }

    public TrainingExercise(Exercise exercise, Integer reps, Integer sets, Float weight, Training training) {
        this.exercise = exercise;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
        this.training = training;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getSets() {
        return sets;
    }


    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingExercise that = (TrainingExercise) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(training, that.training) &&
               Objects.equals(exercise, that.exercise) &&
               Objects.equals(reps, that.reps) &&
               Objects.equals(sets, that.sets) &&
               Objects.equals(weight, that.weight);
    }
}
