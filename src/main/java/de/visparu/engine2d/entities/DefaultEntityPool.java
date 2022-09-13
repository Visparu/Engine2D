package de.visparu.engine2d.entities;

import de.visparu.engine2d.context.GameContext;
import de.visparu.engine2d.entities.entitytypes.*;
import de.visparu.engine2d.gui.graphics.GameGraphics;
import de.visparu.engine2d.gui.input.observers.KeyObserver;
import de.visparu.engine2d.gui.input.observers.MouseMotionObserver;
import de.visparu.engine2d.gui.input.observers.MouseObserver;
import de.visparu.engine2d.gui.input.observers.MouseWheelObserver;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DefaultEntityPool implements EntityPool {
    private GameContext context;
    
    private final Set<GameEntity>      allEntities       = new HashSet<>();
    private final Set<RenderEntity>    renderEntities    = new HashSet<>();
    private final Set<UpdateEntity>    updateEntities    = new HashSet<>();
    private final Set<CollisionEntity> collisionEntities = new HashSet<>();
    
    private final Set<GameEntity> entitiesToAdd    = new HashSet<>();
    private final Set<GameEntity> entitiesToRemove = new HashSet<>();
    
    private DefaultEntityPool() {
    
    }
    
    @Override
    public void initialize(GameContext context) {
        this.context = context;
    }
    
    @Override
    public void updateAllEntities(double updateDeltaSeconds) {
        this.updateEntities.forEach((entity) -> entity.update(updateDeltaSeconds));
    }
    
    @Override
    public void fixedUpdateAllEntities(double fixedUpdateDeltaSeconds) {
        this.updateEntities.forEach((entity) -> entity.fixedUpdate(fixedUpdateDeltaSeconds));
    }
    
    @Override
    public void renderAllEntities(GameGraphics g) {
        this.renderEntities.forEach((entity) -> entity.render(g));
    }
    
    @Override
    public void registerEntity(GameEntity entity) {
        synchronized (this.entitiesToAdd) {
            if (this.allEntities.contains(entity)) {
                return;
            }
            this.entitiesToAdd.add(entity);
        }
    }
    
    @Override
    public void unregisterEntity(GameEntity entity) {
        synchronized (this.entitiesToRemove) {
            if (!this.allEntities.contains(entity)) {
                return;
            }
            this.entitiesToRemove.add(entity);
        }
    }
    
    @Override
    public void runEntityManagement() {
        synchronized (this.entitiesToAdd) {
            this.entitiesToAdd.forEach((entity) -> {
                this.allEntities.add(entity);
                if (entity instanceof RenderEntity renderEntity) {
                    this.renderEntities.add(renderEntity);
                }
                if (entity instanceof UpdateEntity updateEntity) {
                    this.updateEntities.add(updateEntity);
                }
                if (entity instanceof CollisionEntity collisionEntity) {
                    this.collisionEntities.add(collisionEntity);
                }
                if (entity instanceof ControlledEntity controlledEntity) {
                    controlledEntity.initialize(this.context.getWindow()
                                                            .getInputManager()
                                                            .getInputStateTracker());
                }
                if (entity instanceof KeyObserver keyObserver) {
                    this.context.getWindow()
                                .getInputManager()
                                .registerKeyObserver(keyObserver);
                }
                if (entity instanceof MouseObserver mouseObserver) {
                    this.context.getWindow()
                                .getInputManager()
                                .registerMouseObserver(mouseObserver);
                }
                if (entity instanceof MouseMotionObserver mouseMotionObserver) {
                    this.context.getWindow()
                                .getInputManager()
                                .registerMouseMotionObserver(mouseMotionObserver);
                }
                if (entity instanceof MouseWheelObserver mouseWheelObserver) {
                    this.context.getWindow()
                                .getInputManager()
                                .registerMouseWheelObserver(mouseWheelObserver);
                }
                if (entity instanceof ManagerEntity managerEntity) {
                    managerEntity.initialize(this.context);
                }
            });
            this.entitiesToAdd.clear();
        }
        
        synchronized (this.entitiesToRemove) {
            this.entitiesToRemove.forEach((entity) -> {
                this.allEntities.remove(entity);
                if (entity instanceof RenderEntity renderEntity) {
                    this.renderEntities.remove(renderEntity);
                }
                if (entity instanceof UpdateEntity updateEntity) {
                    this.updateEntities.remove(updateEntity);
                }
                if (entity instanceof CollisionEntity collisionEntity) {
                    this.collisionEntities.remove(collisionEntity);
                }
                if (entity instanceof KeyObserver keyObserver) {
                    this.context.getWindow()
                                .getInputManager()
                                .unregisterKeyObserver(keyObserver);
                }
                if (entity instanceof MouseObserver mouseObserver) {
                    this.context.getWindow()
                                .getInputManager()
                                .unregisterMouseObserver(mouseObserver);
                }
                if (entity instanceof MouseMotionObserver mouseMotionObserver) {
                    this.context.getWindow()
                                .getInputManager()
                                .unregisterMouseMotionObserver(mouseMotionObserver);
                }
                if (entity instanceof MouseWheelObserver mouseWheelObserver) {
                    this.context.getWindow()
                                .getInputManager()
                                .unregisterMouseWheelObserver(mouseWheelObserver);
                }
            });
            this.entitiesToRemove.clear();
        }
    }
    
    @Override
    @UnmodifiableView
    public Set<GameEntity> getEntityList() {
        return Collections.unmodifiableSet(this.allEntities);
    }
    
    @Override
    @UnmodifiableView
    public Set<RenderEntity> getRenderEntityList() {
        return Collections.unmodifiableSet(this.renderEntities);
    }
    
    @Override
    @UnmodifiableView
    public Set<UpdateEntity> getUpdateEntityList() {
        return Collections.unmodifiableSet(this.updateEntities);
    }
    
    @Override
    @UnmodifiableView
    public Set<CollisionEntity> getCollisionEntityList() { return Collections.unmodifiableSet(this.collisionEntities); }

    @Contract(value = " -> new", pure = true)
    public static @NotNull DefaultEntityPoolBuilder builder() {
        return new DefaultEntityPoolBuilder();
    }
    
    public static class DefaultEntityPoolBuilder {
        private final DefaultEntityPool instance;
        
        private DefaultEntityPoolBuilder() {
            this.instance = new DefaultEntityPool();
        }
        
        public DefaultEntityPool build() {
            return this.instance;
        }
    }
}
