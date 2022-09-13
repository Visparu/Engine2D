package de.visparu.engine2d.gui.input;

public interface InputStateTracker {
    boolean isKeyPressed(int keycode);
    
    boolean isMousePressed(int button);
}
