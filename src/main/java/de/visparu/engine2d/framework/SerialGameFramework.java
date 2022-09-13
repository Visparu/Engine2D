package de.visparu.engine2d.framework;

import de.visparu.engine2d.context.GameContext;
import de.visparu.engine2d.gui.GameWindow;
import de.visparu.engine2d.gui.graphics.GameColors;
import de.visparu.engine2d.gui.graphics.GameGraphics;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class SerialGameFramework implements GameFramework, Runnable {
    private static final double DEFAULT_FIXED_TPS = 60;
    
    private GameContext context;
    
    private boolean running = false;
    
    private double fixedTPS = SerialGameFramework.DEFAULT_FIXED_TPS;
    
    private SerialGameFramework() {
    
    }
    
    @Override
    public void initialize(GameContext context) {
        this.context = context;
    }
    
    @Override
    public synchronized void start() {
        if (this.running) {
            throw new IllegalStateException("Framework is already running.");
        }
        this.running = true;
        new Thread(this).start();
    }
    
    @Override
    public void stop() {
        if (!this.running) {
            throw new IllegalStateException("Framework is not running.");
        }
        this.running = false;
    }
    
    @Override
    public void run() {
        long startTime = System.nanoTime();
        
        long lastUpdateTime      = startTime;
        long lastFixedUpdateTime = startTime;
        
        double fixedUpdateDelta = 1_000_000_000 / this.fixedTPS;
        
        while (this.running) {
            long   now                            = System.nanoTime();
            double preciseUpdateDeltaSeconds      = (double) (now - lastUpdateTime) / 1_000_000_000;
            double preciseFixedUpdateDeltaSeconds = (double) (now - lastFixedUpdateTime) / 1_000_000_000;
            
            this.update(preciseUpdateDeltaSeconds);
            lastUpdateTime = now;
            
            if ((now - lastFixedUpdateTime) >= fixedUpdateDelta) {
                this.fixedUpdate(preciseFixedUpdateDeltaSeconds);
                lastFixedUpdateTime = now;
            }
            
            try {
                this.render();
            } catch (IllegalStateException ignored) {
            
            }
        }
    }
    
    private void update(double deltaSeconds) {
        this.context.getEntityPool()
                    .updateAllEntities(deltaSeconds);
    }
    
    private void fixedUpdate(double deltaSeconds) {
        this.context.getEntityPool()
                    .fixedUpdateAllEntities(deltaSeconds);
        
        this.context.getCollisionHandler()
                    .calculateCollisions();
        
        this.context.getCollisionHandler()
                    .resolveCollisions();
        
        this.context.getEntityPool()
                    .runEntityManagement();
    }
    
    private void render() {
        GameWindow window = this.context.getWindow();
        GameGraphics g = window.getRenderer()
                               .startRender();
        
        g.setColor(GameColors.BLACK);
        g.fillRectangle(0, 0, window.getWidth(), window.getHeight());
        
        this.context.getEntityPool()
                    .renderAllEntities(g);
        
        window.getRenderer()
              .finishRender();
    }
    
    @Contract(value = " -> new", pure = true)
    public static @NotNull SerialGameFrameworkBuilder builder() {
        return new SerialGameFrameworkBuilder();
    }
    
    public static class SerialGameFrameworkBuilder {
        private final SerialGameFramework instance;
        
        private SerialGameFrameworkBuilder() {
            this.instance = new SerialGameFramework();
        }
        
        public SerialGameFrameworkBuilder withFixedTPS(double fixedTPS) {
            this.instance.fixedTPS = fixedTPS;
            return this;
        }
        
        public SerialGameFramework build() {
            return this.instance;
        }
    }
}
