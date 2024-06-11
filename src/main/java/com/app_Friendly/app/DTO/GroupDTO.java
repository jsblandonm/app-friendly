package com.app_Friendly.app.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GroupDTO {

    @NotBlank(message = "El nombre del grupo no puede estar vacio")
    private String name;

    private String ownerId;

}
