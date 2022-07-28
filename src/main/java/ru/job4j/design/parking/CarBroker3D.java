package ru.job4j.design.parking;

public class CarBroker3D implements ICarBroker {
    private final CarsStore store = new CarsStore3d();

    @Override
    public void addParkingToList(IParking iparking) {

    }

    @Override
    public void parkingCar(ICar car) {

    }

    @Override
    public CarsStore getParkings() {
        return null;
    }

    @Override
    public int getFreePass() {
        return 0;
    }

    @Override
    public int getFreeCargo() {
        return 0;
    }
}
