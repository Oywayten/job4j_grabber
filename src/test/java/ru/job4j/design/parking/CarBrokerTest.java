package ru.job4j.design.parking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CarBrokerTest {
    ICarBroker broker;

    @BeforeEach
    void setup() {
        broker = new CarBroker3D();
    }

    @Disabled
    @Test
    void whenPlaces2Pass2CargoAndCarIsNull() {
        IParking passParking = new Iparking3d(2, false);
        IParking cargoParking = new Iparking3d(2, true);
        assertThatNullPointerException().isThrownBy(() -> broker.parkingCar(null));
    }

    @Disabled
    @Test
    void whenPlaces5Pass3CargoAndCars2Pass4CargoOf2Places() {
        IParking passParking = new Iparking3d(5, false);
        IParking cargoParking = new Iparking3d(3, true);
        List<ICar> passCarsList = List.of(
                new PassCar3d(),
                new PassCar3d());
        List<ICar> cargoCarsList = List.of(
                new CargoCar3d(2),
                new CargoCar3d(2),
                new CargoCar3d(2),
                new CargoCar3d(2)
        );
        passCarsList.forEach(broker::parkingCar);
        cargoCarsList.forEach(broker::parkingCar);
        assertThat(broker.getFreePass()).isEqualTo(1);
        assertThat(broker.getFreeCargo()).isEqualTo(0);
    }

    @Disabled
    @Test
    void whenPlaces0Pass0CargoAndCars2Pass2Cargo() {
        List<ICar> passCarsList = List.of(
                new PassCar3d(),
                new PassCar3d());
        List<ICar> cargoCarsList = List.of(
                new CargoCar3d(2),
                new CargoCar3d(2)
        );
        assertThatIllegalArgumentException().isThrownBy(() -> passCarsList.forEach(broker::parkingCar));
        assertThatIllegalArgumentException().isThrownBy(() -> cargoCarsList.forEach(broker::parkingCar));
    }
}