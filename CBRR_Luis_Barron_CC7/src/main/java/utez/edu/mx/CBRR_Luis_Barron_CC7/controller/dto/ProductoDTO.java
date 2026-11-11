package utez.edu.mx.CBRR_Luis_Barron_CC7.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductoDTO {
    @NotBlank
    private String nombre;
    @NotNull
    private String categoria;
    @Positive
    private Double precio;
}
