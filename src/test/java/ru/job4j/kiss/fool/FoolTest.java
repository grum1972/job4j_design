package ru.job4j.kiss.fool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FoolTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void whenAnswerMultiple3() {
        Integer startAt = 3;
        String answer = "Fizz";
        Fool fool = new Fool();
        assertThat(fool.getStartAt(startAt, answer, "Fizz")).isEqualTo(3);
    }

    @Test
    void whenAnswerMultiple5() {
        Integer startAt = 5;
        String answer = "Buzz";
        Fool fool = new Fool();
        assertThat(fool.getStartAt(startAt, answer, "Buzz")).isEqualTo(5);
    }

    @Test
    void whenAnswerMultiple3And5() {
        Integer startAt = 15;
        String answer = "FizzBuzz";
        Fool fool = new Fool();
        assertThat(fool.getStartAt(startAt, answer, "FizzBuzz")).isEqualTo(15);
    }

    @Test
    void whenOtherAnswer() {
        Integer startAt = 4;
        String answer = "4";
        Fool fool = new Fool();
        assertThat(fool.getStartAt(startAt, answer, startAt.toString())).isEqualTo(4);
    }

    @Test
    void whenWrongAnswer() {
        Integer startAt = 4;
        String answer = "Bizz";
        Fool fool = new Fool();
        assertThat(fool.getStartAt(startAt, answer, startAt.toString())).isEqualTo(0);
    }
}