package de.visparu.engine2d.entities.entitytypes;

public interface UpdateEntity extends GameEntity {
    void update(double updateDeltaSeconds);
    
    void fixedUpdate(double fixedUpdateDeltaSeconds);
}
