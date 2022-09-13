package de.visparu.engine2d.util.geometry.factories;

import de.visparu.engine2d.util.geometry.Ellipse;
import de.visparu.engine2d.util.geometry.Vector2D;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface EllipseFactory {
    Ellipse create(double x, double y, double width, double height);
    Ellipse create(Vector2D anchor, Vector2D dimensions);
    Ellipse createCircle(double centerX, double centerY, double radius);
    Ellipse createCircle(Vector2D anchor, double radius);
    
    @Contract(value = " -> new", pure = true)
    static @NotNull EllipseFactory createDefaultFactory() {
        return new SwingEllipseFactory();
    }
}
