package malakhov.kt_practice.p8_gof_patterns.services;

import lombok.RequiredArgsConstructor;
import malakhov.kt_practice.p8_gof_patterns.domain.EventResourceType;
import malakhov.kt_practice.p8_gof_patterns.domain.EventType;
import malakhov.kt_practice.p8_gof_patterns.domain.ScheduledEvent;
import malakhov.kt_practice.p8_gof_patterns.domain.campaign.Campaign;
import malakhov.kt_practice.p8_gof_patterns.domain.campaign.CampaignStatus;
import malakhov.kt_practice.p8_gof_patterns.repository.ScheduledEventRepository;
import malakhov.kt_practice.p8_gof_patterns.utils.Constants;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@RequiredArgsConstructor
public class CampaignService {

    private final ScheduledEventRepository scheduledEventRepository;

    private final List<String> banners = List.of(
            "Just Do It",
            "I'm Lovin' It",
            "Think Different",
            "Because You're Worth It",
            "Taste the Rainbow",
            "Have a Break, Have a KitKat",
            "The Happiest Place on Earth",
            "Finger Lickin' Good",
            "Red Bull Gives You Wings",
            "It's in the Game"
    );
    private final Random random = new Random();


    public void updateStatus(Campaign campaign, CampaignStatus newStatus) {
        if (campaign.getStatus().equals(CampaignStatus.FINISHED)) {
            throw new RuntimeException("Campaign status changes is not allowed for campaign with status FINISHED");
        }

        if (newStatus.equals(CampaignStatus.ONGOING)) {
            scheduledEventRepository.save(
                    getScheduledEvent(EventType.UPDATE_STATUS, campaign.getName(), campaign.getStartDate())
                            .properties(Map.of(Constants.CAMPAIGN_STATUS, newStatus))
                            .build()
            );
        } else if (newStatus.equals(CampaignStatus.FINISHED)) {
            scheduledEventRepository.save(
                    getScheduledEvent(EventType.UPDATE_STATUS, campaign.getName(), campaign.getEndDate())
                            .properties(Map.of(Constants.CAMPAIGN_STATUS, newStatus))
                            .build()
            );
        } else {
            campaign.setStatus(newStatus);
        }
    }

    public void scheduleUpdateBanner(Campaign campaign, Instant at) {
        scheduledEventRepository.save(
                getScheduledEvent(EventType.UPDATE_BANNER, campaign.getName(), at)
                        .properties(Map.of(Constants.BANNER, banners.get(random.nextInt(banners.size()))))
                        .build()
        );
    }


    private ScheduledEvent.ScheduledEventBuilder getScheduledEvent(EventType eventType, String campaignName, Instant at) {
        return ScheduledEvent.builder()
                .id(UUID.randomUUID())
                .eventType(eventType)
                .resourceType(EventResourceType.CAMPAIGN)
                .resourceName(campaignName)
                .scheduledAt(at);
    }

}
