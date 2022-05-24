package pl.coderslab.wtm.dto.organization;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class OrganizationUpdateDto {

    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Za-z])[A-Za-z\\d]{2,20}$")
    private String name;

    @NotNull
    private Boolean isActive;

    public OrganizationUpdateDto( String name, Boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public Boolean getActive() {
        return isActive;
    }
}
