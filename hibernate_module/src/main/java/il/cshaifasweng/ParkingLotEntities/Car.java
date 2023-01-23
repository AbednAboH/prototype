package il.cshaifasweng.ParkingLotEntities;

import il.cshaifasweng.LogInEntities.Customers.Customer;
import il.cshaifasweng.customerCatalogEntities.Subscription;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="carNumber")
    private String carNum;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription; // Redundant?
    public Car(){
    }
    public Car(String CarID) {
        this.carNum=CarID;
    }
    @Override
    public String toString(){
        return carNum;
    }

}
