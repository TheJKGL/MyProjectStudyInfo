package malakhov.kt_practice.p8_gof_patterns.services.strategy;

import malakhov.kt_practice.p8_gof_patterns.domain.ScheduledEvent;
import malakhov.kt_practice.p8_gof_patterns.domain.Status;

public abstract class AbstractEventStrategy implements EventStrategy {

    @Override
    public ScheduledEvent process(ScheduledEvent event) {
        if (event.getStatus() == Status.FAILED && event.getVersion() > 10) {
            event.setStatus(Status.TERMINATED);
        } else {
            event.setStatus(Status.ONGOING);
        }
        return event;
    }
}
