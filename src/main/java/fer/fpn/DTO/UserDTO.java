package fer.fpn.DTO;

import java.util.Objects;

import fer.fpn.dao.UserFPN;

public class UserDTO {

    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(userId, userDTO.userId) &&
                Objects.equals(role, userDTO.role) &&
                Objects.equals(name, userDTO.name) &&
                Objects.equals(surname, userDTO.surname) &&
                Objects.equals(email, userDTO.email) &&
                Objects.equals(password, userDTO.password) &&
                Objects.equals(dailyCalGoal, userDTO.dailyCalGoal) &&
                Objects.equals(idTrainer, userDTO.idTrainer) &&
                Objects.equals(trainerName, userDTO.trainerName);
    }
    public UserDTO(Long userId, String role, String name, String surname, String email, String password, Float dailyCalGoal, String trainerName, Long idTrainer) {
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.dailyCalGoal = dailyCalGoal;
        this.trainerName = trainerName;
        this.idTrainer = idTrainer;
        this.userId = userId;
    }

    public UserDTO(){}

    public static UserDTO toDto(UserFPN user){
        return new UserDTO(
                user.getUserId(),
                user.getRole(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPassword(),
                user.getDailyCalGoal(),
                user.getTrainer()==null ? null : user.getTrainer().getName(),
                user.getTrainer()==null ? null : user.getTrainer().getUserId()
        );
    }
}
