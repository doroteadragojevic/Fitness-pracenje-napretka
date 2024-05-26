package fer.fpn.DTO;

import java.util.Objects;

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
    	if (training == null) {
            return null;
        }
        Long userId = null;
        if (training.getUser() != null) {
           userId = training.getUser().getUserId();
       }
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingDTO that = (TrainingDTO) o;
        return Objects.equals(idTraining, that.idTraining) &&
               Objects.equals(title, that.title) &&
               Objects.equals(description, that.description) &&
               Objects.equals(userId, that.userId);
    }
}
