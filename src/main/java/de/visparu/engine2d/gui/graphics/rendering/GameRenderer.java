package de.visparu.engine2d.gui.graphics.rendering;

import de.visparu.engine2d.gui.graphics.GameGraphics;

public interface GameRenderer {
    GameGraphics startRender();
    
    void finishRender();
}
