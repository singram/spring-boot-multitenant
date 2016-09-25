package com.srai.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.srai.model.Person;
import com.srai.model.repository.PersonRepository;

import javax.validation.Valid;

/** Simple controller to illustrate templates. */
@RestController
@RequestMapping(value = "/person")
public class PersonController {

  /** Person repository. */
  @Autowired
  private transient PersonRepository repository;

  /**
   * Person retriever.
   * @return Person
   */
  @RequestMapping(value = "/{personId}", method = RequestMethod.GET)
  @ResponseBody public ResponseEntity<?> getPerson(@PathVariable final Long personId) {
    final Person person = repository.findOne(personId);
    if (person == null) {
      return ResponseEntity.notFound().build();
    }
    final Resource<Person> resource = new Resource<Person>(person);
    resource.add(linkTo(methodOn(PersonController.class).getPerson(personId)).withSelfRel());

    return ResponseEntity.ok(resource);
  }

  /**
   * Person creation.
   * @return Person
   */
  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ResponseBody public ResponseEntity<?> savePerson(@RequestBody final Person person) {
    final Person persistedPerson = repository.save(person);
    final Resource<Person> resource = new Resource<Person>(persistedPerson);
    resource.add(
        linkTo(methodOn(PersonController.class).getPerson(persistedPerson.getId())).withSelfRel()
    );
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .contentType(MediaType.APPLICATION_JSON)
        .body(resource);
  }

}
