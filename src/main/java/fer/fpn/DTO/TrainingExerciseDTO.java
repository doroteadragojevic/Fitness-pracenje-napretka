package fer.fpn.DTO;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import fer.fpn.dao.TrainingExercise;


public class TrainingExerciseDTO {

    private Long id;
    private Long idExercise;
    private Long idTraining;
    private String titleOfExercise;
 
    @NotNull(message = "Broj ponavljanja je obavezan")
    @Min(value = 1, message = "Broj ponavljanja mora biti veći od 0")
    private Integer reps;
    

    @NotNull(message = "Broj serija je obavezan")
    @Min(value = 1, message = "Broj serija mora biti veći od 0")
    private Integer sets;
    @NotNull(message = "Potrebno je unijeti kilažu")
    @Min(value = 0, message = "Kilaža mora biti između 0 i 300")
    @Max(value = 300, message = "Kilaža mora biti između 0 i 300")
    private Float weight;

    public TrainingExerciseDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrainingExerciseDTO(Long idTraining) {
        this.idTraining = idTraining;
    }


    public Long getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(Long idTraining) {
        this.idTraining = idTraining;
    }

    public TrainingExerciseDTO(Long id, Long idExercise, String titleOfExercise, Integer reps, Integer sets, Float weight, Long idTraining) {
        this.id = id;
        this.idExercise = idExercise;
        this.titleOfExercise = titleOfExercise;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
        this.idTraining = idTraining;
    }

    public static TrainingExerciseDTO toDto(TrainingExercise te){
        return new TrainingExerciseDTO(
                te.getId(),
                te.getExercise().getIdExercise(),
                te.getExercise().getTitle(),
                te.getReps(),
                te.getSets(),
                te.getWeight(),
                te.getTraining() == null ? null : te.getTraining().getIdTraining());
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
