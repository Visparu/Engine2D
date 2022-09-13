package de.visparu.engine2d.util.geometry.factories;

import de.visparu.engine2d.util.geometry.GameShape;
import de.visparu.engine2d.util.geometry.Rectangle;
import de.visparu.engine2d.util.geometry.Vector2D;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SwingRectangleFactory implements RectangleFactory {
    @Override
    public @NotNull Rectangle create(double x, double y, double width, double height) {
        return new SwingRectangle(x, y, width, height);
    }
    
    @Override
    public @NotNull Rectangle create(Vector2D anchor, Vector2D dimensions) {
        return new SwingRectangle(anchor, dimensions);
    }
    
    @Override
    public @NotNull Rectangle createSquare(double x, double y, double size) {
        return new SwingRectangle(x, y, size, size);
    }
    
    @Override
    public @NotNull Rectangle createSquare(Vector2D anchor, double size) {
        return new SwingRectangle(anchor, new Vector2D(size, size));
    }
    
    public static class SwingRectangle implements Rectangle {
        private final Rectangle2D internalRect;
        
        private SwingRectangle(double x, double y, double width, double height) {
            this.internalRect = new Rectangle2D.Double(x, y, width, height);
        }
        
        private SwingRectangle(@NotNull Vector2D anchor, @NotNull Vector2D dimensions) {
            this(anchor.x(), anchor.y(), dimensions.x(), dimensions.y());
        }
        
        @Override
        public @NotNull Vector2D getAnchor() {
            return new Vector2D(this.internalRect.getX(), this.internalRect.getY());
        }
        
        @Override
        public @NotNull Vector2D getDimensions() {
            return new Vector2D(this.internalRect.getWidth(), this.internalRect.getHeight());
        }
        
        @Override
        public @NotNull Vector2D getUpperLeft() {
            return new Vector2D(this.internalRect.getMinX(), this.internalRect.getMinY());
        }
    
        @Override
        public @NotNull Vector2D getUpperRight() {
            return new Vector2D(this.internalRect.getMaxX(), this.internalRect.getMinY());
        }
    
        @Override
        public @NotNull Vector2D getLowerLeft() {
            return new Vector2D(this.internalRect.getMinX(), this.internalRect.getMaxY());
        }
    
        @Override
        public @NotNull Vector2D getLowerRight() {
            return new Vector2D(this.internalRect.getMaxX(), this.internalRect.getMaxY());
        }
    
        @Override
        public @NotNull Vector2D getCenter() {
            return new Vector2D(this.internalRect.getCenterX(), this.internalRect.getCenterY());
        }
    
        @Override
        public boolean intersects(GameShape shape) {
            if (shape instanceof Shape s) {
                return s.intersects(this.internalRect);
            }
            Rectangle bounds = shape.getBounds();
            Rectangle2D awtBounds = new Rectangle2D.Double(
                    bounds.getAnchor().x(),
                    bounds.getAnchor().y(),
                    bounds.getDimensions().x(),
                    bounds.getDimensions().y());
            return awtBounds.intersects(this.internalRect);
        }
        
        @Override
        public @NotNull Rectangle getBounds() {
            return this;
        }
    }
}
