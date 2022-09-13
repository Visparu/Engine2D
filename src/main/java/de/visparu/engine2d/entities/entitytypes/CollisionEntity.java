package de.visparu.engine2d.entities.entitytypes;

import de.visparu.engine2d.util.geometry.GameShape;
import de.visparu.engine2d.util.geometry.Rectangle;

public interface CollisionEntity extends GameEntity{
    Rectangle getBounds();
    GameShape getCollider();
    boolean collides(CollisionEntity collider);
    void onCollision(CollisionEntity collider);
}
