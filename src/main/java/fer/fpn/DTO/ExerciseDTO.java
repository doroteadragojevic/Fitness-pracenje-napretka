package fer.fpn.DTO;

import fer.fpn.dao.Exercise;

public class ExerciseDTO {

    private Long idExercise;

    private String title;

    private String description;

    private Long idUser;

    public ExerciseDTO() {

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

    public ExerciseDTO(Long idExercise, String title, String description, Long idUser) {
        this.idExercise = idExercise;
        this.title = title;
        this.description = description;
        this.idUser = idUser;
    }

    public static ExerciseDTO toDto(Exercise exercise){
        return new ExerciseDTO(
                exercise.getIdExercise(),
                exercise.getTitle(),
                exercise.getDescription(),
                exercise.getUser() == null ? null : exercise.getUser().getUserId());
    }
}
