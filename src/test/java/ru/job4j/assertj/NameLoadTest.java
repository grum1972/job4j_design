package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();

        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void namesIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[]{};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void keyValueIsNotValidateSignEqual() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[]{"a:10"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(names[0])
                .hasMessageContaining("=");
    }

    @Test
    void keyValueIsNotValidateAsKeyIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[]{"=10"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(names[0])
                .hasMessageContaining("key");
    }

    @Test
    void keyValueIsNotValidateAsValueIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[]{"a="};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(names[0])
                .hasMessageContaining("value");
    }
}