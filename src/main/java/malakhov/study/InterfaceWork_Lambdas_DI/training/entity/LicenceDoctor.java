package malakhov.study.InterfaceWork_Lambdas_DI.training.entity;

import java.time.LocalDate;

public interface LicenceDoctor {
    LicenseForTreatment.KindOfActivity category();
    LocalDate fromDate();
    LocalDate expires();
}
