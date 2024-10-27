package malakhov.kt_practice.p8_gof_patterns;

import malakhov.kt_practice.p8_gof_patterns.domain.ScheduledEvent;
import malakhov.kt_practice.p8_gof_patterns.domain.campaign.Campaign;
import malakhov.kt_practice.p8_gof_patterns.domain.campaign.CampaignStatus;
import malakhov.kt_practice.p8_gof_patterns.repository.CampaignRepository;
import malakhov.kt_practice.p8_gof_patterns.repository.ScheduledEventRepository;
import malakhov.kt_practice.p8_gof_patterns.services.CampaignService;
import malakhov.kt_practice.p8_gof_patterns.services.ScheduledEventProcessor;
import malakhov.kt_practice.p8_gof_patterns.services.strategy.CampaignBannerUpdateStrategy;
import malakhov.kt_practice.p8_gof_patterns.services.strategy.CampaignStatusUpdateStrategy;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

public class Application {

    public static void main(String[] args) {
        var random = new Random();

        var campaignRepository = new CampaignRepository();
        var scheduledEventRepository = new ScheduledEventRepository();
        var campaignService = new CampaignService(scheduledEventRepository);

        List<Campaign> campaigns = campaignRepository.getAll();

        System.out.println("======================================");
        campaigns.forEach(System.out::println);
        System.out.println("======================================\n\n");

        //Configuration
        campaignService.updateStatus(campaigns.get(random.nextInt(campaigns.size())), CampaignStatus.ONGOING);
        campaignService.updateStatus(campaigns.get(random.nextInt(campaigns.size())), CampaignStatus.FINISHED);
        campaignService.scheduleUpdateBanner(campaigns.get(random.nextInt(campaigns.size())), Instant.now().plus(random.nextInt(1000), ChronoUnit.SECONDS));

        //Since this point you need to provide your implementation
        CampaignBannerUpdateStrategy campaignBannerUpdateStrategy = new CampaignBannerUpdateStrategy(campaignRepository, scheduledEventRepository);
        CampaignStatusUpdateStrategy campaignStatusUpdateStrategy = new CampaignStatusUpdateStrategy(campaignRepository, scheduledEventRepository);

        ScheduledEventProcessor scheduledEventProcessor = new ScheduledEventProcessor(List.of(campaignBannerUpdateStrategy, campaignStatusUpdateStrategy));
        for (ScheduledEvent scheduledEvent : scheduledEventRepository.getAll()) {
            scheduledEventProcessor.processEvent(scheduledEvent);
        }

        System.out.println("======================================");
        campaigns.forEach(System.out::println);
        System.out.println("======================================");
    }
}
