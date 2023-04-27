package servidorBase;

import java.time.LocalDate;

public class Pedido {
	
	private int id_pedido;
	private double pago;
	private LocalDate fecha;
	private int id_cliente;
	
	
	public Pedido(int id_pedido, double pago, LocalDate fecha, int id_cliente) {
		super();
		this.id_pedido = id_pedido;
		this.pago = pago;
		this.fecha = fecha;
		this.id_cliente = id_cliente;
	}


	public int getId_pedido() {
		return id_pedido;
	}


	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}


	public double getPago() {
		return pago;
	}


	public void setPago(double pago) {
		this.pago = pago;
	}


	public LocalDate getFecha() {
		return fecha;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	public int getId_cliente() {
		return id_cliente;
	}


	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}


	@Override
	public String toString() {
		return "Pedido [id_pedido=" + id_pedido + ", pago=" + pago + ", fecha=" + fecha + ", id_cliente=" + id_cliente
				+ "]";
	}
	
	
}
