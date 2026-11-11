

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.CBRR_Luis_Barron_CC7.model.Pedido;
import utez.edu.mx.CBRR_Luis_Barron_CC7.model.Producto;
import utez.edu.mx.CBRR_Luis_Barron_CC7.service.MyService;

import java.util.List;

@RestController
@RequestMapping("/controlador")
@CrossOrigin("*")
public class MyController {

    private final MyService service;

    public MyController(MyService service) {
        this.service = service;
    }


    @PostMapping("/pedido/crear/{id}")
    public ResponseEntity<Pedido> crear(@PathVariable Long id,
                                        @RequestParam String nombre,
                                        @RequestParam Integer cantidad) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.CrearPedido(id, nombre, cantidad));
    }


    @PutMapping("/pedido/cancelar/{id}")
    public ResponseEntity<Pedido> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(service.CancelarPedido(id));
    }


    @PutMapping("/pedido/procesar/{id}")
    public ResponseEntity<Pedido> procesar(@PathVariable Long id) {
        return ResponseEntity.ok(service.ProcesarPedido(id));
    }


    @PutMapping("/producto/desactivar/{id}")
    public ResponseEntity<Producto> desactivarProducto(@PathVariable Long id) {
        return ResponseEntity.ok(service.DesactivarProducto(id));
    }


    @PutMapping("/pedido/cancelar-pendientes/{id}")
    public ResponseEntity<Pedido> cancelarPedidosPendientes(@PathVariable Long id) {
        return ResponseEntity.ok(service.CancelarPedidosPendientes(id));
    }


    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(service.ListarProductor());
    }
}