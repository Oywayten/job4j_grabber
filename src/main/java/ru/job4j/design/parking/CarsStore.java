package ru.job4j.design.parking;

import java.util.List;

/**
 * Маркерный интерфейс для хранилища паркингов машин
 */
public interface CarsStore {
    List<IParking> getStorage();
}
