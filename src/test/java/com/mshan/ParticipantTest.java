package com.mshan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;



public class ParticipantTest {

    @Test
    public void testConstruction(){
        Participant participant = new Participant();
        Assertions.assertNotNull(participant);
    }
}
