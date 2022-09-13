package de.visparu.engine2d.util.geometry;

public interface GameShape {
    boolean intersects(GameShape shape);
    
    Rectangle getBounds();
}
