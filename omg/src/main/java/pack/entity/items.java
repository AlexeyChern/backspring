package pack.entity;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;

@Entity
public class items {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id")
    private int id;
    @JsonProperty("name")
    @Column(name = "_name")
    private String name;
    @JsonProperty("price")
    @Column(name = "_price")
    private Double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public items(){}

    private items(int id, String name, Double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

}
