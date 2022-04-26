package pl.coderslab.wtm.dto.user;


public class UserUpdateDto {

    private Long id;
    private String pass;
    private String mail;
    private Boolean isActive;

    public UserUpdateDto(Long id, String pass, String mail, Boolean isActive) {
        this.id = id;
        this.pass = pass;
        this.mail = mail;
        this.isActive = isActive;
    }

    public UserUpdateDto() {
    }

    public Long getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    public String getMail() {
        return mail;
    }

    public Boolean getIsActive() {
        return isActive;
    }
}
