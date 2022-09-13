package de.visparu.engine2d;

import de.visparu.engine2d.context.DefaultGameContext;
import de.visparu.engine2d.context.GameContext;
import de.visparu.engine2d.entities.DefaultEntityPool;
import de.visparu.engine2d.framework.SerialGameFramework;
import de.visparu.engine2d.gui.SwingGameWindow;
import de.visparu.engine2d.util.collision.QuadTreeCollisionHandler;
import org.jetbrains.annotations.NotNull;

public class Engine2D {
    public static @NotNull GameContext defaultEngine() {
        GameContext context = DefaultGameContext.builder()
                                                .withFramework(SerialGameFramework.builder()
                                                                                  .build())
                                                .withWindow(SwingGameWindow.builder()
                                                                           .build())
                                                .withEntityPool(DefaultEntityPool.builder()
                                                                                 .build())
                                                .withCollisionHandler(QuadTreeCollisionHandler.builder()
                                                                                              .build())
                                                .build();
        context.initialize();
        context.getFramework()
               .start();
        return context;
    }
}
