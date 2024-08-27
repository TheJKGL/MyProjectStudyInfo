package malakhov.study.interface_work_lambdas_DI.training.entity;

import java.time.LocalDate;

public interface HumanDoctor {
    String name();
    LocalDate birthday();
    Human.Gender gender();
}
