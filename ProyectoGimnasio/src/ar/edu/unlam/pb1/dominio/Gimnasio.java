package ar.edu.unlam.pb1.dominio;

import java.util.Iterator;

public class Gimnasio {

	private Actividad[] actividades;
	private Cliente[] clientes;

	public Gimnasio(String nombre) {
		// TODO: se debe adminitar hasta 10000 clientes
		this.actividades = new Actividad[15];
		this.agregarActividades(); // :O
		this.clientes = new Cliente[1000];
		this.inicializarClientes();
	}

	public boolean iniciarSesion(int dni, String contrasenia) {
		// TODO: obtiene un cliente por su dni y, si existe, verifica que la contrasenia
		// sea la suministrada. Devuelve verdadero en caso de que las credenciales sean
		// validas.
		boolean credencialesValidas = false;
		Cliente clienteBuscado = buscarClientePorDni(dni);

		if (clienteBuscado != null && clienteBuscado.getContrasenia().equals(contrasenia)) {
			credencialesValidas = true;
		}

		return credencialesValidas;
	}

	public boolean registrarCliente(Cliente cliente) {
		// TODO: se debe registrar el cliente, agregandolo a los clientes del gimnasio,
		// solo en caso de que el dni del cliente suministrado no exista entre los
		// clientes actuales. Devuelve verdadero en caso de exito.

		int indice = 0;
		boolean clienteRegistrado = false;

		while (indice < this.clientes.length && !clienteRegistrado) {
			// COMO OBTENGO EL DNI DE UN CLIENTE QUE RECIEN ME ENTRA POR PARAMETRO ?

			if (this.clientes[indice] == null) {
				this.clientes[indice] = cliente;
				clienteRegistrado = true;
			}
			indice++;
		}

		return clienteRegistrado;
	}

	public Actividad buscarActividadPorId(int id) {
		// TODO: Buscar entre las actividades del gimnasio alguna que aplique con el id
		// suministrado y devolverla

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
		// TODO: Revisa entre los clientes del gimnasio, si algun posee el dni indicado
		// y lo devuelve.

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
		// TODO: Devuelve verdadero en caso de que la contrasenia contenga: 2 o mas
		// mayusculas, un largo minimo de 8 caracteres y, si tiene numeros y estan
		// juntos, no pueden
		// ser consecutivos. Ejemplo valido: PassWord77 - Ejemplos invalidos:
		// PassWord123 o PassWord234 o PassWord345
		// -> notar que los numeros consecutivos son: 1 y 2 o 2 y 3, etc.

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

		// System.out.println("\n Contrasenia valida!");
		// contraseniaValida = true;

		if (!tieneDosCaracteresNumericosConsecutivos && contadorDeMayusculas >= 2) {
			contraseniaValida = true;

		} else {
			System.out.println("\n Contrasenia invalida, revise las condiciones para generar una contrasenia. ");
		}

		return contraseniaValida;
	}

	public Cliente obtenerElClienteQueMenosActividadesRealizo() {
		// TODO: devuelve el cliente que menos actividades realizo

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
		// TODO: Ordenar las actividades de forma descendente por la cantidad de
		// calorias que se queman y devolverlas

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
