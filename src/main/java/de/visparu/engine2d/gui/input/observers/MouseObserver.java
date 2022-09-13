package de.visparu.engine2d.gui.input.observers;

import de.visparu.engine2d.gui.input.events.GameMouseEvent;

public interface MouseObserver {
    void onMousePressed(GameMouseEvent event);
    
    void onMouseReleased(GameMouseEvent event);
}
