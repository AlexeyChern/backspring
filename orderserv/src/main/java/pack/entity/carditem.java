package pack.entity;

import javax.persistence.*;

@Entity

public class carditem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id")
    private int id;
    @Column(name = "_itemid")
    private int itemid;
    @Column(name = "_quantity")
    private int quantity;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public int getItemId() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public carditem() {
    }

}
