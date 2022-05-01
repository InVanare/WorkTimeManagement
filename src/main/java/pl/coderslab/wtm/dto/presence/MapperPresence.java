package pl.coderslab.wtm.dto.presence;

import pl.coderslab.wtm.repository.entity.Organization;
import pl.coderslab.wtm.repository.entity.Presence;

public class MapperPresence {

    private Presence presence = new Presence();

    public PresenceDto toDto(Presence presence) {
        return new PresenceDto(presence.getId(),
                presence.getStart(),
                presence.getEnd(),
                presence.getUser());
    }

    public Presence toPresence(PresenceCreationDto presenceDto) {
        presence.setStart(presenceDto.getStart());
        presence.setEnd(presenceDto.getEnd());
        presence.setUser(presenceDto.getUser());
        presence.setOrganization(presenceDto.getOrganization());

        return presence;
    }

    public Presence toPresence(Presence presenceMap) {
        presence.setId(presenceMap.getId());
        presence.setStart(presenceMap.getStart());
        presence.setEnd(presenceMap.getEnd());
        presence.setUser(presenceMap.getUser());
        presence.setOrganization(presenceMap.getOrganization());
        return presence;
    }
}


