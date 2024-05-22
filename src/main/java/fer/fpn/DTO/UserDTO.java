package fer.fpn.DTO;

import fer.fpn.dao.User;
import org.antlr.v4.runtime.misc.NotNull;

public class UserDTO {

    private String role;

    private String name;

    private String surname;

    private String email;

    private String password;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getDailyCalGoal() {
        return dailyCalGoal;
    }

    public void setDailyCalGoal(Float dailyCalGoal) {
        this.dailyCalGoal = dailyCalGoal;
    }

    public Long getIdTrainer() {
        return idTrainer;
    }

    public void setIdTrainer(Long idTrainer) {
        this.idTrainer = idTrainer;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    private Float dailyCalGoal;

    private Long idTrainer;
    private String trainerName;

    public UserDTO(String role, String name, String surname, String email, String password, Float dailyCalGoal, String trainerName, Long idTrainer) {
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.dailyCalGoal = dailyCalGoal;
        this.trainerName = trainerName;
        this.idTrainer = idTrainer;
    }

    public static UserDTO toDto(User user){
        return new UserDTO(user.getRole(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getDailyCalGoal(), user.getTrainer().getName(), user.getTrainer().getUserId());
    }
}
