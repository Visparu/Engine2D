package de.visparu.engine2d.gui.input.observers;

import de.visparu.engine2d.gui.input.events.GameMouseWheelEvent;

public interface MouseWheelObserver {
    void onMouseWheelMoved(GameMouseWheelEvent mouseWheelEvent);
}
