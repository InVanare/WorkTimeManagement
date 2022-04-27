package pl.coderslab.wtm.dto.organization;

public class OrganizationUpdateDto {

    private Long id;
    private String name;
    private Boolean isActive;

    public OrganizationUpdateDto(Long id, String name, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    public OrganizationUpdateDto() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getActive() {
        return isActive;
    }
}
