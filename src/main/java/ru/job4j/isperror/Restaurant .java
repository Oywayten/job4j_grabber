package ru.job4j.isperror;

import java.util.Calendar;

interface Restaurant {
    
    void makeOrder();
    
    void callWaiter();
    
    boolean cashlessPay(double d);
    
    boolean tableBook(String fio, Calendar c);
}
