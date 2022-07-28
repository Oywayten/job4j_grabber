package ru.job4j.design.parking;

public interface Iparking {

    /**
     * В методе логика учета места.
     * Сравнивает размер авто из метода Icar.parkIn() и количество свободных мест
     * в методе getFree();
     * @param icar машина для парковки
     * @return возвращает успех парковки типа boolean
     */
    boolean carParking(Icar icar);

    /**
     * Метод возвращает сколько мест свободно
     * @return число свободных мест на парковке
     */
    int getFree();
}
