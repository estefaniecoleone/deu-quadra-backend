package ifsp.edu.br.deuquadra.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifsp.edu.br.deuquadra.models.BoundingBox;
import ifsp.edu.br.deuquadra.models.CourtsModel;
import ifsp.edu.br.deuquadra.services.CourtsService;

@ComponentScan("ifsp.edu.br.deuquadra.*")
@RestController
@RequestMapping("/courts")
@CrossOrigin
public class CourtsController {

    @Autowired
    CourtsService courtsService;

    @GetMapping
    public ResponseEntity<List<CourtsModel>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(courtsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CourtsModel>> findById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(courtsService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CourtsModel> create(@RequestBody CourtsModel courtsModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courtsService.save(courtsModel));
    }

    @PostMapping("search")
    public ResponseEntity<List<CourtsModel>> FindByProximity(@PathVariable Double lon, @PathVariable Double lat,
    		@PathVariable Integer km){
    	BoundingBox bb = new BoundingBox(lat, lon, km);
    
    	return ResponseEntity.status(HttpStatus.OK).body(courtsService.findByProximity(bb.getMinLatitude(), bb.getMaxLatitude(), bb.getMinLongitude(), bb.getMaxLongitude()));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CourtsModel> update(@RequestBody CourtsModel courtsModel) {
        return ResponseEntity.status(HttpStatus.OK).body(courtsService.update(courtsModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        courtsService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}