package com.wsconge.controlllers;
import com.wsconge.entities.PolitiqueConge;
import com.wsconge.repositories.IPolitiqueCongeRepository;
import com.wsconge.services.PolitiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wsconge")
@CrossOrigin("http://localhost:4200")

public class PolitiqueController {
    @Autowired
    private PolitiqueService politiqueCongeService;

    @GetMapping("/allPolitiques")
    public List<PolitiqueConge> getAllPolitiques() {

        return politiqueCongeService.getallPolitiques();
    }

    @GetMapping("/getPolitique/{id}")
    public PolitiqueConge getPolitiqueById(@PathVariable Long id) {
        return politiqueCongeService.getPolitiqueById(id);
    }

    @PostMapping("/addPolitique")
    public void addPolitique(@RequestBody PolitiqueConge politiqueConge) {
        politiqueCongeService.addPolitique(politiqueConge);
    }

    @PutMapping("/updatePolitique/{id}")
    public void updatePolitique(@PathVariable Long id, @RequestBody PolitiqueConge updatedPolitique) {
        politiqueCongeService.updatePolitique(id, updatedPolitique);
    }

    @DeleteMapping("/deletePolitique/{id}")
    public void deletePolitique(@PathVariable Long id) {
        politiqueCongeService.deletePolitique(id);
    }
}
