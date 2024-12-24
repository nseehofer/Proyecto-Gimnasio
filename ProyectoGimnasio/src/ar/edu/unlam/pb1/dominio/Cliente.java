package ar.edu.unlam.pb1.dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Cliente {

	private String contrasenia;
	private String nombre;
	private int dni;
	private Actividad[] actividades;

	public Cliente(int dni, String nombre, String contrasenia) {
		// TODO: El cliente podra realizar hasta 10000 actividades
		this.dni = dni;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.actividades = new Actividad[1000];
	}

	public void realizarActividad(Actividad actividad) {
		// TODO: Agrega una actividad a las actividades del cliente
		boolean actividadAgregada = false;
		int indice = 0;

		while (indice < this.actividades.length && !actividadAgregada) {
			if (this.actividades[indice] == null) {
				this.actividades[indice] = actividad;
				actividadAgregada = true;
			}
			indice++;
		}

	}

	public boolean eliminarActividadPorId(int id) {
		// TODO: busca una actividad por su id y en caso de existir, la elimina.
		boolean actividadEliminada = false;
		int indice = 0;
		while (indice < this.actividades.length && !actividadEliminada) {
			if (this.actividades[indice] != null && this.actividades[indice].getId() == id) {
				this.actividades[indice] = null;
				actividadEliminada = true;
			}
			indice++;
		}
		return actividadEliminada;
	}

	public int obtenerCantidadDeActividadesRealizadas() {
		// TODO: revisar cuantas actividades realizo el cliente y devolver el valor
		// correspondiente
		boolean actividadRealizada = false;
		int indice = 0, acumuladorDeActividadesRealizadas = indice - 1;

		while (indice < this.actividades.length && !actividadRealizada) {
			if (this.actividades[indice] == null) {
				actividadRealizada = true;
			}
			indice++;
		}

		return acumuladorDeActividadesRealizadas;
	}

	public double obtenerCantidadDeCaloriasQuemadasPorActividadDeTipo(TipoActividad tipoActividad) {
		// TODO: Obtener la cantidad de calorias quemadas por el cliente al realizar
		// actividades de un tipo determinado

		int indice = 0;
		boolean noRealizoActividad = false;
		double acumuladorDeCaloriasQuemadasPorActividadDelTipo = 0;

		while (indice < this.actividades.length && !noRealizoActividad) {
			if (this.actividades == null) {
				noRealizoActividad = true;
			}
			acumuladorDeCaloriasQuemadasPorActividadDelTipo += this.actividades[indice].getCaloriasQueQuema();
			indice++;
		}

		return acumuladorDeCaloriasQuemadasPorActividadDelTipo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public Actividad[] getActividades() {
		return actividades;
	}

	public void setActividades(Actividad[] actividades) {
		this.actividades = actividades;
	}

	@Override
	public String toString() {
		return "Cliente [contrasenia=" + contrasenia + ", nombre=" + nombre + ", dni=" + dni + ", actividades="
				+ Arrays.toString(actividades) + "]";
	}

}
