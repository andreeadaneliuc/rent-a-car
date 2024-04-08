package org.example;

import org.example.clients.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTest {

    @Test
    @DisplayName("Create person")
    public void registerClient_whenInfoCorrect_thenReturnValidClient(){
        String name = "Maria";
        String cnp = "2901212443456";

        Client client = new Client(name, cnp);

        assertEquals("Maria", client.getName());
        assertEquals(cnp, client.getCnp());
    }
}
