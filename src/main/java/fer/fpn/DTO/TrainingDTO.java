package fer.fpn.DTO;

import fer.fpn.dao.Training;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

public class TrainingDTO {
    public TrainingDTO() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private Long idTraining;

    private String title;

    private String description;
    private Long userId;
    private List<Long> ids;
    private String userName;
    private String userSurname;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public TrainingDTO(Long idTraining, String title, String description, Long userId, List<Long> ids, String username, String usersurname) {
        this.idTraining = idTraining;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.ids = ids;
        this.userName = username;
        this.userSurname = usersurname;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public static TrainingDTO toDto(Training training) {
        return new TrainingDTO(training.getIdTraining(), training.getTitle(), training.getDescription(), training.getUser().getUserId(), training.getTrainingExercisesIds(), training.getUser().getName(), training.getUser().getSurname());
    }

    public Long getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(Long idTraining) {
        this.idTraining = idTraining;
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
}
