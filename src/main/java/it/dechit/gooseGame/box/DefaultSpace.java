package it.dechit.gooseGame.box;

import java.util.function.Function;

public class DefaultSpace implements Space {

    private final String name;

    private final Integer index;

    public DefaultSpace(String name, Integer index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Function<Integer, Integer> getSpaceRule() {
        return (roll) -> index;
    }

    public Integer getIndex() {
        return index;
    }

}
