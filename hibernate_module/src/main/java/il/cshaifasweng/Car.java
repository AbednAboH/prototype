package il.cshaifasweng;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription; // Redundant?
    public Car(){

    }
    public Car(int id, Customer customer, Subscription subscription) {
        this.id = id;
        this.customer = customer;
        this.subscription = subscription;
    }
}
