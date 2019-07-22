package it.dechit.gooseGame;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class DiceTest {

    private Dice dice;

    @Before
    public void setUp() {
        dice = new Dice(6);
    }

    @Test
    public void rollReturnIntegerBetweenOneAndSix() {
        Integer result = dice.roll();

        assertThat(result, is(both(greaterThanOrEqualTo(1)).and(lessThanOrEqualTo(6))));
    }
}