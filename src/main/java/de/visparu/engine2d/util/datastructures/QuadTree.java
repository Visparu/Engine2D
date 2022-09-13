package de.visparu.engine2d.util.datastructures;

import de.visparu.engine2d.entities.entitytypes.CollisionEntity;
import de.visparu.engine2d.util.geometry.Rectangle;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class QuadTree<T extends CollisionEntity> {
    private static final int MAX_OBJECTS = 10;
    private static final int MAX_LEVELS = 5;
    
    private final int     level;
    private final List<T>    objects;
    private final Rectangle  bounds;
    private final QuadTree[] nodes;
    
    public QuadTree(Rectangle pBounds) {
        this(0, pBounds);
    }
    
    public QuadTree(int pLevel, Rectangle pBounds) {
        level = pLevel;
        objects = new ArrayList<T>();
        bounds = pBounds;
        nodes = new QuadTree[4];
    }
    
    public void clear() {
        objects.clear();
        
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                nodes[i].clear();
                nodes[i] = null;
            }
        }
    }
    
    private void split() {
        double x = bounds.getAnchor().x();
        double y = bounds.getAnchor().y();
        double subWidth = bounds.getDimensions().x() / 2;
        double subHeight = bounds.getDimensions().y() / 2;
        
        nodes[0] = new QuadTree<T>(level+1, Rectangle.create(x + subWidth, y, subWidth, subHeight));
        nodes[1] = new QuadTree<T>(level+1, Rectangle.create(x, y, subWidth, subHeight));
        nodes[2] = new QuadTree<T>(level+1, Rectangle.create(x, y + subHeight, subWidth, subHeight));
        nodes[3] = new QuadTree<T>(level+1, Rectangle.create(x + subWidth, y + subHeight, subWidth, subHeight));
    }
    
    private int getIndex(@NotNull T entity) {
        Rectangle pRect = entity.getBounds();
        int index = -1;
        double verticalMidpoint = bounds.getAnchor().x() + (bounds.getDimensions().x() / 2);
        double horizontalMidpoint = bounds.getAnchor().y() + (bounds.getDimensions().y() / 2);
        
        boolean topQuadrant =
                (pRect.getAnchor().y() < horizontalMidpoint
                 && pRect.getAnchor().y() + pRect.getDimensions().y() < horizontalMidpoint);
        boolean bottomQuadrant = (pRect.getAnchor().y() > horizontalMidpoint);
        
        if (pRect.getAnchor().x() < verticalMidpoint
            && pRect.getAnchor().x() + pRect.getDimensions().x() < verticalMidpoint) {
            if (topQuadrant) {
                index = 1;
            }
            else if (bottomQuadrant) {
                index = 2;
            }
        }
        else if (pRect.getAnchor().x() > verticalMidpoint) {
            if (topQuadrant) {
                index = 0;
            }
            else if (bottomQuadrant) {
                index = 3;
            }
        }
        
        return index;
    }
    
    public void insert(T entity) {
        if (nodes[0] != null) {
            int index = getIndex(entity);
            
            if (index != -1) {
                nodes[index].insert(entity);
                return;
            }
        }
        
        objects.add(entity);
        
        if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
            if (nodes[0] == null) {
                split();
            }
            
            int i = 0;
            while (i < objects.size()) {
                int index = getIndex(objects.get(i));
                if (index != -1) {
                    nodes[index].insert(objects.remove(i));
                }
                else {
                    i++;
                }
            }
        }
    }
    
    public List<T> retrieve(List<T> returnObjects, T entity) {
        int index = getIndex(entity);
        if (index != -1 && nodes[0] != null) {
            nodes[index].retrieve(returnObjects, entity);
        }
        
        returnObjects.addAll(objects);
        
        return returnObjects;
    }
    
    public List<T> retrieve(T entity) {
        return this.retrieve(new ArrayList<>(), entity);
    }
}
