package il.cshaifasweng.customerCatalogEntities;

import il.cshaifasweng.LogInEntities.Customers.RegisteredCustomer;
import il.cshaifasweng.MoneyRelatedServices.Transactions;
import il.cshaifasweng.ParkingLotEntities.Car;
import il.cshaifasweng.ParkingLotEntities.EntryAndExitLog;
import il.cshaifasweng.ParkingLotEntities.ParkingLot;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order extends Transactions {
    final int MAX_REMINDER_SENT=3;
    final int REMIND=0;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="registeredCustomer_id")
    private RegisteredCustomer registeredCustomer;
    @OneToOne
    @JoinColumn(name="EntryAndExitLog_id")
    private EntryAndExitLog entryAndExitLog;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parkingLot_id")
    private ParkingLot parkingLotID;

//    @Convert(converter = LocalDateAttributeConverter.class)
    @Column(name="dateOfOrder")
    private LocalDateTime dateOfOrder;

    @Column(name="active")
    private boolean active;

    @Column(name="exitingTime")
    private LocalDateTime exiting;


    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private Car car;

    @Column(name="email")
    private String email;
    @Column(name="ReminderSent")
    private int reminderSent=REMIND;
    @Column(name="agreedToPayPenalty")
    private boolean agreedToPayPenalty=false;
    public Order(RegisteredCustomer registeredCustomer, ParkingLot parkingLotID, LocalDate date,
                 String entering, String exiting, String car, String email) {
        this.registeredCustomer = registeredCustomer;
        this.dateOfOrder= date.atTime(Integer.parseInt(entering),0);
        this.date=LocalDate.now();
        this.parkingLotID = parkingLotID;

        this.exiting =date.atTime(Integer.parseInt(exiting),0);
        this.car =new Car(car);
        this.email = email;
        this.active = true;
        this.car.setCustomer(this.registeredCustomer);
        this.car.setTransaction(this);


    }
    public Order(RegisteredCustomer registeredCustomer, ParkingLot parkingLotID, LocalDate date,
                 LocalTime DateOfOrder, String exiting, String car, String email) {
        this.registeredCustomer = registeredCustomer;
        this.dateOfOrder= date.atTime(DateOfOrder.getHour(),DateOfOrder.getMinute());
        this.date=LocalDate.now();
        this.parkingLotID = parkingLotID;
        this.exiting =date.atTime(Integer.parseInt(exiting),0);

        this.car =new Car(car);
        this.email = email;
        this.active = true;
        this.car.setCustomer(this.registeredCustomer);
        this.car.setTransaction(this);

    }
    public Order(RegisteredCustomer registeredCustomer, ParkingLot parkingLotID, LocalDate date,
                 String entering, String exiting, String car, String email, boolean localBuilder) {
        this.date=date;
        this.registeredCustomer = registeredCustomer;
        this.parkingLotID = parkingLotID;
        this.exiting =date.atTime(Integer.parseInt(exiting),0);

        this.car =new Car(car);

        this.email = email;
        this.active = true;
        if (!localBuilder)
            this.registeredCustomer.addOrder(this);
        this.car.setCustomer(this.registeredCustomer);
        this.car.setTransaction(this);


    }
    public Order(ParkingLot parkingLotID, LocalDate date,
                 String entering, String exiting, String car, String email) {
//        this.registeredCustomer = registeredCustomer;
        this.date=date;
        this.parkingLotID = parkingLotID;
        this.exiting =date.atTime(Integer.parseInt(exiting),0);

        this.car.setCustomer(this.registeredCustomer);
        this.car.setTransaction(this);
        this.email = email;
        this.active = true;
    }
    @Override
    public String toString(){
        return "order id: "+id+" at"+date;
    }

    public Order() {

    }
    public int getHoursOfResidency(){
        return exiting.getHour()-dateOfOrder.getHour();
        // TODO: 12/02/2023  remap the exiting time to the date of order
//        return Integer.parseInt(exiting)-Integer.parseInt(entering);
    }
    public EntryAndExitLog getEntryAndExitLog(String licensePlate){
        return entryAndExitLog;
    }
    public EntryAndExitLog getEntryAndExitLog(){
        return entryAndExitLog;
    }
    // TODO: 1/10/2023 toString Function 
}
