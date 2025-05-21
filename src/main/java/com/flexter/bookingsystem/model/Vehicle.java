package com.flexter.bookingsystem.model;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
    indexes = {
        @Index(name = "idx_vehicles_city", columnList = "city")
    }
)
public class Vehicle {
    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Double rate;

    @Column(nullable = false)
    private String city;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Availability> availabilities;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

}
