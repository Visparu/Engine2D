package de.visparu.engine2d.gui.graphics.rendering;

import de.visparu.engine2d.gui.graphics.GameGraphics;
import de.visparu.engine2d.gui.graphics.SwingGameGraphics;

import java.awt.image.BufferStrategy;

public class SwingGameRenderer implements GameRenderer {
    private final BufferStrategy bufferStrategy;
    
    public SwingGameRenderer(BufferStrategy bufferStrategy) {
        this.bufferStrategy = bufferStrategy;
    }
    
    @Override
    public GameGraphics startRender() {
        return new SwingGameGraphics(this.bufferStrategy.getDrawGraphics());
    }
    
    @Override
    public void finishRender() {
        this.bufferStrategy.show();
    }
}
