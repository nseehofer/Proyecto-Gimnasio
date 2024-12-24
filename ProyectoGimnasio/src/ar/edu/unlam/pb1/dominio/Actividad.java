package ar.edu.unlam.pb1.dominio;

public class Actividad {
	private static int siguienteId = 1;
	private int id, duracion;
	private TipoActividad tipoActividad;
	private double caloriasQueQuema;
	private String nombre;

	public Actividad(String nombre, int duracion, double caloriasQueQuema, TipoActividad tipoActividad) {
		this.id = siguienteId++;
		this.nombre = nombre;
		this.duracion = duracion;
		this.caloriasQueQuema = caloriasQueQuema;
		this.tipoActividad = tipoActividad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public TipoActividad getTipoActividad() {
		return tipoActividad;
	}

	public void setTipoActividad(TipoActividad tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	public double getCaloriasQueQuema() {
		return caloriasQueQuema;
	}

	public void setCaloriasQueQuema(double caloriasQueQuema) {
		this.caloriasQueQuema = caloriasQueQuema;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Actividad [id=" + id + ", duracion=" + duracion + ", tipoActividad=" + tipoActividad
				+ ", caloriasQueQuema=" + caloriasQueQuema + ", nombre=" + nombre + "]";
	}

}
