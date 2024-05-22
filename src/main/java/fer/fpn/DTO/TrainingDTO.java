package fer.fpn.DTO;

import fer.fpn.dao.Training;
import org.antlr.v4.runtime.misc.NotNull;

public class TrainingDTO {
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

    public TrainingDTO(Long idTraining, String title, String description, Long userId) {
        this.idTraining = idTraining;
        this.title = title;
        this.description = description;
        this.userId = userId;
    }

    public static TrainingDTO toDto(Training training) {
        return new TrainingDTO(training.getIdTraining(), training.getTitle(), training.getDescription(), training.getUser().getUserId());
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
