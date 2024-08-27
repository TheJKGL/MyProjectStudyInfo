package malakhov.study.interface_work_lambdas_DI.training.entity;

import java.time.LocalDate;

public interface LicenceDoctor {
    LicenseForTreatment.KindOfActivity category();
    LocalDate fromDate();
    LocalDate expires();
}
