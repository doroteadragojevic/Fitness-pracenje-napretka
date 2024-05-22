package fer.fpn.DTO;

import fer.fpn.dao.TrainingExercise;

public class TrainingExerciseDTO {

    private Long idExercise;
    private Long idTraining;
    private String titleOfExercise;
    private Integer reps;
    private Integer sets;
    private Float weight;

    public Long getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(Long idTraining) {
        this.idTraining = idTraining;
    }

    public TrainingExerciseDTO(Long idExercise, String titleOfExercise, Integer reps, Integer sets, Float weight, Long idTraining) {
        this.idExercise = idExercise;
        this.titleOfExercise = titleOfExercise;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
        this.idTraining = idTraining;
    }

    public static TrainingExerciseDTO toDto(TrainingExercise te){
        return new TrainingExerciseDTO(te.getExercise().getIdExercise(), te.getExercise().getTitle(), te.getReps(), te.getSets(), te.getWeight(), te.getTraining().getIdTraining());
    }

    public Long getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(Long idExercise) {
        this.idExercise = idExercise;
    }

    public String getTitleOfExercise() {
        return titleOfExercise;
    }

    public void setTitleOfExercise(String titleOfExercise) {
        this.titleOfExercise = titleOfExercise;
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
