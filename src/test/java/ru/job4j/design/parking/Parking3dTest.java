package ru.job4j.design.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class Parking3dTest {
    IParking parking;

    @Test
    void whenPlaces5Pass3CargoAndCars3Pass5CargoOf2Places() {
        parking = new Parking3d(5, 3);
        assertThat(parking.carParking(new PassCar3d())).isEqualTo(true);
        assertThat(parking.carParking(new PassCar3d())).isEqualTo(true);
        assertThat(parking.carParking(new PassCar3d())).isEqualTo(true);
        assertThat(parking.carParking(new CargoCar3d(2))).isEqualTo(true);
        assertThat(parking.carParking(new CargoCar3d(2))).isEqualTo(true);
        assertThat(parking.carParking(new CargoCar3d(2))).isEqualTo(true);
        assertThat(parking.carParking(new CargoCar3d(2))).isEqualTo(true);
        assertThat(parking.carParking(new CargoCar3d(2))).isEqualTo(false);
    }

    @Test
    void whenPlaces2PassAndCars3Pass() {
        parking = new Parking3d(2, 3);
        assertThat(parking.carParking(new PassCar3d())).isEqualTo(true);
        assertThat(parking.carParking(new PassCar3d())).isEqualTo(true);
        assertThat(parking.carParking(new PassCar3d())).isEqualTo(false);
    }

    @Test
    void whenPlaces2Pass3CargoAndCars5Cargo() {
        parking = new Parking3d(2, 3);
        assertThat(parking.carParking(new CargoCar3d(2))).isEqualTo(true);
        assertThat(parking.carParking(new CargoCar3d(2))).isEqualTo(true);
        assertThat(parking.carParking(new CargoCar3d(2))).isEqualTo(true);
        assertThat(parking.carParking(new CargoCar3d(2))).isEqualTo(true);
        assertThat(parking.carParking(new CargoCar3d(2))).isEqualTo(false);
    }

    @Test
    void whenPlaces0Pass0Cargo() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Parking3d(0, 0));
    }

    @Test
    void whenCargoSizeIs1() {
        assertThatIllegalArgumentException().isThrownBy(() -> new CargoCar3d(1));
    }

    @Test
    void whenPlaces2Pass2CargoAndCarIsNull() {
        parking = new Parking3d(2, 2);
        assertThatNullPointerException().isThrownBy(() -> parking.carParking(null));
    }
}