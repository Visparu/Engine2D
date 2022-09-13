package de.visparu.engine2d.util.collision;

import de.visparu.engine2d.entities.entitytypes.CollisionEntity;

public record Collision(CollisionEntity collider1, CollisionEntity collider2) {
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Collision c) {
            if (this.collider1 == c.collider1 && this.collider2 == c.collider2) {
                return true;
            }
            if (this.collider1 == c.collider2 && this.collider2 == c.collider1) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return collider1.hashCode() * collider2.hashCode();
    }
}
