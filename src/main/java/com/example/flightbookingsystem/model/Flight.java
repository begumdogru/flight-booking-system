package com.example.flightbookingsystem.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flight")
public class Flight{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer flightId;
    private String route;
    private Integer departTime;
    private Integer arrivalTime;
    private Integer reservationCode;
    @ManyToMany(mappedBy = "flights", cascade = CascadeType.ALL) // Consider using cascade
    @JsonBackReference
    private List<Passenger> passengers;
    @OneToMany(mappedBy = "flight", fetch = FetchType.EAGER)// Consider using cascade
    @JsonIgnore
    private List<Seat> seats;

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Integer getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Integer departTime) {
        this.departTime = departTime;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Integer arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(Integer reservationCode) {
        this.reservationCode = reservationCode;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}