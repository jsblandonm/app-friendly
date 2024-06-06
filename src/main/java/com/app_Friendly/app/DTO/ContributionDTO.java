package com.app_Friendly.app.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContributionDTO {
    @NotNull(message = "El ID de la persona no puede ser nulo")
    private String peopleId;

    @NotNull(message = "El ID del grupo no puede ser nulo")
    private String groupId;

    @NotNull(message = "El monto de la contribución no puede ser nulo")
    private double amount;
}
