package malakhov.kt_practice.p8_gof_patterns.services;

import malakhov.kt_practice.p8_gof_patterns.domain.EventResourceType;
import malakhov.kt_practice.p8_gof_patterns.domain.EventType;
import malakhov.kt_practice.p8_gof_patterns.domain.ScheduledEvent;
import malakhov.kt_practice.p8_gof_patterns.services.strategy.EventStrategy;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduledEventProcessor {

    private final Map<Pair<EventType, EventResourceType>, EventStrategy> strategiesMap = new HashMap<>();

    public ScheduledEventProcessor(List<EventStrategy> eventStrategies) {
        for (EventStrategy eventStrategy : eventStrategies) {
            strategiesMap.put(eventStrategy.getType(), eventStrategy);
        }
    }

    public void processEvent(ScheduledEvent event) {
        strategiesMap.get(Pair.of(event.getEventType(), event.getResourceType()))
                .process(event);
    }
}
