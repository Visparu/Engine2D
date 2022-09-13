package de.visparu.engine2d.util.geometry.factories;

import de.visparu.engine2d.util.geometry.Rectangle;
import de.visparu.engine2d.util.geometry.Vector2D;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface RectangleFactory {
    Rectangle create(double x, double y, double width, double height);
    
    Rectangle create(Vector2D anchor, Vector2D dimensions);
    
    Rectangle createSquare(double x, double y, double size);
    
    Rectangle createSquare(Vector2D anchor, double size);
    
    @Contract(value = " -> new", pure = true)
    static @NotNull RectangleFactory createDefaultFactory() {
        return new SwingRectangleFactory();
    }
}
