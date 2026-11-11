package utez.edu.mx.CBRR_Luis_Barron_CC7.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Producto")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;
    private String nombre;
    private String categoria;
    private Double precio;
    private Integer stock;
    private boolean activo;

}
