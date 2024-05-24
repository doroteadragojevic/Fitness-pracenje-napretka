package fer.fpn.DTO;

import fer.fpn.dao.Training;

public class TrainingDTO {


    private Long idTraining;

    private String title;

    private String description;
    private Long userId;
    private String userName;
    private String userSurname;

    public TrainingDTO() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public TrainingDTO(Long idTraining, String title, String description, Long userId, String username, String usersurname) {
        this.idTraining = idTraining;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.userName = username;
        this.userSurname = usersurname;
    }


    public static TrainingDTO toDto(Training training) {
        return new TrainingDTO(training.getIdTraining(), training.getTitle(), training.getDescription(), training.getUser().getUserId(), training.getUser().getName(), training.getUser().getSurname());
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
