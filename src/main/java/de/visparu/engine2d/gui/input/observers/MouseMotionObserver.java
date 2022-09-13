package de.visparu.engine2d.gui.input.observers;

import de.visparu.engine2d.gui.input.events.GameMouseMovedEvent;

public interface MouseMotionObserver {
    void onMouseMoved(GameMouseMovedEvent event);
}
