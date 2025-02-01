package com.wsconge.controlllers;

import com.wsconge.entities.SoldeConge;
import com.wsconge.services.SoldeCongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wsconge/soldeconge")
@CrossOrigin(origins = "http://localhost:4200")

public class SoldeCongeController {
    @Autowired
    private SoldeCongeService soldeCongeService;

    @PostMapping("/create")
    public void addSoldeConge(@RequestBody SoldeConge soldeConge) {
        soldeCongeService.addSoldeConge(soldeConge);

    }
    @GetMapping("/{id}")
    public SoldeConge getSoldeCongeById(@PathVariable Long id) {
        return soldeCongeService.getSoldeCongeById(id);

    }

    // Get Leave Balance by User ID
    @GetMapping("/balance/{userId}")
    public int getLeaveBalance(@PathVariable Long userId) {

        return soldeCongeService.trackLeaveBalance(userId);
    }

    // Delete a SoldeConge by ID
    @DeleteMapping("/{id}")
    public void deleteSoldeConge(@PathVariable Long id) {
        soldeCongeService.deleteSoldeConge(id);
    }
}
