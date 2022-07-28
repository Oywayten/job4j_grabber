package ru.job4j.design.parking;

/**
 * Интерфейс является брокером паркингов. Машина взаимодействует только с ним и
 * её не интересует, какого типа конкретный паркинг.
 * В классе реализующем интерфейс, создается поле-хранилище паркингов разных типов.
 */
public interface CarBroker {

    /**
     * Метод добавляет паркинги в хранилище брокера паркингов
     */
    void addParkingToList(Iparking iparking);

    /**
     * Метед паркует машины в один из паркингов хранилища, если есть
     * подходящий паркинг со свободным местом нужного размера.
     */
    void parkingCar(Icar icar);

    /**
     * Метод возвращает хранилище, чтобы в него можно было добавить машину.
     *
     * @return хранилище маркерного интерфейса
     */
    CarsStore getParkings();
}
