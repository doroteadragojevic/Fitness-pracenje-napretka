package fer.fpn.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class TrainingExercise {

    @Id
    @OneToOne
    private Long idTraining;

    @Id
    @OneToOne
    private Long idUser;

    private Integer reps;

    private Integer sets;

    private Float weight;

    public TrainingExercise(){}

    public TrainingExercise(Long idUser, Integer reps, Integer sets, Float weight, Long idTraining) {
        this.idUser = idUser;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
        this.idTraining = idTraining;
    }

    public Long getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(Long idTraining) {
        this.idTraining = idTraining;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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
}
