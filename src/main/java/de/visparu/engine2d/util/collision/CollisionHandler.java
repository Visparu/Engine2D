package de.visparu.engine2d.util.collision;

import de.visparu.engine2d.context.GameContextComponent;

public interface CollisionHandler extends GameContextComponent {
    void calculateCollisions();
    void resolveCollisions();
}
