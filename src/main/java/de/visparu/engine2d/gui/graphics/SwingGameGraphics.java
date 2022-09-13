package de.visparu.engine2d.gui.graphics;

import de.visparu.engine2d.util.geometry.Ellipse;
import de.visparu.engine2d.util.geometry.Rectangle;
import de.visparu.engine2d.util.geometry.Vector2D;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class SwingGameGraphics implements GameGraphics {
    private final Graphics2D g2d;
    
    public SwingGameGraphics(Graphics g) {
        this.g2d = (Graphics2D) g;
    }
    
    @Override
    public void setColor(@NotNull GameColor gameColor) {
        this.g2d.setColor(new Color(gameColor.red(), gameColor.green(), gameColor.blue()));
    }
    
    @Override
    public void setColor(@NotNull GameColors gameColors) {
        this.setColor(gameColors.getGameColor());
    }
    
    @Override
    public void drawLine(double x1, double y1, double x2, double y2) {
        int absX1 = (int) Math.round(x1);
        int absY1 = (int) Math.round(y1);
        int absX2 = (int) Math.round(x2);
        int absY2 = (int) Math.round(y2);
        this.g2d.drawLine(absX1, absY1, absX2, absY2);
    }
    
    @Override
    public void drawLine(@NotNull Vector2D v1, @NotNull Vector2D v2) {
        this.drawLine(v1.x(), v1.y(), v2.x(), v2.y());
    }
    
    @Override
    public void drawRectangle(double x, double y, double width, double height) {
        int absX      = (int) Math.round(x);
        int absY      = (int) Math.round(y);
        int absWidth  = (int) Math.round(width);
        int absHeight = (int) Math.round(height);
        this.g2d.drawRect(absX, absY, absWidth, absHeight);
    }
    
    @Override
    public void drawRectangle(@NotNull Rectangle r) {
        this.drawRectangle(r.getAnchor().x(), r.getAnchor().y(), r.getDimensions().x(), r.getDimensions().y());
    }
    
    @Override
    public void fillRectangle(double x, double y, double width, double height) {
        int absX      = (int) Math.round(x);
        int absY      = (int) Math.round(y);
        int absWidth  = (int) Math.round(width);
        int absHeight = (int) Math.round(height);
        this.g2d.fillRect(absX, absY, absWidth, absHeight);
    }
    
    @Override
    public void fillRectangle(@NotNull Rectangle r) {
        this.fillRectangle(r.getAnchor().x(), r.getAnchor().y(), r.getDimensions().x(), r.getDimensions().y());
    }
    
    @Override
    public void drawEllipse(double x, double y, double width, double height) {
        int absX      = (int) Math.round(x);
        int absY      = (int) Math.round(y);
        int absWidth  = (int) Math.round(width);
        int absHeight = (int) Math.round(height);
        this.g2d.drawOval(absX, absY, absWidth, absHeight);
    }
    
    @Override
    public void drawEllipse(@NotNull Ellipse e) {
        this.drawEllipse(e.getAnchor().x(), e.getAnchor().y(), e.getDimensions().x(), e.getDimensions().y());
    }
    
    @Override
    public void fillEllipse(double x, double y, double width, double height) {
        int absX      = (int) Math.round(x);
        int absY      = (int) Math.round(y);
        int absWidth  = (int) Math.round(width);
        int absHeight = (int) Math.round(height);
        this.g2d.fillOval(absX, absY, absWidth, absHeight);
    }
    
    @Override
    public void fillEllipse(@NotNull Ellipse e) {
        this.fillEllipse(e.getAnchor().x(), e.getAnchor().y(), e.getDimensions().x(), e.getDimensions().y());
    }
    
    @Override
    public void dispose() {
        this.g2d.dispose();
    }
}
