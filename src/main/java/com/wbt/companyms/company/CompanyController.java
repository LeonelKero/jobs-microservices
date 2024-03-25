package com.wbt.companyms.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = {"/companies"})
public record CompanyController(CompanyService companyService) {

    @GetMapping
    public ResponseEntity<List<Company>> companies() {
        return ResponseEntity.ok(this.companyService.findAll());
    }

    @PostMapping
    public ResponseEntity<Company> create(final @RequestBody CompanyRequest request) {
        return new ResponseEntity<>(this.companyService.create(request), HttpStatus.CREATED);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<?> company(final @PathVariable(name = "id") Long id) {
        Optional<Company> optionalCompany = this.companyService.findById(id);
        if (optionalCompany.isPresent()) return new ResponseEntity<>(optionalCompany.get(), HttpStatus.OK);
        return new ResponseEntity<>("Company resource with id %s not found".formatted(id), HttpStatus.NOT_FOUND);
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<String> update(final @PathVariable(name = "id") Long id, final @RequestBody CompanyRequest request) {
        if (this.companyService.update(id, request)) return ResponseEntity.ok("Company resource successfully updated!");
        return new ResponseEntity<>("Unable to update resource %s".formatted(id), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<String> delete(final @PathVariable(name = "id") Long id) {
        if (this.companyService.delete(id)) return ResponseEntity.ok("Company resource successfully deleted!");
        return ResponseEntity.badRequest().body("company resource with id %s not found, unable to delete".formatted(id));
    }
}
