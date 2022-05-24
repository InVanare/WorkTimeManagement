package pl.coderslab.wtm.dto.team;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class TeamUpdateDto {

    @NotEmpty
    private String nameTeam;
    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Za-z])[A-Za-z\\d]{3,20}$")
    private String nameToUpdate;
    @NotNull
    private Boolean isActive;

    public TeamUpdateDto(String nameTeam, String nameToUpdate, Boolean isActive) {
        this.nameTeam = nameTeam;
        this.nameToUpdate = nameToUpdate;
        this.isActive = isActive;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public String getNameToUpdate() {
        return nameToUpdate;
    }

    public Boolean getActive() {
        return isActive;
    }
}
