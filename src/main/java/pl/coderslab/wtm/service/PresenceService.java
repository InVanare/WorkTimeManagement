package pl.coderslab.wtm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import pl.coderslab.wtm.dto.Mapper;
import pl.coderslab.wtm.dto.presence.PresenceCreationDto;
import pl.coderslab.wtm.dto.presence.PresenceDto;
import pl.coderslab.wtm.dto.presence.PresenceUpdateDto;
import pl.coderslab.wtm.repository.PresenceRepository;
import pl.coderslab.wtm.repository.UserRepository;
import pl.coderslab.wtm.repository.entity.Presence;
import pl.coderslab.wtm.repository.entity.User;
import pl.coderslab.wtm.utility.SecurityContext;
import pl.coderslab.wtm.utility.Validation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PresenceService {
    private final PresenceRepository presenceRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;
    private final Validation validation;
    private final SecurityContext securityContext;

    @Autowired
    public PresenceService(PresenceRepository presenceRepository, UserRepository userRepository, Mapper mapper, Validation validation, SecurityContext securityContext) {
        this.presenceRepository = presenceRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.validation = validation;
        this.securityContext = securityContext;
    }

    public Optional<PresenceDto> findById(Long id) {
        for (GrantedAuthority single : securityContext.getAuthorities()) {
            if (single.getAuthority().equals("ROLE_ADMIN")) {
                return presenceRepository.findById(id).map(mapper::toDto);
            }
        }
        return Optional.empty();
    }

    public List<PresenceDto> findByUser(String name) {
        Optional<User> user = userRepository.findByUsername(name);
        Optional<User> owner = userRepository.findByUsername(securityContext.getName());
        if (user.isEmpty() || owner.isEmpty()) {
            return List.of();
        }
        User userMapped = user.orElseThrow(RuntimeException::new);
        User ownerMapped = owner.orElseThrow(RuntimeException::new);
        if (userMapped.getOrganization().getId() == ownerMapped.getOrganization().getId()) {
            return presenceRepository.findByUser(userMapped)
                    .stream().map(p -> mapper.toDto(p.orElseThrow(RuntimeException::new)))
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    public Optional<PresenceDto> startPresence() {
        Optional<User> user = userRepository.findByUsername(securityContext.getName());
        if (user.isEmpty()) {
            return Optional.empty();
        }
        User userMapped = user.orElseThrow(RuntimeException::new);
        Optional<Presence> presenceFinished = presenceRepository.findByUserAndFinished(userMapped, false);
        if (presenceFinished.isPresent()) {
            return Optional.empty();
        }
        Presence presence = new Presence();
        LocalDateTime timeNow = LocalDateTime.now();
        presence.setStart(timeNow);
        presence.setEnd(timeNow);
        presence.setUser(userMapped);
        presence.setOrganization(userMapped.getOrganization());
        presence.setFinished(false);
        save(presence);
        return Optional.ofNullable(mapper.toDto(presence));
    }

    public Optional<PresenceDto> endPresence() {
        Optional<User> user = userRepository.findByUsername(securityContext.getName());
        if (user.isEmpty()) {
            return Optional.empty();
        }
        User userMapped = user.orElseThrow(RuntimeException::new);
        Optional<Presence> presence = presenceRepository.findByUserAndFinished(userMapped, false);
        if (presence.isEmpty()) {
            return Optional.empty();
        }
        Presence presenceMapped = presence.orElseThrow(RuntimeException::new);
        presenceMapped.setEnd(LocalDateTime.now());
        presenceMapped.setFinished(true);
        save(presenceMapped);
        return Optional.ofNullable(mapper.toDto(presenceMapped));
    }

    public Presence save(Presence presence) {
        return presenceRepository.save(presence);
    }
}
