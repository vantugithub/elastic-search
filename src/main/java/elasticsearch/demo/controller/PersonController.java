package elasticsearch.demo.controller;

import elasticsearch.demo.document.Person;
import elasticsearch.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value = "/api/person")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void save(@RequestBody Person person){
        personService.save(person);
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable String id){
        return personService.findById(id);
    }
}
