package pack.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pack.entity.items;
import pack.repository.ItRepo;
import com.fasterxml.jackson.annotation.*;

import java.util.Optional;


@RestController
public class AppController {
    @Autowired
    private ItRepo itRepo;

    @PostMapping(path = "/add") // Map ONLY POST Requests
    public
    ResponseEntity<items> addNewItem(@RequestBody items n) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        items l = new items();
        l.setName(n.getName());
        l.setPrice(n.getPrice());
        itRepo.save(n);
        return new ResponseEntity<items>(l, HttpStatus.OK) ;
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public
    Iterable<items> getAllItems() {
        // This returns a JSON or XML with the items
        return itRepo.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public
    Optional<items> getAllItems(@PathVariable int id) {
        // This returns a JSON or XML with the items
        return itRepo.findById(id);
    }

    @DeleteMapping(value = "/del/{id}")
    public void delItem(@PathVariable int id) {
        itRepo.deleteById(id);
    }

    @PutMapping(path = "/put/{id}")
    public String modifyit(@PathVariable int id, @RequestBody items n) {

        items upd = itRepo.getOne(id);
        if (n.getName() != null) {
            upd.setName(n.getName());
        }
        if (n.getPrice() != null) {
            upd.setPrice(n.getPrice());
        }

        itRepo.save(upd);
        return "ok";


    }


}
