package ar.edu.unlam.pb1.dominio;

import java.util.Iterator;

public class Gimnasio {

	private Actividad[] actividades;
	private Cliente[] clientes;

	public Gimnasio(String nombre) {
		
		this.actividades = new Actividad[15];
		this.agregarActividades(); // :O
		this.clientes = new Cliente[1000];
		this.inicializarClientes();
	}

	public boolean iniciarSesion(int dni, String contrasenia) {
		
		boolean credencialesValidas = false;
		Cliente clienteBuscado = buscarClientePorDni(dni);

		if (clienteBuscado != null && clienteBuscado.getContrasenia().equals(contrasenia)) {
			credencialesValidas = true;
		}

		return credencialesValidas;
	}

	public boolean registrarCliente(Cliente cliente) {
		
		int indice = 0;
		boolean clienteRegistrado = false;

		while (indice < this.clientes.length && !clienteRegistrado) {
			

			if (this.clientes[indice] == null) {
				this.clientes[indice] = cliente;
				clienteRegistrado = true;
			}
			indice++;
		}

		return clienteRegistrado;
	}

	public Actividad buscarActividadPorId(int id) {
		

		Actividad actividadEncontrada = null;
		boolean seEncontro = false;
		int indice = 0;
		while (indice < this.actividades.length && !seEncontro) {
			if (this.actividades[indice] != null && this.actividades[indice].getId() == id) {
				actividadEncontrada = this.actividades[indice];
			}
			indice++;
		}

		return actividadEncontrada;
	}

	public Cliente buscarClientePorDni(int dni) {
		
		boolean existenClientesConEseDni = false;
		int indice = 0;
		Cliente clienteConDniIngresado = null;

		while (indice < this.clientes.length && !existenClientesConEseDni) {
			if (this.clientes[indice] != null && this.clientes[indice].getDni() == dni) {
				clienteConDniIngresado = this.clientes[indice];
				existenClientesConEseDni = true;
			}
			indice++;
		}

		return clienteConDniIngresado;
	}

	public boolean validarContrasenia(String contrasenia) {
		

		int contadorDeMayusculas = 0;
		boolean contraseniaValida = false, tieneDosCaracteresNumericosConsecutivos = false;

		if (contrasenia.length() >= 8) {
			for (int i = 0; i < contrasenia.length(); i++) {
				char caracter = contrasenia.charAt(i);
				if (Character.isUpperCase(caracter)) {
					contadorDeMayusculas++;
				}
				
			}
		}

		for (int j = 0; j < contrasenia.length() - 1; j++) {
			char caracterDigito = contrasenia.charAt(j);
			char siguienteCaracterDigito = contrasenia.charAt(j + 1);
			if (Character.isDigit(caracterDigito) && Character.isDigit(siguienteCaracterDigito)
					&& ((Character.getNumericValue(siguienteCaracterDigito) - 1) == Character
							.getNumericValue(caracterDigito))) {

				tieneDosCaracteresNumericosConsecutivos = true;

			} 

		}

		
		if (!tieneDosCaracteresNumericosConsecutivos && contadorDeMayusculas >= 2) {
			contraseniaValida = true;

		} else {
			System.out.println("\n Contrasenia invalida, revise las condiciones para generar una contrasenia. ");
		}

		return contraseniaValida;
	}

	public Cliente obtenerElClienteQueMenosActividadesRealizo() {
		

		Cliente clienteQueMenosActividadRealizo = this.clientes[0];
		int cantidadDeActividadRealizadaPorElClienteQueMenosRealizo = clienteQueMenosActividadRealizo
				.obtenerCantidadDeActividadesRealizadas();

		for (int i = 1; i < this.clientes.length; i++) {
			if (this.clientes[i] != null && cantidadDeActividadRealizadaPorElClienteQueMenosRealizo > this.clientes[i]
					.obtenerCantidadDeActividadesRealizadas()) {
				clienteQueMenosActividadRealizo = this.clientes[i];
			}
		}

		return clienteQueMenosActividadRealizo;

	}

	public Actividad[] obtenerActividadesOrdenadasPorCaloriasQueQuemaDescendente() {
		

		Actividad auxParaOrdenar = null;

		for (int i = 0; i < this.actividades.length; i++) {
			for (int j = 0; j < this.actividades.length - 1; j++) {
				if (this.actividades[j] != null && this.actividades[j + 1] != null
						&& this.actividades[j].getCaloriasQueQuema() < this.actividades[j + 1].getCaloriasQueQuema()) {
					auxParaOrdenar = this.actividades[j + 1];
					this.actividades[j + 1] = this.actividades[j];
					this.actividades[j] = auxParaOrdenar;
				}
			}
		}

		return this.actividades;
	}

	private void agregarActividades() {
		String nombre = "";
		TipoActividad tipoActividad;
		int valor = this.actividades.length / TipoActividad.values().length;
		int duracion = 30;
		double calorias = 400;

		for (int i = 0; i < this.actividades.length; i++) {

			if (i < valor) {
				nombre = TipoActividad.AEROBICO.toString();
				tipoActividad = TipoActividad.AEROBICO;
			} else if (i < (valor * 2)) {
				nombre = TipoActividad.FUNCIONAL.toString();
				tipoActividad = TipoActividad.FUNCIONAL;
				duracion = 60;
				calorias = 281;
			} else {
				nombre = TipoActividad.PESAS.toString();
				tipoActividad = TipoActividad.PESAS;
				duracion = 20;
				calorias = 450;
			}

			nombre += " " + (i + 1);

			this.actividades[i] = new Actividad(nombre, duracion, calorias, tipoActividad);
		}

	}

	private void inicializarClientes() {

		this.clientes[0] = new Cliente(32123659, "Maria", "Je1456M48");
		this.clientes[1] = new Cliente(41256395, "Ignacio", "Jm4154L48");
		this.clientes[0] = new Cliente(27589632, "Ines", "471RmoP6");
	}

	public Actividad[] getActividades() {
		return actividades;
	}

	public void setActividades(Actividad[] actividades) {
		this.actividades = actividades;
	}

}
