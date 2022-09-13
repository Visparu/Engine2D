package de.visparu.engine2d.gui.graphics;

import de.visparu.engine2d.util.geometry.Ellipse;
import de.visparu.engine2d.util.geometry.Rectangle;
import de.visparu.engine2d.util.geometry.Vector2D;

public interface GameGraphics {
    void setColor(GameColor gameColor);
    
    void setColor(GameColors gameColors);
    
    void drawLine(double x1, double y1, double x2, double y2);
    
    void drawLine(Vector2D v1, Vector2D v2);
    
    void drawRectangle(double x1, double y1, double x2, double y2);
    
    void drawRectangle(Rectangle r);
    
    void fillRectangle(double x1, double y1, double x2, double y2);
    
    void fillRectangle(Rectangle r);
    
    void drawEllipse(double x1, double y1, double x2, double y2);
    
    void drawEllipse(Ellipse e);
    
    void fillEllipse(double x1, double y1, double x2, double y2);
    
    void fillEllipse(Ellipse e);
    
    void dispose();
}
