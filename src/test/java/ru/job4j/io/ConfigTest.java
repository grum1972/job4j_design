package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("PostgreSQL")).isNull();
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres");
    }

    @Test
    void whenIllegalArg() {
        String path = "./data/invalid.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithEmptyRow() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.size()).isEqualTo(5);
    }

    @Test
    void whenPairWithContainsTwoEqual() {
        String path = "./data/app1.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("ivan=1");
    }

}