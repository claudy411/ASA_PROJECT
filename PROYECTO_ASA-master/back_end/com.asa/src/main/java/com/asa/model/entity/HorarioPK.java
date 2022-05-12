package com.asa.model.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class HorarioPK implements Serializable {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn()
	private Evento evento;

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(evento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HorarioPK other = (HorarioPK) obj;
		return Objects.equals(evento, other.evento);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1353834236346840988L;
}
