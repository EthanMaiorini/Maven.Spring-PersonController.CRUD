import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {
    @Autowired
    PersonRepository personRepository;

    @PostMapping("/people")
    public ResponseEntity createPerson(@RequestBody Person p){
        return new ResponseEntity(personRepository.save(p), HttpStatus.CREATED);
    }

    @GetMapping("/people/{id}")
    public ResponseEntity getPerson(@PathVariable Long id){
        return new ResponseEntity(personRepository.findOne(id),HttpStatus.OK);
    }

    @GetMapping("/people")
    public ResponseEntity getPersonList(){
        List<Person> people = new ArrayList<>();
        personRepository.findAll().forEach(people::add);
       return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @PutMapping("/people")
    public ResponseEntity updatePerson(@RequestBody Person p){
        return new ResponseEntity(personRepository.save(p),HttpStatus.OK);
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity deletePerson(@PathVariable Long id){
       personRepository.delete(id);
       return new ResponseEntity(personRepository.findOne(id),HttpStatus.NO_CONTENT);
    }
}
