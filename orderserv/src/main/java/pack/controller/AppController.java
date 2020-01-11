package pack.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pack.entity.carditem;
import pack.entity.Orders;
import pack.repository.CIRepo;
import com.fasterxml.jackson.annotation.*;
import pack.repository.OrRepo;
import com.google.gson.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import java.util.Optional;


@RestController
public class AppController {
    @Autowired
    private CIRepo itRepo;
    @Autowired
    private OrRepo orRepo;


    @PostMapping(path = "/add") // Map ONLY POST Requests
    public ResponseEntity<carditem> addNewCardItem(@RequestBody carditem n) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        carditem l = new carditem();
        l.setItemid(n.getItemId());
        l.setQuantity(n.getQuantity());
        itRepo.save(n);
        return new ResponseEntity<carditem>(l, HttpStatus.OK);
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<carditem> getAllCardItems() {

        return itRepo.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<carditem> getCardItem(@PathVariable int id) {

        return itRepo.findById(id);
    }

    @DeleteMapping(value = "/del/{id}")
    public void delItem(@PathVariable int id) {
        itRepo.deleteById(id);
    }

    @PutMapping(path = "/put/{id}")
    public String modifyit(@PathVariable int id, @RequestBody carditem n) {

        carditem upd = itRepo.getOne(id);
        upd.setItemid(n.getItemId());

        upd.setQuantity(n.getQuantity());


        itRepo.save(upd);
        return "ok";


    }


    //////////////////////////////////////////////////

    @PostMapping(path = "/addorder")
    public ResponseEntity<Orders> addNewOrder(@RequestBody Orders n) {


        Orders l = new Orders();
        l.setCreator(n.getCreator());
        l.setCarditems("");
        l.setTotalCost(0.0);
        l.setStatus("Unpayed");
        orRepo.save(l);
        return new ResponseEntity<Orders>(l, HttpStatus.OK);
    }

    @GetMapping(path = "/allorders", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Orders> getAllOrders() {

        return orRepo.findAll();
    }

    @DeleteMapping(value = "/delorder/{id}")
    public void delOrder(@PathVariable int id) {
        orRepo.deleteById(id);
    }

    ///////////////hell///////////
    @PutMapping(path = "/addcrditem/{id}")
    public String addcrditem(@PathVariable int id, @RequestBody Orders n) throws IOException {
        Orders upd = orRepo.getOne(id);
        String arr = upd.getCarditems();

        arr = arr + ", " + n.getCarditems();
        upd.setCarditems(arr);

        int result = Integer.parseInt(n.getCarditems());
        carditem helps = itRepo.getOne(result);


        upd.setAmount(upd.getAmount() + helps.getQuantity());

        final URL obj = new URL("http://localhost:8080/" + helps.getItemId());

        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();


        Gson GSON = new GsonBuilder().setPrettyPrinting().create();

        Item its = GSON.fromJson(String.valueOf(response), Item.class);
        upd.setTotalCost(upd.gettotalCost() + its.getPrice() * helps.getQuantity());
        System.out.println(String.valueOf(response));


        orRepo.save(upd);
        return "Updated";


    }
    ////////////endofhell/////////////////

}

///////////////////////
class Item {
    private int id;
    private Double price;
    private String name;

    public int getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Item(int id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


}
