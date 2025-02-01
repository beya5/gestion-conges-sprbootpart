package com.wsconge.services;

import com.wsconge.entities.Presence;

import java.util.List;
import java.util.Optional;

public interface IPresenceService {

    Presence addPresence(Presence presence);
    Presence getPresenceById(Long id);
    List<Presence> getAllPresences();
    void deletePresence(Long id);
    Presence updatePresence(Long id,Presence presence);
}
