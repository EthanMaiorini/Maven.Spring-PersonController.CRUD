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
    public ResponseEntity<Person> createPerson(@RequestBody Person p){
        return new ResponseEntity<>(personRepository.save(p), HttpStatus.CREATED);
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") Long id){
        Person person = personRepository.findById(id).get();
        if (person == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("/people")
    public ResponseEntity<List<Person>> getPersonList(){
        List<Person> people = new ArrayList<>();
        personRepository.findAll().forEach(people::add);
        if (people == null || people.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
       return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") Long id, @RequestBody Person p){
        Person person = personRepository.findById(id).get();
        if (person == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        person.setFirst_name(p.getFirst_name());
        person.setLast_name(p.getLast_name());
        personRepository.save(person);
        return new ResponseEntity<>(person,HttpStatus.OK);
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable("id") Long id){
        Person person = personRepository.findById(id).get();

        if (person == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       personRepository.delete(person);
       return new ResponseEntity<>(HttpStatus.OK);
    }
}
