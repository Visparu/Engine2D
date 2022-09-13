package de.visparu.engine2d.util.geometry.factories;

import de.visparu.engine2d.util.geometry.Ellipse;
import de.visparu.engine2d.util.geometry.GameShape;
import de.visparu.engine2d.util.geometry.Rectangle;
import de.visparu.engine2d.util.geometry.Vector2D;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class SwingEllipseFactory implements EllipseFactory{
    @Override
    public @NotNull Ellipse create(double x, double y, double width, double height) {
        return new SwingEllipse(x, y, width, height);
    }
    
    @Override
    public @NotNull Ellipse create(@NotNull Vector2D anchor, @NotNull Vector2D dimensions) {
        return new SwingEllipse(anchor, dimensions);
    }
    
    @Override
    public @NotNull Ellipse createCircle(double centerX, double centerY, double radius) {
        return new SwingEllipse(centerX - radius, centerY - radius, radius * 2, radius * 2);
    }
    
    @Override
    public @NotNull Ellipse createCircle(@NotNull Vector2D anchor, double radius) {
        return new SwingEllipse(anchor.x() - radius, anchor.y() - radius, radius * 2, radius * 2);
    }
    
    public static class SwingEllipse implements Ellipse {
        private final Ellipse2D internalEllipse;
        
        private SwingEllipse(double x, double y, double width, double height) {
            this.internalEllipse = new Ellipse2D.Double(x, y, width, height);
        }
        
        private SwingEllipse(@NotNull Vector2D anchor, @NotNull Vector2D dimensions) {
            this(anchor.x(), anchor.y(), dimensions.x(), dimensions.y());
        }
    
        @Override
        public @NotNull Vector2D getAnchor() {
            return new Vector2D(this.internalEllipse.getX(), this.internalEllipse.getY());
        }
    
        @Override
        public @NotNull Vector2D getDimensions() {
            return new Vector2D(this.internalEllipse.getWidth(), this.internalEllipse.getHeight());
        }
    
        @Override
        public double getRadiusX() {
            return this.internalEllipse.getWidth();
        }
        
        @Override
        public double getRadiusY() {return this.internalEllipse.getHeight(); }
    
        @Override
        public boolean intersects(GameShape shape) {
            if (shape instanceof Shape s) {
                return s.intersects(this.internalEllipse.getBounds());
            }
            Rectangle bounds = shape.getBounds();
            Rectangle2D awtBounds = new Rectangle2D.Double(
                    bounds.getAnchor().x(),
                    bounds.getAnchor().y(),
                    bounds.getDimensions().x(),
                    bounds.getDimensions().y());
            return awtBounds.intersects(this.internalEllipse.getBounds());
        }
    
        @Override
        public @NotNull Rectangle getBounds() {
            return RectangleFactory.createDefaultFactory()
                                   .create(this.internalEllipse.getX(),
                                           this.internalEllipse.getY(),
                                           this.internalEllipse.getWidth(),
                                           this.internalEllipse.getHeight());
        }
    }
}
