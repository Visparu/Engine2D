package de.visparu.engine2d.util.geometry;

import de.visparu.engine2d.util.geometry.factories.EllipseFactory;
import org.jetbrains.annotations.NotNull;

public interface Ellipse extends GameShape {
    Vector2D getAnchor();
    Vector2D getDimensions();
    double getRadiusX();
    double getRadiusY();
    
    static @NotNull Ellipse create(double x, double y, double width, double height) {
        return EllipseFactory.createDefaultFactory()
                             .create(x, y, width, height);
    }
    
    static @NotNull Ellipse create(@NotNull Vector2D anchor, @NotNull Vector2D dimensions) {
        return EllipseFactory.createDefaultFactory()
                             .create(anchor, dimensions);
    }
    
    static @NotNull Ellipse createCircle(double centerX, double centerY, double radius) {
        return EllipseFactory.createDefaultFactory()
                             .createCircle(centerX, centerY, radius);
    }
    
    static @NotNull Ellipse createCircle(@NotNull Vector2D center, double radius) {
        return EllipseFactory.createDefaultFactory()
                             .createCircle(center, radius);
    }
}
