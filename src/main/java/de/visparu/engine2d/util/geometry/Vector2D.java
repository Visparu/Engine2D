package de.visparu.engine2d.util.geometry;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public record Vector2D(double x, double y) {
    public static final Vector2D ZERO = new Vector2D(0.0, 0.0);
    public static final Vector2D POS_X = new Vector2D(1.0, 0.0);
    public static final Vector2D NEG_X = new Vector2D(-1.0, 0.0);
    public static final Vector2D POS_Y = new Vector2D(0.0, 1.0);
    public static final Vector2D NEG_Y = new Vector2D(0.0, -1.0);
    
    public static @NotNull Vector2D centroid(@NotNull List<Vector2D> vectors) {
        if (vectors.isEmpty()) {
            return Vector2D.ZERO;
        }
        return vectors.stream()
                .reduce(Vector2D::add)
                .get()
                .scaleDown(vectors.size());
    }
    
    public static @NotNull Vector2D centroid(@NotNull Vector2D... vectors) {
        return Vector2D.centroid(Arrays.asList(vectors));
    }
    
    public static @NotNull Vector2D sum(@NotNull List<Vector2D> vectors) {
        return vectors.stream()
                .reduce(Vector2D::add)
                .orElse(Vector2D.ZERO);
    }
    
    public static @NotNull Vector2D sum(@NotNull Vector2D... vectors) {
        return Vector2D.sum(Arrays.asList(vectors));
    }
    
    public static double angleBetween(@NotNull Vector2D v1, @NotNull Vector2D v2) {
        return v1.getAngleBetween(v2);
    }
    
    public static double distance(@NotNull Vector2D v1, @NotNull Vector2D v2) {
        return v1.distance(v2);
    }
    
    public static double dotProduct(@NotNull Vector2D v1, @NotNull Vector2D v2) {
        return v1.dotProduct(v2);
    }
    
    public static @NotNull Vector2D crossProduct(@NotNull Vector2D v1, @NotNull Vector2D v2) {
        return v1.crossProduct(v2);
    }
    
    public @NotNull Vector2D add(@NotNull Vector2D v) {
        return new Vector2D(this.x + v.x, this.y + v.y);
    }
    
    public @NotNull Vector2D subtract(@NotNull Vector2D v) {
        return new Vector2D(this.x - v.x, this.y - v.y);
    }
    
    public @NotNull Vector2D scaleUp(double factor) {
        return new Vector2D(this.x * factor, this.y * factor);
    }
    
    public @NotNull Vector2D scaleDown(double divisor) {
        if (divisor == 0.0) {
            throw new IllegalArgumentException("Attempted division by 0.");
        }
        return new Vector2D(this.x / divisor, this.y / divisor);
    }
    
    public double length() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }
    
    public double distance(@NotNull Vector2D v) {
        return Math.sqrt(Math.pow(this.x - v.x, 2) + Math.pow(this.y - v.y, 2));
    }
    
    public @NotNull Vector2D normalize() {
        double euclideanDistance = this.length();
        return new Vector2D(this.x / euclideanDistance, this.y / euclideanDistance);
    }
    
    public double dotProduct(@NotNull Vector2D v) {
        return this.x * v.x + this.y * v.y;
    }
    
    public @NotNull Vector2D crossProduct(@NotNull Vector2D v) {
        return new Vector2D(this.y * v.x - this.x * v.y, this.x * v.y - this.y * v.x);
    }
    
    public double getAngleBetween(@NotNull Vector2D v) {
        double dot = this.dotProduct(v);
        double det = this.x * v.y - this.y * v.x;
        return Math.atan2(det, dot);
    }
    
    public double getAngle() {
        return this.getAngleBetween(Vector2D.POS_X);
    }
}
