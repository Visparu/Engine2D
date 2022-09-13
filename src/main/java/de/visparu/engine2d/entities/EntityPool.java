package de.visparu.engine2d.entities;

import de.visparu.engine2d.context.GameContextComponent;
import de.visparu.engine2d.entities.entitytypes.CollisionEntity;
import de.visparu.engine2d.entities.entitytypes.GameEntity;
import de.visparu.engine2d.entities.entitytypes.RenderEntity;
import de.visparu.engine2d.entities.entitytypes.UpdateEntity;
import de.visparu.engine2d.gui.graphics.GameGraphics;

import java.util.Set;

public interface EntityPool extends GameContextComponent {
    void updateAllEntities(double updateDeltaSeconds);
    
    void fixedUpdateAllEntities(double fixedUpdateDeltaSeconds);
    
    void renderAllEntities(GameGraphics g);
    
    void registerEntity(GameEntity entity);
    
    void unregisterEntity(GameEntity entity);
    
    void runEntityManagement();
    
    Set<GameEntity> getEntityList();
    
    Set<RenderEntity> getRenderEntityList();
    
    Set<UpdateEntity> getUpdateEntityList();
    
    Set<CollisionEntity> getCollisionEntityList();
}
