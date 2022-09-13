package de.visparu.engine2d.gui.input.observers;

import de.visparu.engine2d.gui.input.events.GameKeyEvent;

public interface KeyObserver {
    void onKeyPressed(GameKeyEvent event);
    
    void onKeyReleased(GameKeyEvent event);
}
