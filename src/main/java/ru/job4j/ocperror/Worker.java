package ru.job4j.ocperror;

public class Worker extends Man {
    private Income income;

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.goToWork();
    }

    public Income getIncome() {
        return income;
    }

    public Income setIncome(Salary salary) {
        Income inc = new Income();
        inc.setInc(salary.getMoney());
        return inc;
    }
}
