package de.visparu.engine2d.gui;

import de.visparu.engine2d.context.GameContextComponent;
import de.visparu.engine2d.gui.graphics.rendering.GameRenderer;
import de.visparu.engine2d.gui.input.InputManager;

public interface GameWindow extends GameContextComponent {
    int getWidth();
    
    int getHeight();
    
    GameRenderer getRenderer();
    
    InputManager getInputManager();
}
