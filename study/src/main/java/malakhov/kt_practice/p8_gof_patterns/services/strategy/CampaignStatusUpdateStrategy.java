package malakhov.kt_practice.p8_gof_patterns.services.strategy;

import malakhov.kt_practice.p8_gof_patterns.domain.EventResourceType;
import malakhov.kt_practice.p8_gof_patterns.domain.EventType;
import malakhov.kt_practice.p8_gof_patterns.domain.ScheduledEvent;
import malakhov.kt_practice.p8_gof_patterns.domain.Status;
import malakhov.kt_practice.p8_gof_patterns.domain.campaign.Campaign;
import malakhov.kt_practice.p8_gof_patterns.domain.campaign.CampaignStatus;
import malakhov.kt_practice.p8_gof_patterns.repository.CampaignRepository;
import malakhov.kt_practice.p8_gof_patterns.repository.ScheduledEventRepository;
import malakhov.kt_practice.p8_gof_patterns.utils.Constants;
import org.apache.commons.lang3.tuple.Pair;

public class CampaignStatusUpdateStrategy extends AbstractEventStrategy {

    CampaignRepository campaignRepository;
    ScheduledEventRepository scheduledEventRepository;

    public CampaignStatusUpdateStrategy(CampaignRepository campaignRepository,
                                        ScheduledEventRepository scheduledEventRepository) {
        this.campaignRepository = campaignRepository;
        this.scheduledEventRepository = scheduledEventRepository;
    }

    @Override
    public ScheduledEvent process(ScheduledEvent event) {
        super.process(event);
        try {
            Campaign campaign = campaignRepository.getCampaignByName(event.getResourceName())
                    .orElseThrow(() -> new RuntimeException("No campaign present"));
            CampaignStatus status = (CampaignStatus) event.getProperties().get(Constants.CAMPAIGN_STATUS);
            if (status == null) {
                throw new RuntimeException("No CampaignStatus present");
            }
            campaign.setStatus(status);
            campaignRepository.save(campaign);
            event.setStatus(Status.COMPLETED);
        } catch (Exception e) {
            event.setStatus(Status.FAILED);
        }
        return scheduledEventRepository.save(event);
    }

    @Override
    public Pair<EventType, EventResourceType> getType() {
        return Pair.of(EventType.UPDATE_STATUS, EventResourceType.CAMPAIGN);
    }
}
