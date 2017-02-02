package org.tetawex.tms.ecs.components;

import com.badlogic.ashley.core.Component;
import org.tetawex.tms.ecs.misc.stats.Stats;

/**
 * Created by tetawex on 28.01.17.
 */
public class StatsComponent implements Component {
    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }
    public StatsComponent(Stats stats) {
        setStats(stats);
    }

    private Stats stats;
}
