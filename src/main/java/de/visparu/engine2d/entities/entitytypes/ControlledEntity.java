package de.visparu.engine2d.entities.entitytypes;

import de.visparu.engine2d.gui.input.InputStateTracker;

public interface ControlledEntity extends GameEntity {
    void initialize(InputStateTracker inputStateTracker);
}
