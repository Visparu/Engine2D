package de.visparu.engine2d.entities.entitytypes;

public interface GameEntity {
    static double normalizeSpeed(double initialSpeed, double deltaSeconds) {
        return initialSpeed * deltaSeconds;
    }
}
