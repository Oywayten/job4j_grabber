package ru.job4j.design.parking;

public interface IParking {

    /**
     * В методе логика учета места.
     * Сравнивает размер авто из метода ICar.parkIn() и количество свободных мест
     * в методе getFree();
     * @param car машина для парковки
     * @return возвращает успех парковки типа boolean
     */
    boolean carParking(ICar car);

    /**
     * Метод возвращает сколько мест свободно
     * @return число свободных мест на парковке
     */
    int getFree();

    /**
     * Метод сообщает, является ли паркинг грузовым
     * @return возвращает true, если паркинг грузовой
     */
    boolean isCargo();
}
