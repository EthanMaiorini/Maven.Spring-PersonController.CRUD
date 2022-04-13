import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {
    @Autowired
    PersonRepository personRepository;

    @PostMapping("/people")
    public Person createPerson(@RequestBody Person p){
        return personRepository.save(p);
    }

    @GetMapping("/people/{id}")
    public Person getPerson(@PathVariable Long id){
        return personRepository.findOne(id);
    }

    @GetMapping("/people")
    public List<Person> getPersonList(){
        List<Person> people = new ArrayList<>();
        personRepository.findAll().forEach(people::add);
        return people;
    }

    @PutMapping("/people")
    public Person updatePerson(@RequestBody Person p){
        return personRepository.save(p);
    }

    @DeleteMapping("/people/{id}")
    public void deletePerson(@PathVariable Long id){
        personRepository.delete(id);
    }
}
