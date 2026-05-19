package co.edu.uniquindio.poo.demo.Back;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class Taller {

    public String nombre, direccion;
    public Collection<Cliente> clientes;
    public Collection<Mecanico> mecanicos;
    public Collection<Factura> facturas;
    public Collection<Repuesto> respuestos;
    public Collection<Pago> pagos;
    public Collection<Vehiculo> vehiculos;
    public Collection<Orden> ordenes;
    public Collection<Servicio> servicios;

    public Taller(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.clientes = new LinkedList<>();
        this.mecanicos = new LinkedList<>();
        this.facturas = new LinkedList<>();
        this.respuestos = new LinkedList<>();
        this.pagos = new LinkedList<>();
        this.vehiculos = new LinkedList<>();
        this.ordenes = new LinkedList<>();
        this.servicios = new LinkedList<>();
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Collection<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Collection<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Collection<Mecanico> getMecanicos() {
        return mecanicos;
    }

    public void setMecanicos(Collection<Mecanico> mecanicos) {
        this.mecanicos = mecanicos;
    }

    public Collection<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(Collection<Factura> facturas) {
        this.facturas = facturas;
    }

    public Collection<Repuesto> getRespuestos() {
        return respuestos;
    }

    public void setRespuestos(Collection<Repuesto> respuestos) {
        this.respuestos = respuestos;
    }

    public Collection<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(Collection<Pago> pagos) {
        this.pagos = pagos;
    }

    public Collection<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(Collection<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public Collection<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(Collection<Orden> ordenes) {
        this.ordenes = ordenes;
    }

    public Collection<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(Collection<Servicio> servicios) {
        this.servicios = servicios;
    }

    public boolean verificarCliente(String id) {
        for (Cliente c : clientes) {
            if (id.equals(c.getDocumento())) {
                return false;
            }
        }
        return true;
    }

    public boolean agregarCliente(Cliente cliente) {
        boolean centinela = false;
        boolean esUnico = verificarCliente(cliente.getDocumento());
        if (esUnico) {
            clientes.add(cliente);
            centinela = true;
            System.out.println("Cliente agregado exitosamente");
        } else {
            System.out.println("El Cliente ya existe");
        }
        return centinela;
    }


    public boolean eliminarCliente(Cliente cliente) {
        boolean centinela = false;
        boolean esUnico = !verificarCliente(cliente.getDocumento());
        if (esUnico) {
            clientes.remove(cliente);
            centinela = true;
            System.out.println("Cliente eliminado exitosamente");
        } else {
            System.out.println("El cliente ya existe");
        }
        return centinela;
    }

    public boolean actualizarCliente(String cedula, Cliente actualizado) {
        boolean centinela = false;

        for (Cliente c : clientes) {
            if (c.getDocumento().equals(cedula)) {
                c.setDocumento(actualizado.getDocumento());
                c.setNombre(actualizado.getNombre());
                c.setDireccion(actualizado.getDireccion());
                c.setEstado(actualizado.getEstado());
                c.setTelefonono(actualizado.getTelefonono());
                c.setEmail(actualizado.getEmail());
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean verificarMecanico(String id) {
        for (Mecanico m : mecanicos) {
            if (id.equals(m.getDocumento())) {
                return false;
            }
        }
        return true;
    }

    public boolean agregarMecanico(Mecanico mecanico) {
        boolean centinela = false;
        boolean esUnico = verificarMecanico(mecanico.getDocumento());
        if (esUnico) {
            mecanicos.add(mecanico);
            centinela = true;
            System.out.println("Mecanico agregado exitosamente");
        } else {
            System.out.println("El Mecanico ya existe");
        }
        return centinela;
    }


    public boolean eliminarMecanico(Mecanico mecanico) {
        boolean centinela = false;
        boolean esUnico = !verificarMecanico(mecanico.getDocumento());
        if (esUnico) {
            mecanicos.remove(mecanico);
            centinela = true;
            System.out.println("Mecanico eliminado exitosamente");
        } else {
            System.out.println("El mecanico ya existe");
        }
        return centinela;
    }

    public boolean actualizarMecanico(String cedula, Mecanico actualizado) {
        boolean centinela = false;

        for (Mecanico m : mecanicos) {
            if (m.getDocumento().equals(cedula)) {
                m.setDocumento(actualizado.getDocumento());
                m.setNombre(actualizado.getNombre());
                m.setDireccion(actualizado.getDireccion());
                m.setEstado(actualizado.getEstado());
                m.setTelefonono(actualizado.getTelefonono());
                m.setEmail(actualizado.getEmail());
                centinela = true;
                break;
            }
        }
        return centinela;
    }


    public boolean verificarVehiculo(String id) {
        for (Vehiculo v : vehiculos) {
            if (id.equals(v.getPlaca())) {
                return false;
            }
        }
        return true;
    }

    public boolean agregarVehiculo(Vehiculo vehiculo) {
        boolean centinela = false;
        boolean esUnico = verificarVehiculo(vehiculo.getPlaca());
        if (esUnico) {
            vehiculos.add(vehiculo);
            centinela = true;
            System.out.println("vehiculo agregado exitosamente");
        } else {
            System.out.println("El vehiculo ya existe");
        }
        return centinela;
    }


    public boolean eliminarVehiculo(Vehiculo vehiculo) {
        boolean centinela = false;
        boolean esUnico = !verificarVehiculo(vehiculo.getPlaca());
        if (esUnico) {
            vehiculos.remove(vehiculo);
            centinela = true;
            System.out.println("vehiculo eliminado exitosamente");
        } else {
            System.out.println("El vehiculo ya existe");
        }
        return centinela;
    }

    public boolean actualizarVehiculo(String cedula, Vehiculo actualizado) {
        boolean centinela = false;

        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equals(cedula)) {
                v.setPlaca(actualizado.getPlaca());
                v.setDuenio(actualizado.getDuenio());
                v.setKilometraje(actualizado.getKilometraje());
                v.setTipo(actualizado.getTipo());
                v.setModelo(actualizado.getModelo());
                v.setMarca(actualizado.getMarca());
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean verificarPago(String id) {
        for (Pago p : pagos) {
            if (id.equals(p.getIdPago())) {
                return false;
            }
        }
        return true;
    }

    public boolean agregarPago(Pago pago) {
        boolean centinela = false;
        boolean esUnico = verificarPago(pago.getIdPago());
        if (esUnico) {
            pagos.add(pago);
            centinela = true;
            System.out.println("pago agregado exitosamente");
        } else {
            System.out.println("El pago ya existe");
        }
        return centinela;
    }


    public boolean eliminarPago(Pago pago) {
        boolean centinela = false;
        boolean esUnico = !verificarPago(pago.getIdPago());
        if (esUnico) {
            pagos.remove(pago);
            centinela = true;
            System.out.println("Pago eliminado exitosamente");
        } else {
            System.out.println("El pago ya existe");
        }
        return centinela;
    }

    public boolean actualizarPago(String cedula, Pago actualizado) {
        boolean centinela = false;

        for (Pago p : pagos) {
            if (p.getIdPago().equals(cedula)) {
                p.setIdPago(actualizado.getIdPago());
                p.setEstado(actualizado.getEstado());
                p.setFechaPago(actualizado.getFechaPago());
                p.setMetodo(actualizado.getMetodo());
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean verificarOrden(String id) {
        for (Orden o : ordenes) {
            if (id.equals(o.getIdOrden())) {
                return false;
            }
        }
        return true;
    }

    public boolean agregarOrden(Orden orden) {
        boolean centinela = false;
        boolean esUnico = verificarOrden(orden.getIdOrden());
        if (esUnico) {
            ordenes.add(orden);
            centinela = true;
            System.out.println("orden agregado exitosamente");
        } else {
            System.out.println("El orden ya existe");
        }
        return centinela;
    }


    public boolean eliminarOrden(Orden orden) {
        boolean centinela = false;
        boolean esUnico = !verificarOrden(orden.getIdOrden());
        if (esUnico) {
            ordenes.remove(orden);
            centinela = true;
            System.out.println("orden eliminado exitosamente");
        } else {
            System.out.println("El orden ya existe");
        }
        return centinela;
    }

    public boolean actualizarOrden(String cedula, Orden actualizado) {
        boolean centinela = false;

        for (Orden o : ordenes) {
            if (o.getIdOrden().equals(cedula)) {
                o.setIdOrden(actualizado.getIdOrden());
                o.setCliente(actualizado.getCliente());
                o.setFecha(actualizado.getFecha());
                o.setMecanicos(actualizado.getMecanicos());
                o.setVehiculo(actualizado.getVehiculo());
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean verificarRespuesto(String id) {
        for (Repuesto r : respuestos) {
            if (id.equals(r.getIdRepuesto())) {
                return false;
            }
        }
        return true;
    }

    public boolean agregarRespuesto(Repuesto respuesto) {
        boolean centinela = false;
        boolean esUnico = verificarRespuesto(respuesto.getIdRepuesto());
        if (esUnico) {
            respuestos.add(respuesto);
            centinela = true;
            System.out.println("respuesto agregado exitosamente");
        } else {
            System.out.println("El respuesto ya existe");
        }
        return centinela;
    }


    public boolean eliminarRespuesto(Repuesto respuesto) {
        boolean centinela = false;
        boolean esUnico = !verificarRespuesto(respuesto.getIdRepuesto());
        if (esUnico) {
            respuestos.remove(respuesto);
            centinela = true;
            System.out.println("respuesto eliminado exitosamente");
        } else {
            System.out.println("El respuesto ya existe");
        }
        return centinela;
    }

    public boolean actualizarRespuesto(String cedula, Repuesto actualizado) {
        boolean centinela = false;

        for (Repuesto r : respuestos) {
            if (r.getIdRepuesto().equals(cedula)) {
                r.setMarca(actualizado.getMarca());
                r.setNombre(actualizado.getNombre());
                r.setStock(actualizado.getStock());
                r.setIdRepuesto(actualizado.getIdRepuesto());
                r.setPrecio(actualizado.getPrecio());
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean verificarFactura(String id) {
        for (Factura f : facturas) {
            if (id.equals(f.getIdFactura())) {
                return false;
            }
        }
        return true;
    }

    public boolean agregarFactura(Factura factura) {
        boolean centinela = false;
        boolean esUnico = verificarFactura(factura.getIdFactura());
        if (esUnico) {
            facturas.add(factura);
            centinela = true;
            System.out.println("factura agregado exitosamente");
        } else {
            System.out.println("El factura ya existe");
        }
        return centinela;
    }


    public boolean eliminarFactura(Factura factura) {
        boolean centinela = false;
        boolean esUnico = !verificarFactura(factura.getIdFactura());
        if (esUnico) {
            facturas.remove(factura);
            centinela = true;
            System.out.println("factura eliminado exitosamente");
        } else {
            System.out.println("El factura ya existe");
        }
        return centinela;
    }

    public boolean actualizarFactura(String cedula, Factura actualizado) {
        boolean centinela = false;

        for (Factura c : facturas) {
            if (c.getIdFactura().equals(cedula)) {
                c.setIdFactura(actualizado.getIdFactura());
                c.setPago(actualizado.getPago());
                c.setServicio(actualizado.getServicio());
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean verificarServicio(String id) {
        for (Servicio s : servicios) {
            if (id.equals(s.getIdServicio())) {
                return false;
            }
        }
        return true;
    }

    public boolean agregarServicio(Servicio servicio) {
        boolean centinela = false;
        boolean esUnico = verificarServicio(servicio.getIdServicio());
        if (esUnico) {
            servicios.add(servicio);
            centinela = true;
            System.out.println("servicio agregado exitosamente");
        } else {
            System.out.println("El servicio ya existe");
        }
        return centinela;
    }


    public boolean eliminarServicio(Servicio servicio) {
        boolean centinela = false;
        boolean esUnico = !verificarServicio(servicio.getIdServicio());
        if (esUnico) {
            servicios.remove(servicio);
            centinela = true;
            System.out.println("servicio eliminado exitosamente");
        } else {
            System.out.println("El servicio ya existe");
        }
        return centinela;
    }

    public boolean actualizarServicio(String cedula, Servicio actualizado) {
        boolean centinela = false;

        for (Servicio c : servicios) {
            if (c.getIdServicio().equals(cedula)) {
                c.setIdServicio(actualizado.getIdServicio());
                c.setDescripcion(actualizado.getDescripcion());
                c.setEstadoServicio(actualizado.getEstadoServicio());
                c.setRepuestos(actualizado.getRepuestos());
                c.setPrecioManoObra(actualizado.getPrecioManoObra());
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public void finalizaServicio() {
        servicios.removeIf(s -> s.getEstadoServicio().equals(Servicio.EstadoServicio.Cancelado));
    }

    public int contarServiciosPorMecanico(String documentoMecanico) {
        int contador = 0;
        for (Orden s : ordenes) {
            for (Mecanico m : s.getMecanicos()) {
                if (m.getDocumento().equals(documentoMecanico)) {
                    contador++;break;
                }
            }
        }
        return contador;
    }






}
