package pack.entity;
import java.util.Arrays;
import java.lang.StringBuilder.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;

@Entity
public class Orders {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id")
    private int id;
    @Column(name = "_carditems")
    private String carditems;
    @Column(name = "_price")
    private Double totalCost;
    @Column(name = "_amount")
    private int amount;
    @Column(name = "_creator")
    private String creator;
    @Column(name = "_status")
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreator(String creator){
        this.creator = creator;
    }
    public String getCreator(){
        return creator;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return status;
    }
    public Double gettotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getCarditems() {
        return carditems;
    }

    public void setCarditems(String carditems) {
        this.carditems = carditems;
    }




    public Orders() {
    }


}
