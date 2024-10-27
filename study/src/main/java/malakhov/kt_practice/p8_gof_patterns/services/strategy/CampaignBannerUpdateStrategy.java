package malakhov.kt_practice.p8_gof_patterns.services.strategy;

import org.apache.commons.lang3.tuple.Pair;
import malakhov.kt_practice.p8_gof_patterns.domain.EventResourceType;
import malakhov.kt_practice.p8_gof_patterns.domain.EventType;
import malakhov.kt_practice.p8_gof_patterns.domain.ScheduledEvent;
import malakhov.kt_practice.p8_gof_patterns.domain.Status;
import malakhov.kt_practice.p8_gof_patterns.domain.campaign.Campaign;
import malakhov.kt_practice.p8_gof_patterns.repository.CampaignRepository;
import malakhov.kt_practice.p8_gof_patterns.repository.ScheduledEventRepository;
import malakhov.kt_practice.p8_gof_patterns.utils.Constants;

public class CampaignBannerUpdateStrategy extends AbstractEventStrategy {

    CampaignRepository campaignRepository;
    ScheduledEventRepository scheduledEventRepository;

    public CampaignBannerUpdateStrategy(CampaignRepository campaignRepository,
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
            String banner = (String) event.getProperties().get(Constants.BANNER);
            if (banner == null) {
                throw new RuntimeException("No banner present");
            }
            campaign.setBanner(banner);
            campaignRepository.save(campaign);
            event.setStatus(Status.COMPLETED);
        } catch (Exception e) {
            event.setStatus(Status.FAILED);
        }
        return scheduledEventRepository.save(event);
    }

    @Override
    public Pair<EventType, EventResourceType> getType() {
        return Pair.of(EventType.UPDATE_BANNER, EventResourceType.CAMPAIGN);
    }
}
