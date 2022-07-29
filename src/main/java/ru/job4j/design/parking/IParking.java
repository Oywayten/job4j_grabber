package ru.job4j.design.parking;

public interface IParking {

    /**
     * В методе логика учета места.
     * Сравнивает размер авто из метода ICar.parkIn() и количество свободных мест
     * в методе getFree();
     *
     * @param car машина для парковки
     * @return возвращает успех парковки типа boolean
     */
    boolean carParking(ICar car);
}
