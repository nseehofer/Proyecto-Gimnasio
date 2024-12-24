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
		
		this.dni = dni;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.actividades = new Actividad[1000];
	}

	public void realizarActividad(Actividad actividad) {
		
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
