package com.wsconge.controlllers;

import com.wsconge.entities.Presence;
import com.wsconge.services.IPresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wsconge/presence")
public class PresenceController {
    @Autowired
    private IPresenceService presenceService;
    @GetMapping("/getpresence/{id}")
    public Presence getPresenceById(@PathVariable Long id){
        return presenceService.getPresenceById(id);
    }
    @PostMapping("/addpresence")
    public void addPresence(@RequestBody Presence p){
        presenceService.addPresence(p);
    }
    @DeleteMapping("/deletepresence/{id}")
    public void deletePresence(@PathVariable Long id){
        presenceService.deletePresence(id);
        System.out.println("Presence deleted !");
    }
    @PutMapping ("/updatepresence/{id}")
    public void updatePresence(@PathVariable Long id, @RequestBody Presence updatedP) {
        presenceService.updatePresence(id, updatedP);
        System.out.println("Presence "+id+" updated!");
    }
}
