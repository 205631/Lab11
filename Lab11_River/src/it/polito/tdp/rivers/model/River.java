package it.polito.tdp.rivers.model;

import java.util.ArrayList;
import java.util.List;

public class River {

	private List<Flow> flows;
	private String name;
	private int id;
	private double fMed;
	

	public River(int id) {
		this.id = id;
		fMed=0;
	}

	public River(int id, String name) {
		this.id = id;
		this.name = name;
		fMed=0;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFlows(List<Flow> flows) {
		this.flows = flows;
	}

	public List<Flow> getFlows() {
		if (flows == null)
			flows = new ArrayList<Flow>();
		return flows;
	}
	
	public double mediaFlow(){
		for(Flow f:flows){
			fMed+=f.getFlow();
		}
		return fMed/=flows.size();
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		River other = (River) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
