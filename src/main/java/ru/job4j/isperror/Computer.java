package ru.job4j.isperror;

public interface Computer {
    
    boolean run(Program p);
    
    boolean stop(Program p);
    
    void reboot();
    
    void shutdown();
    
    void show(byte[] bytes);
    
    void speak(byte[] bytes);
}
