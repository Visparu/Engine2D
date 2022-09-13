package de.visparu.engine2d.gui.input;

import de.visparu.engine2d.gui.input.observers.KeyObserver;
import de.visparu.engine2d.gui.input.observers.MouseMotionObserver;
import de.visparu.engine2d.gui.input.observers.MouseObserver;
import de.visparu.engine2d.gui.input.observers.MouseWheelObserver;

public interface InputManager {
    void registerKeyObserver(KeyObserver keyObserver);
    
    void unregisterKeyObserver(KeyObserver keyObserver);
    
    void registerMouseMotionObserver(MouseMotionObserver mouseMotionObserver);
    
    void unregisterMouseMotionObserver(MouseMotionObserver mouseMotionObserver);
    
    void registerMouseObserver(MouseObserver mouseObserver);
    
    void unregisterMouseObserver(MouseObserver mouseObserver);
    
    void registerMouseWheelObserver(MouseWheelObserver mouseWheelObserver);
    
    void unregisterMouseWheelObserver(MouseWheelObserver mouseWheelObserver);
    
    InputStateTracker getInputStateTracker();
}
