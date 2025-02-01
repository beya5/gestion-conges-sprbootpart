package com.wsconge.controlllers;
import com.wsconge.DTO.DemandesCongeDTO;
import com.wsconge.entities.DemandesConge;
import com.wsconge.services.DemCongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/wsconge")
@CrossOrigin("http://localhost:4200")
public class DemCongeController {
         @Autowired
        private DemCongeService demCongeService;

        @PostMapping("/addrequest")
        public ResponseEntity<Map<String, String>> requestLeave(@RequestBody DemandesCongeDTO demande) {
            Map<String, String> response = new HashMap<>();
            try {
                if (demande.getIdutilisateur() == null){
                    response.put("error", "User ID is missing in request body.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }
                if (demande.getDatedebut() == null || demande.getDatefin() == null) {
                    response.put("error", "Start and end dates are required.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }
                demCongeService.requestLeave(demande);
                response.put("message", "Leave request successfully submitted");
                return ResponseEntity.ok(response);
            }catch (Exception e) {
                response.put("error", "Error processing request.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }
    @GetMapping("/debug-cookies")
    public ResponseEntity<String> debugCookies(@CookieValue(name = "userId", required = false) Long userId) {
        return ResponseEntity.ok("userId from cookie: " + userId);
    }

        @GetMapping("/trackrequest/{id}")
        public String trackRequest(@PathVariable Long id) {
            return demCongeService.trackRequest(id);
        }

        @PutMapping("/acceptrequest/{id}")
        public void acceptRequest(@PathVariable Long id) {
            System.out.println("🔄 Acceptation de la demande avec ID: " + id);
            demCongeService.acceptRequest(id);
            System.out.println("✅ Demande acceptée !");
        }

        @PutMapping("/declinerequest/{id}")
        public void declineRequest(@PathVariable Long id) {
            demCongeService.declineRequest(id);
        }

        @GetMapping("/allrequests")
        public List<DemandesCongeDTO> getAllDemandes() {
            return demCongeService.getAllDemandes();
        }

        @GetMapping("/request/{id}")
        public DemandesConge getDemandeById(@PathVariable Long id) {
            return demCongeService.getDemandeById(id);
        }

        @DeleteMapping("/deleterequest/{id}")
        public void deleteDemande(@PathVariable Long id) {
            demCongeService.deleteDemande(id);
        }
    @GetMapping("/userrequests")
    public List<DemandesCongeDTO> getUserRequests(@RequestParam Long id) {
        return demCongeService.getUserRequests(id);
    }

//        @PutMapping("/updaterequest/{id}")
//        public void updateDemande(@PathVariable Long id, @RequestBody DemandesConge updatedDemande) {
//            demCongeService.updateDemande(id, updatedDemande);
//        }
}
