package de.visparu.engine2d.util.collision;

import de.visparu.engine2d.context.GameContext;
import de.visparu.engine2d.entities.entitytypes.CollisionEntity;
import de.visparu.engine2d.util.datastructures.QuadTree;
import de.visparu.engine2d.util.geometry.Rectangle;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuadTreeCollisionHandler implements CollisionHandler {
    private GameContext               context;
    private QuadTree<CollisionEntity> quadTree;
    private Set<Collision>            unresolvedCollisions;
    
    private QuadTreeCollisionHandler() {
    
    }
    
    @Override
    public void initialize(GameContext context) {
        this.context = context;
        
        double width  = this.context.getWindow()
                                    .getWidth();
        double height = this.context.getWindow()
                                    .getHeight();
        this.quadTree = new QuadTree<>(Rectangle.create(0, 0, width, height));
        this.unresolvedCollisions = new HashSet<>();
    }
    
    @Override
    public void calculateCollisions() {
        quadTree.clear();
        this.context.getEntityPool()
                    .getCollisionEntityList()
                    .forEach(quadTree::insert);
        
        this.context.getEntityPool()
                .getCollisionEntityList()
                .forEach(entity -> {
                    List<CollisionEntity> possibleCollisions = quadTree.retrieve(entity);
                    possibleCollisions.forEach(collider -> {
                        if (entity == collider) {
                            return;
                        }
                        if (entity.collides(collider)) {
                            Collision collision = new Collision(entity, collider);
                            this.unresolvedCollisions.add(collision);
                        }
                    });
                });
    }
    
    @Override
    public void resolveCollisions() {
        this.unresolvedCollisions.forEach((collision) -> {
            collision.collider1().onCollision(collision.collider2());
            collision.collider2().onCollision(collision.collider1());
        });
        this.unresolvedCollisions.clear();
    }
    
    @Contract(value = " -> new", pure = true)
    public static @NotNull QuadTreeCollisionHandlerBuilder builder() {
        return new QuadTreeCollisionHandlerBuilder();
    }
    
    public static class QuadTreeCollisionHandlerBuilder {
        private final QuadTreeCollisionHandler instance;
        
        private QuadTreeCollisionHandlerBuilder() {
            this.instance = new QuadTreeCollisionHandler();
        }
        
        public QuadTreeCollisionHandler build() {
            return this.instance;
        }
    }
}
