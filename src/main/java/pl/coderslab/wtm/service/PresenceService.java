package pl.coderslab.wtm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.wtm.dto.Mapper;
import pl.coderslab.wtm.dto.presence.PresenceDto;
import pl.coderslab.wtm.dto.presence.PresenceUpdateDto;
import pl.coderslab.wtm.repository.PresenceRepository;
import pl.coderslab.wtm.repository.entity.Presence;

import java.util.Optional;

@Service
public class PresenceService {
    private final PresenceRepository presenceRepository;
    private final Mapper mapper;

    @Autowired
    public PresenceService(PresenceRepository presenceRepository, Mapper mapper) {
        this.presenceRepository = presenceRepository;
        this.mapper = mapper;
    }

    public Optional<PresenceDto> findById(Long id) {
        return presenceRepository.findById(id).map(this::toDto);
    }

    public Presence save(Presence presence) {
        return presenceRepository.save(presence);
    }

    public Optional<PresenceDto> update(PresenceUpdateDto presenceUpdate) {
        Optional<Presence> presence = presenceRepository
                .findById(presenceUpdate.getId())
                .map(u -> {
                    u.setStart(presenceUpdate.getStart());
                    u.setEnd(presenceUpdate.getEnd());
                    return u;
                });

        if (presence.isEmpty()) {
            return Optional.empty();
        }
        Presence presenceMapped = presence.map(this::toPresence).get();
        presenceRepository.save(presenceMapped);
        return Optional.ofNullable(toDto(presenceMapped));
    }

    private PresenceDto toDto(Presence presence) {
        return mapper.toDto(presence);
    }

    private Presence toPresence(Presence presence) {
        return mapper.toPresence(presence);
    }

}
