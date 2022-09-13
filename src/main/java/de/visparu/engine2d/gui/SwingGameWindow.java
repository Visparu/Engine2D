package de.visparu.engine2d.gui;

import de.visparu.engine2d.context.GameContext;
import de.visparu.engine2d.gui.graphics.rendering.GameRenderer;
import de.visparu.engine2d.gui.graphics.rendering.SwingGameRenderer;
import de.visparu.engine2d.gui.input.InputManager;
import de.visparu.engine2d.gui.input.SwingInputManager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SwingGameWindow implements GameWindow {
    private static final String   DEFAULT_TITLE                = "Game";
    private static final int      DEFAULT_WIDTH                = 600;
    private static final int      DEFAULT_HEIGHT               = 600;
    private static final boolean  DEFAULT_RESIZABLE            = false;
    private static final Runnable DEFAULT_WINDOW_CLOSING_EVENT = () -> {
    };
    
    private JFrame jframe;
    private Canvas canvas;
    
    private SwingGameRenderer gameRenderer;
    private SwingInputManager inputManager;
    
    private SwingGameWindow() {
    
    }
    
    @Override
    public void initialize(GameContext context) {
        this.inputManager = new SwingInputManager();
        this.canvas.addKeyListener(this.inputManager.getKeyListener());
        this.canvas.addMouseListener(this.inputManager.getMouseListener());
        this.canvas.addMouseMotionListener(this.inputManager.getMouseMotionListener());
        this.canvas.addMouseWheelListener(this.inputManager.getMouseWheelListener());
        
        this.canvas.createBufferStrategy(3);
        this.gameRenderer = new SwingGameRenderer(this.canvas.getBufferStrategy());
        
        this.jframe.setVisible(true);
    }
    
    @Override
    public int getWidth() {
        return this.canvas.getWidth();
    }
    
    @Override
    public int getHeight() {
        return this.canvas.getHeight();
    }
    
    @Override
    public GameRenderer getRenderer() {
        return this.gameRenderer;
    }
    
    @Override
    public InputManager getInputManager() {
        return this.inputManager;
    }
    
    @Contract(value = " -> new", pure = true)
    public static @NotNull SwingGameWindowBuilder builder() {
        return new SwingGameWindowBuilder();
    }
    
    public static class SwingGameWindowBuilder {
        private String   title              = SwingGameWindow.DEFAULT_TITLE;
        private int      width              = SwingGameWindow.DEFAULT_WIDTH;
        private int      height             = SwingGameWindow.DEFAULT_HEIGHT;
        private boolean  resizable          = SwingGameWindow.DEFAULT_RESIZABLE;
        private Runnable windowClosingEvent = SwingGameWindow.DEFAULT_WINDOW_CLOSING_EVENT;
        
        private SwingGameWindowBuilder() {
        
        }
        
        public SwingGameWindowBuilder withTitle(@NotNull String title) {
            this.title = title;
            return this;
        }
        
        public SwingGameWindowBuilder withWidth(int width) {
            this.width = width;
            return this;
        }
        
        public SwingGameWindowBuilder withHeight(int height) {
            this.height = height;
            return this;
        }
        
        public SwingGameWindowBuilder withResizable(boolean resizable) {
            this.resizable = resizable;
            return this;
        }
        
        public SwingGameWindowBuilder withOnWindowClosing(Runnable runnable) {
            this.windowClosingEvent = runnable;
            return this;
        }
        
        public SwingGameWindow build() {
            SwingGameWindow sgw = new SwingGameWindow();
            sgw.jframe = new JFrame();
            sgw.jframe.setTitle(this.title);
            sgw.jframe.setResizable(this.resizable);
            sgw.jframe.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            sgw.jframe.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    e.getWindow()
                     .dispose();
                    SwingGameWindowBuilder.this.windowClosingEvent.run();
                    System.exit(0);
                }
            });
            
            sgw.canvas = new Canvas();
            sgw.canvas.setSize(this.width, this.height);
            
            sgw.jframe.add(sgw.canvas);
            sgw.jframe.pack();
            sgw.jframe.setLocationRelativeTo(null);
            
            return sgw;
        }
    }
}
