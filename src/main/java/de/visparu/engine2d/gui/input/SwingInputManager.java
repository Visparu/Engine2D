package de.visparu.engine2d.gui.input;

import de.visparu.engine2d.gui.input.events.GameKeyEvent;
import de.visparu.engine2d.gui.input.events.GameMouseEvent;
import de.visparu.engine2d.gui.input.events.GameMouseMovedEvent;
import de.visparu.engine2d.gui.input.events.GameMouseWheelEvent;
import de.visparu.engine2d.gui.input.observers.KeyObserver;
import de.visparu.engine2d.gui.input.observers.MouseMotionObserver;
import de.visparu.engine2d.gui.input.observers.MouseObserver;
import de.visparu.engine2d.gui.input.observers.MouseWheelObserver;
import org.jetbrains.annotations.NotNull;

import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class SwingInputManager implements InputManager {
    private final InputStateTracker inputStateTracker;
    
    private final SwingInputManagerKeyAdapter      keyAdapter;
    private final SwingInputManagerKeyMouseAdapter mouseAdapter;
    
    private final Set<KeyObserver>         keyObservers;
    private final Set<MouseObserver>       mouseObservers;
    private final Set<MouseMotionObserver> mouseMotionObservers;
    private final Set<MouseWheelObserver>  mouseWheelObservers;
    
    private final Set<Integer> keysPressed;
    private final Set<Integer> buttonsPressed;
    
    public SwingInputManager() {
        this.keyAdapter   = new SwingInputManagerKeyAdapter();
        this.mouseAdapter = new SwingInputManagerKeyMouseAdapter();
        
        this.keyObservers         = new HashSet<>();
        this.mouseObservers       = new HashSet<>();
        this.mouseMotionObservers = new HashSet<>();
        this.mouseWheelObservers  = new HashSet<>();
        
        this.keysPressed    = new HashSet<>();
        this.buttonsPressed = new HashSet<>();
        
        this.inputStateTracker = new InputStateTracker() {
            @Override
            public boolean isKeyPressed(int keycode) {
                return SwingInputManager.this.keysPressed.contains(keycode);
            }
            
            @Override
            public boolean isMousePressed(int button) {
                return SwingInputManager.this.buttonsPressed.contains(button);
            }
        };
    }
    
    @Override
    public void registerKeyObserver(@NotNull KeyObserver keyObserver) {
        this.keyObservers.add(keyObserver);
    }
    
    @Override
    public void unregisterKeyObserver(@NotNull KeyObserver keyObserver) {
        this.keyObservers.remove(keyObserver);
    }
    
    @Override
    public void registerMouseMotionObserver(@NotNull MouseMotionObserver mouseMotionObserver) {
        this.mouseMotionObservers.add(mouseMotionObserver);
    }
    
    @Override
    public void unregisterMouseMotionObserver(@NotNull MouseMotionObserver mouseMotionObserver) {
        this.mouseMotionObservers.remove(mouseMotionObserver);
    }
    
    @Override
    public void registerMouseObserver(@NotNull MouseObserver mouseObserver) {
        this.mouseObservers.add(mouseObserver);
    }
    
    @Override
    public void unregisterMouseObserver(@NotNull MouseObserver mouseObserver) {
        this.mouseObservers.remove(mouseObserver);
    }
    
    @Override
    public void registerMouseWheelObserver(@NotNull MouseWheelObserver mouseWheelObserver) {
        this.mouseWheelObservers.add(mouseWheelObserver);
    }
    
    @Override
    public void unregisterMouseWheelObserver(@NotNull MouseWheelObserver mouseWheelObserver) {
        this.mouseWheelObservers.remove(mouseWheelObserver);
    }
    
    @NotNull
    public KeyListener getKeyListener() {
        return this.keyAdapter;
    }
    
    @NotNull
    public MouseListener getMouseListener() {
        return this.mouseAdapter;
    }
    
    @NotNull
    public MouseMotionListener getMouseMotionListener() {
        return this.mouseAdapter;
    }
    
    @NotNull
    public MouseWheelListener getMouseWheelListener() {
        return this.mouseAdapter;
    }
    
    @NotNull
    public InputStateTracker getInputStateTracker() { return this.inputStateTracker; }
    
    public class SwingInputManagerKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(@NotNull KeyEvent e) {
            SwingInputManager.this.keysPressed.add(e.getKeyCode());
            SwingInputManager.this.keyObservers.forEach(observer -> {
                observer.onKeyPressed(new GameKeyEvent(e.getKeyCode()));
            });
        }
        
        @Override
        public void keyReleased(@NotNull KeyEvent e) {
            SwingInputManager.this.keysPressed.remove(e.getKeyCode());
            SwingInputManager.this.keyObservers.forEach(observer -> {
                observer.onKeyReleased(new GameKeyEvent(e.getKeyCode()));
            });
        }
    }
    
    public class SwingInputManagerKeyMouseAdapter extends MouseAdapter {
        private int previousX = 0;
        private int previousY = 0;
        
        @Override
        public void mousePressed(@NotNull MouseEvent e) {
            SwingInputManager.this.buttonsPressed.add(e.getButton());
            SwingInputManager.this.mouseObservers.forEach(observer -> {
                observer.onMousePressed(new GameMouseEvent(e.getButton(), e.getX(), e.getY()));
            });
        }
        
        @Override
        public void mouseReleased(@NotNull MouseEvent e) {
            SwingInputManager.this.buttonsPressed.remove(e.getButton());
            SwingInputManager.this.mouseObservers.forEach(observer -> {
                observer.onMouseReleased(new GameMouseEvent(e.getButton(), e.getX(), e.getY()));
            });
        }
        
        @Override
        public void mouseMoved(@NotNull MouseEvent e) {
            SwingInputManager.this.mouseMotionObservers.forEach(observer -> {
                observer.onMouseMoved(new GameMouseMovedEvent(this.previousX, this.previousY, e.getX(), e.getY()));
            });
            this.previousX = e.getX();
            this.previousY = e.getY();
        }
        
        @Override
        public void mouseWheelMoved(@NotNull MouseWheelEvent e) {
            SwingInputManager.this.mouseWheelObservers.forEach(observer -> {
                observer.onMouseWheelMoved(new GameMouseWheelEvent(e.getScrollAmount()));
            });
        }
    }
}
