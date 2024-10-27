package malakhov.kt_practice.p8_gof_patterns.services.strategy;

import malakhov.kt_practice.p8_gof_patterns.domain.EventResourceType;
import malakhov.kt_practice.p8_gof_patterns.domain.EventType;
import malakhov.kt_practice.p8_gof_patterns.domain.ScheduledEvent;
import org.apache.commons.lang3.tuple.Pair;

public interface EventStrategy {

    ScheduledEvent process(ScheduledEvent event);

    Pair<EventType, EventResourceType> getType();

}
