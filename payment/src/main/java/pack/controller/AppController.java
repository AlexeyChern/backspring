package pack.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pack.entity.payments;
import pack.repository.ItRepo;
import com.fasterxml.jackson.annotation.*;

import java.util.Optional;


@RestController
public class AppController {
    @Autowired
    private ItRepo itRepo;

    @PostMapping(path = "/add") // Map ONLY POST Requests
    public
    ResponseEntity<payments> addNewPay(@RequestBody payments n) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        payments l = new payments();
        l.setStatus(n.getStatus());
        l.setOrderID(n.getOrderID());
        itRepo.save(n);
        return new ResponseEntity<payments>(l, HttpStatus.OK) ;
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public
    Iterable<payments> getAllPays() {

        return itRepo.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public
    Optional<payments> getAll(@PathVariable int id) {

        return itRepo.findById(id);
    }

    @DeleteMapping(value = "/del/{id}")
    public void delpay(@PathVariable int id) {
        itRepo.deleteById(id);
    }

    @PutMapping(path = "/put/{id}")
    public String modifyit(@PathVariable int id, @RequestBody payments n) {

        payments upd = itRepo.getOne(id);
        if (n.getStatus() != null) {
            upd.setStatus(n.getStatus());
        }
        upd.setOrderID(n.getOrderID());


        itRepo.save(upd);
        return "ok";


    }


}
