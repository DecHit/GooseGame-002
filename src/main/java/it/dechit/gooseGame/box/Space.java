package it.dechit.gooseGame.box;

import java.util.function.Function;

public interface Space {

    String getName();

    Function<Integer, Integer> getSpaceRule();
}
