package pack.entity;
import javax.persistence.*;

///itemid =20 ,
@Entity
public class payments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id")
    private int id;
    @Column(name = "_status")
    private String status;
    @Column(name = "_orderid")
    private int OrderID;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public int getOrderID(){
        return OrderID;
    }
    public void setOrderID(int OrderID){
        this.OrderID = OrderID;
    }
    public payments(){}
}
