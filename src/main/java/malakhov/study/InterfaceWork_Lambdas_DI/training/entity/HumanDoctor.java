package malakhov.study.InterfaceWork_Lambdas_DI.training.entity;

import java.time.LocalDate;

public interface HumanDoctor {
    String name();
    LocalDate birthday();
    Human.Gender gender();
}
