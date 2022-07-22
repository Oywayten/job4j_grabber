package ru.job4j.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class CinemaTest {

    @Disabled
    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions).isEqualTo(Arrays.asList(new Session3D()));
    }

    @Disabled
    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Disabled
    @Test
    public void whenNotFind() {
        Cinema cinema = new Cinema3D();
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions).isEqualTo(new ArrayList<Session>());
    }

    @Disabled
    @Test
    public void whenWrongDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 42, 23, 00);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            cinema.buy(account, 1, 1, date);
        });
    }

    @Disabled
    @Test
    public void whenWrongPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            cinema.buy(account, 0, 0, date);
        });
    }

    @Disabled
    @Test
    public void whenSoldPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        cinema.buy(account, 1, 1, date);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            cinema.buy(account, 1, 1, date);
        });
    }
}