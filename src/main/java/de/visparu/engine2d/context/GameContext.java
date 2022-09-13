package de.visparu.engine2d.context;

import de.visparu.engine2d.entities.EntityPool;
import de.visparu.engine2d.framework.GameFramework;
import de.visparu.engine2d.gui.GameWindow;
import de.visparu.engine2d.util.collision.CollisionHandler;

public interface GameContext {
    void initialize();
    
    GameFramework getFramework();
    
    GameWindow getWindow();
    
    EntityPool getEntityPool();
    
    CollisionHandler getCollisionHandler();
}
