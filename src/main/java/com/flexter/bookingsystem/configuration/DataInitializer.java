package com.flexter.bookingsystem.configuration;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.flexter.bookingsystem.model.Availability;
import com.flexter.bookingsystem.model.Owner;
import com.flexter.bookingsystem.model.Vehicle;
import com.flexter.bookingsystem.repository.OwnerRepository;
import com.flexter.bookingsystem.repository.VehicleRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Component
@AllArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final VehicleRepository vehicleRepository;
    private final OwnerRepository ownerRepository;

    private List<Availability> getAvailabilities(Vehicle vehicle, int fromTime, int toTime, List<DayOfWeek> dayOfWeeks) {

        return dayOfWeeks.stream().map(d -> Availability.builder()
                .dayOfWeek(d)
                .fromTime(LocalTime.of(fromTime, 0))
                .toTime(LocalTime.of(toTime, 0))
                .vehicle(vehicle)
                .build()).toList();
    }

    @Override
    public void run(String... args) {

        System.out.println("Initiliazing database ...");
        if (vehicleRepository.count() == 0) {
            log.info("Initiliazing database ...");
            Owner ownerVinDiesel = Owner.builder().name("Vin Diesel").email("vindiesel@flexter.com").build();
            Owner ownerPaulWalker = Owner.builder().name("Paul Walker").email("paulwalker@flexter.com").build();
            Owner ownerTyrese = Owner.builder().name("Tyrese").email("tyrese@flexter.com").build();

            Vehicle mitsubishi2002 = Vehicle.builder().year("2002").make("Mitsubishi").model("Lancer Evolution VII").city("Montreal").rate(300.0).owner(ownerPaulWalker).build();
            Vehicle mitsubishi2003 = Vehicle.builder().year("2003").make("Mitsubishi").model("Eclipse Spyder GTS").city("Montreal").rate(200.0).owner(ownerTyrese).build();
            Vehicle toyota = Vehicle.builder().year("1994").make("Toyota").model("Supra MK IV").city("Los Angeles").rate(500.0).owner(ownerVinDiesel).build();
            Vehicle dodge = Vehicle.builder().year("1970").make("Dodge").model("Charger R/T").city("Montreal").rate(200.0).owner(ownerVinDiesel).build();
            Vehicle nissan = Vehicle.builder().year("2000").make("Nissan").model("Skyline GT-R").city("Los Angeles").rate(450.0).owner(ownerPaulWalker).build();

            List<Availability> availDodge= getAvailabilities(dodge, 8, 17, List.of(
                DayOfWeek.MONDAY, 
                DayOfWeek.TUESDAY, 
                DayOfWeek.WEDNESDAY, 
                DayOfWeek.THURSDAY, 
                DayOfWeek.FRIDAY, 
                DayOfWeek.SATURDAY
                ));
            dodge.setAvailabilities(availDodge);
            List<Availability> availToyota = getAvailabilities(toyota, 8, 12, List.of(
                    DayOfWeek.MONDAY, 
                    DayOfWeek.TUESDAY, 
                    DayOfWeek.WEDNESDAY, 
                    DayOfWeek.THURSDAY, 
                    DayOfWeek.FRIDAY, 
                    DayOfWeek.SATURDAY
                    ));
            toyota.setAvailabilities(availToyota);
            List<Availability> availMitsu2002 = getAvailabilities(mitsubishi2002, 9, 19, List.of(
                    DayOfWeek.SATURDAY, 
                    DayOfWeek.TUESDAY, 
                    DayOfWeek.WEDNESDAY, 
                    DayOfWeek.THURSDAY, 
                    DayOfWeek.FRIDAY, 
                    DayOfWeek.SUNDAY
                    ));
            mitsubishi2002.setAvailabilities(availMitsu2002);
            List<Availability> availNissan = getAvailabilities(nissan, 9, 19, List.of(
                    DayOfWeek.SATURDAY, 
                    DayOfWeek.TUESDAY, 
                    DayOfWeek.WEDNESDAY, 
                    DayOfWeek.THURSDAY, 
                    DayOfWeek.FRIDAY, 
                    DayOfWeek.SUNDAY
                    ));
            nissan.setAvailabilities(availNissan);
            List<Availability> availMitsu2003 = getAvailabilities(mitsubishi2003, 10, 18, List.of(
                    DayOfWeek.MONDAY, 
                    DayOfWeek.TUESDAY, 
                    DayOfWeek.WEDNESDAY, 
                    DayOfWeek.THURSDAY, 
                    DayOfWeek.SUNDAY, 
                    DayOfWeek.SATURDAY
                    ));
            mitsubishi2003.setAvailabilities(availMitsu2003);

            ownerVinDiesel.setVehicles(List.of(toyota, dodge));
            ownerPaulWalker.setVehicles(List.of(mitsubishi2002, nissan));
            ownerTyrese.setVehicles(List.of(mitsubishi2003));
            // vehicleRepository.saveAll(List.of());
            ownerRepository.save(ownerVinDiesel);
            ownerRepository.save(ownerPaulWalker);
            ownerRepository.save(ownerTyrese);
            log.info("Database initialized.");
        } else {
            log.info("Database already initialized.");
        }
    }
}
