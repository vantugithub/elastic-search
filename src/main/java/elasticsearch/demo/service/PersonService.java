package elasticsearch.demo.service;

import elasticsearch.demo.document.Person;
import elasticsearch.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public Person findById(String id){
        return personRepository.findById(id).orElse(null);
    }

}
