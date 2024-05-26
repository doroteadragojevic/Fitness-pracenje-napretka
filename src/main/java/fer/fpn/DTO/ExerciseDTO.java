package fer.fpn.DTO;

import java.util.Objects;

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
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ExerciseDTO that = (ExerciseDTO) o;
//        return Objects.equals(idExercise, that.idExercise) &&
//                Objects.equals(title, that.title) &&
//                Objects.equals(description, that.description) &&
//                Objects.equals(idUser, that.idUser);
//    }
    


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseDTO that = (ExerciseDTO) o;
        return Objects.equals(idExercise, that.idExercise) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(idUser, that.idUser); 
    }
    @Override
    public int hashCode() {
        return Objects.hash(idExercise, title, description, idUser);
    }
}
