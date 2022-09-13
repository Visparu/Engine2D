package de.visparu.engine2d.framework;

import de.visparu.engine2d.context.GameContextComponent;

public interface GameFramework extends GameContextComponent {
    void start();
    
    void stop();
}
