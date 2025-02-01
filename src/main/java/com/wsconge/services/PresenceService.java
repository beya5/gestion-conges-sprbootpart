package com.wsconge.services;

import com.wsconge.entities.Presence;
import com.wsconge.repositories.IPresenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresenceService implements IPresenceService {

    @Autowired
    private IPresenceRepository presenceRepository;

    @Override
    public Presence addPresence(Presence presence) {
        return presenceRepository.save(presence);
    }

    @Override
    public Presence getPresenceById(Long id) {
        return presenceRepository.findById(id).get();
    }

    @Override
    public List<Presence> getAllPresences() {
        return presenceRepository.findAll();
    }

    @Override
    public void deletePresence(Long id) {
        presenceRepository.deleteById(id);
    }

    @Override
    public Presence updatePresence(Long id,Presence presence) {
        Presence p=presenceRepository.findById(id).get();

        return presenceRepository.save(presence);
    }

}
