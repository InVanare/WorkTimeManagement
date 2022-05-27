package pl.coderslab.wtm.dto.presence;

import pl.coderslab.wtm.repository.entity.Organization;
import pl.coderslab.wtm.repository.entity.Presence;

public class MapperPresence {

    public PresenceDto toDto(Presence presence) {
        return new PresenceDto(presence.getId(),
                presence.getStart(),
                presence.getEnd(),
                presence.getUser().getUsername(),
                presence.getOrganization().getName(),
                presence.getFinished());
    }

    public Presence toPresence(PresenceCreationDto presenceDto) {
        Presence presence = new Presence();
        presence.setStart(presenceDto.getStart());
        presence.setEnd(presenceDto.getEnd());
        presence.setUser(presenceDto.getUser());
        presence.setOrganization(presenceDto.getOrganization());

        return presence;
    }

    public Presence toPresence(Presence presenceMap) {
        Presence presence = new Presence();
        presence.setId(presenceMap.getId());
        presence.setStart(presenceMap.getStart());
        presence.setEnd(presenceMap.getEnd());
        presence.setUser(presenceMap.getUser());
        presence.setOrganization(presenceMap.getOrganization());
        return presence;
    }
}


