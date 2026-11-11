package utez.edu.mx.CBRR_Luis_Barron_CC7.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoDTO {
    @NotNull
    @NotBlank
    private String nombreCliente;
}
