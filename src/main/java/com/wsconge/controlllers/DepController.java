package com.wsconge.controlllers;

import com.wsconge.entities.Departement;
import com.wsconge.entities.Utilisateur;
import com.wsconge.services.DepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wsconge")
@CrossOrigin("http://localhost:4200")
public class DepController {
    @Autowired
    private DepService depService;
    @GetMapping("/getdep/{id}")
    public Departement getDepById(@PathVariable Long id){

        return depService.getDepartementById(id);
    }
    @GetMapping("/getAllDepartements")
    public List<Departement> getAllDepartements(){
        return depService.getAllDepartements();
    }
    @PostMapping("/adddep")
    public void addDep(@RequestBody Departement dep) {
        depService.addDepartement(dep);
    }
    @DeleteMapping("/deletedep/{id}")
    public void deleteDep(@PathVariable Long id){
        depService.deleteDepartement(id);
        System.out.println("departement deleted successfully!");
    }
    @PutMapping("/updatedep/{id}")
    public void updateDep(@PathVariable Long id, @RequestBody Departement dep) {
        depService.updateDepartement(id, dep);
        System.out.println("Departement "+id+" updated!");
    }
}
