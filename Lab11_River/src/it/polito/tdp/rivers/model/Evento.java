package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Evento implements Comparable<Evento>{

	public enum TipoEvento{
		ADD_FLOW
	}
	
	private LocalDate date;
	private TipoEvento tipo;
	private double flow;
	//private Bacino bacino;
	
	
	public Evento(LocalDate date, TipoEvento tipo,double flow){//, Bacino bacino) {
		super();
		this.date = date;
		this.tipo = tipo;
		this.flow=flow;
		//this.bacino = bacino;
	}


	public LocalDate getDate() {
		return date;
	}


	public TipoEvento getTipo() {
		return tipo;
	}

	public double getFlow() {
		return flow;
	}


	/*public Bacino getBacino() {
		return bacino;
	}*/


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//result = prime * result + ((bacino == null) ? 0 : bacino.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		/*if (bacino == null) {
			if (other.bacino != null)
				return false;
		} else if (!bacino.equals(other.bacino))
			return false;*/
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}


	@Override
	public int compareTo(Evento o) {
		return this.getDate().compareTo(o.getDate());
	}
	
	
	
	
}
