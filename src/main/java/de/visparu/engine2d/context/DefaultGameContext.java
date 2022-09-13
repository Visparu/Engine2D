package de.visparu.engine2d.context;

import de.visparu.engine2d.entities.EntityPool;
import de.visparu.engine2d.framework.GameFramework;
import de.visparu.engine2d.gui.GameWindow;
import de.visparu.engine2d.util.collision.CollisionHandler;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class DefaultGameContext implements GameContext {
    private GameFramework framework;
    private GameWindow    window;
    private EntityPool    entityPool;
    private CollisionHandler collisionHandler;
    
    private DefaultGameContext() {
    
    }
    
    @Override
    public void initialize() {
        this.framework.initialize(this);
        this.window.initialize(this);
        this.entityPool.initialize(this);
        this.collisionHandler.initialize(this);
    }
    
    @Override
    public GameFramework getFramework() {
        return this.framework;
    }
    
    @Override
    public GameWindow getWindow() {
        return this.window;
    }
    
    @Override
    public EntityPool getEntityPool() {
        return this.entityPool;
    }
    
    @Override
    public CollisionHandler getCollisionHandler() { return this.collisionHandler; };
    
    @Contract(value = " -> new", pure = true)
    public static @NotNull DefaultGameContextBuilder builder() {
        return new DefaultGameContextBuilder();
    }
    
    public static class DefaultGameContextBuilder {
        private final DefaultGameContext instance;
        
        private DefaultGameContextBuilder() {
            this.instance = new DefaultGameContext();
        }
        
        public DefaultGameContextBuilder withFramework(GameFramework framework) {
            this.instance.framework = framework;
            return this;
        }
        
        public DefaultGameContextBuilder withWindow(GameWindow window) {
            this.instance.window = window;
            return this;
        }
        
        public DefaultGameContextBuilder withEntityPool(EntityPool entityPool) {
            this.instance.entityPool = entityPool;
            return this;
        }
        
        public DefaultGameContextBuilder withCollisionHandler(CollisionHandler collisionHandler) {
            this.instance.collisionHandler = collisionHandler;
            return this;
        }
        
        public DefaultGameContext build() {
            if (this.instance.framework == null) {
                throw new IllegalStateException("Framework not set.");
            }
            if (this.instance.window == null) {
                throw new IllegalStateException("Window not set.");
            }
            if (this.instance.entityPool == null) {
                throw new IllegalStateException("EntityPool not set.");
            }
            if (this.instance.collisionHandler == null) {
                throw new IllegalStateException("CollisionHandler not set.");
            }
            return this.instance;
        }
    }
}
