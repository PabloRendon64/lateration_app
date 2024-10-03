package com.lateration_app.example.domain.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class LevelOneServiceTest {

    @Test
    void given_distances_then_calculate_position() {
        double[][] positions = new double[][] { {-500d, -200d}, {100d, -100d}, {500d, 100d} };
        double[] distances = new double[] { 100d, 115.5d, 142.7d};
        double[] actual = LevelOneService.getLocation(positions, distances);
        assertThat(new int[] {(int) actual[0], (int) actual[1]})
                .isEqualTo(new int[] { 53, -33 });
    }

    @Test
    void given_messages_get_secret_message() {
        String[] message1 = {"este", "", "", "mensaje", ""};
        String[] message2 = {"", "es", "", "", "secreto"};
        String[] message3 = {"este", "", "un", "", ""};
        String[][] messages = new String[3][];
        messages[0] = message1;
        messages[1] = message2;
        messages[2] = message3;
        assertThat(LevelOneService.getMessage(messages)).isEqualTo("este es un mensaje secreto");
    }

}
