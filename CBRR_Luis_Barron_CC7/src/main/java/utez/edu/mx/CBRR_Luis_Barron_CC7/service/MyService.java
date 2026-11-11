package utez.edu.mx.CBRR_Luis_Barron_CC7.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import utez.edu.mx.CBRR_Luis_Barron_CC7.exception.CustomException;
import utez.edu.mx.CBRR_Luis_Barron_CC7.model.EstadoPedido;
import utez.edu.mx.CBRR_Luis_Barron_CC7.model.Pedido;
import utez.edu.mx.CBRR_Luis_Barron_CC7.model.Producto;
import utez.edu.mx.CBRR_Luis_Barron_CC7.repository.PedidoRepository;
import utez.edu.mx.CBRR_Luis_Barron_CC7.repository.ProductoRepository;

import java.util.List;

@Service
public class MyService {
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    public MyService(PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }


    @Transactional
    public Pedido CrearPedido(Long id, String nombre, Integer cantidad){
        Producto producto = productoRepository.findById(id).orElseThrow(()-> new CustomException("El producto no Existe."));
        if (!producto.isActivo()){
            throw new CustomException("No esta activo");
        }
        if (cantidad<0){
            throw new CustomException("La cantidad debe ser mayor a 0");
        }

        if (producto.getStock() < 0){
            throw new CustomException("No hay suficiente stock");
        }

        Double precioActual = producto.getPrecio();
        Double totalActual = precioActual * cantidad;

        Pedido pedido = new Pedido();
        cantidad = cantidad - producto.getStock();
        pedido.setNombreCliente(nombre);
        pedido.setCantidad(cantidad);
        pedido.setPrecioUnitario(producto.getPrecio());
        pedido.setTotal(totalActual);
        pedido.setEstado(EstadoPedido.PENDIENTE);
        pedido.setProducto(producto);
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido CancelarPedido(Long id){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(
                ()-> new CustomException("El pedido no Existe."));
        Producto producto = productoRepository.findById(pedido.getProducto().getId_producto()).orElseThrow(
                ()-> new CustomException("El producto no Existe."));


        if (pedido.getEstado() != EstadoPedido.PENDIENTE){
            throw new CustomException("Solo se pueden pedidos en estado pendiente");
        }

        pedido.setEstado(EstadoPedido.CANCELADO);
        producto.setStock(  producto.getStock() + pedido.getCantidad());
        productoRepository.save(producto);

        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido ProcesarPedido(Long id){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(
                ()-> new CustomException("El pedido no Existe."));

        if (pedido.getEstado() != EstadoPedido.PENDIENTE){
            throw new CustomException("Solo se pueden pedidos en estado pendiente");
        }

        pedido.setEstado(EstadoPedido.PROCESADO);
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Producto DesactivarProducto(Long id){

        Producto producto = productoRepository.findById(id).orElseThrow(
                ()-> new CustomException("El producto no Existe."));

        pedidoRepository.findByEstado(EstadoPedido.PENDIENTE);
        producto.setActivo(false);
        return productoRepository.save(producto);
    }

    @Transactional
    public Pedido CancelarPedidosPendientes(Long id){
        Pedido p = new Pedido();
        Producto producto = productoRepository.findById(id).orElseThrow(
                ()-> new CustomException("El producto no Existe."));

         List<Pedido> pedidos = pedidoRepository.findByEstadoAndId(id,EstadoPedido.PENDIENTE);
         return pedidoRepository.save(p);
    }

    @Transactional()
    public List<Producto> ListarProductor(){
        List<Producto> activos = productoRepository.findByAcivos(true);
        return activos;
    }


}
