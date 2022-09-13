package de.visparu.engine2d.util.geometry;

import de.visparu.engine2d.util.geometry.factories.RectangleFactory;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.css.Rect;

public interface Rectangle extends GameShape {
    Vector2D getAnchor();
    
    Vector2D getDimensions();
    
    Vector2D getUpperLeft();
    
    Vector2D getUpperRight();
    
    Vector2D getLowerLeft();
    
    Vector2D getLowerRight();
    
    Vector2D getCenter();
    
    static @NotNull Rectangle create(double x, double y, double width, double height) {
        return RectangleFactory.createDefaultFactory()
                               .create(x, y, width, height);
    }
    
    static @NotNull Rectangle create(@NotNull Vector2D anchor, @NotNull Vector2D dimensions) {
        return RectangleFactory.createDefaultFactory()
                               .create(anchor, dimensions);
    }
    
    static @NotNull Rectangle createSquare(double x, double y, double size) {
        return RectangleFactory.createDefaultFactory()
                               .createSquare(x, y, size);
    }
    
    static @NotNull Rectangle createSquare(@NotNull Vector2D anchor, double size) {
        return RectangleFactory.createDefaultFactory()
                               .createSquare(anchor, size);
    }
}
