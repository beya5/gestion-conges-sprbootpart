package com.wsconge.controlllers;

import com.wsconge.entities.UtilisDep;
import com.wsconge.services.IUtilisDepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/wsconge/uniondepuser")
@CrossOrigin(origins = "http://localhost:4200")
public class UnionController {
    @Autowired
    private final IUtilisDepService iUtilisDepService;

    public UnionController(IUtilisDepService iUtilisDepService) {
        this.iUtilisDepService = iUtilisDepService;
    }

//    @GetMapping("/getunion/{iddep}/{idu}")
//    public UtilisDep getUnion(@PathVariable Long iddep,@PathVariable Long idu){
//        return iUtilisDepService.getUnion(iddep, idu);
//    }
    @GetMapping("/departments/{userId}")
    public List<Map<String, Object>> getDepartmentsForUser(@PathVariable Long userId) {
        return iUtilisDepService.getDepartmentsWithUserAssignment(userId);
    }
    @PostMapping("/addunion")
    public void addUnion(@RequestBody UtilisDep u) {

        iUtilisDepService.addUnion(
                u.getId().getId_departement(),
                u.getId().getId_utilisateur(),
                u.getDateEntree()
        );
        System.out.println("union added successfully!");
    }
//    @DeleteMapping("/deleteunion/{iddep}/{idu}")
//    public void deleteUnion(@PathVariable Long iddep,@PathVariable Long idu){
//        iUtilisDepService.deleteUnion(iddep, idu);
//        System.out.println("union deleted successfully!");
//    }
//    @PutMapping("/updateunion/{iddep}/{idu}")
//    public void updateUnion(@PathVariable Long iddep, @PathVariable Long idu, @RequestBody UtilisDep u) {
//        iUtilisDepService.updateUnion(iddep, idu,u.getDateEntree());
//        System.out.println("Union updated!");
//    }
    @PutMapping("/update-departments/{userId}")
    public void updateDepartments(@PathVariable Long userId, @RequestBody List<Long> departmentIds) {
            iUtilisDepService.updateUserDepartments(userId, departmentIds);
    }
}
