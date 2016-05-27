package it.polito.tdp.rivers.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.event.DocumentEvent.EventType;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {

	private RiversDAO dao=new RiversDAO();
	private List<River> rivers=new ArrayList<>();

	public Model() {
		rivers.addAll(dao.getAllRivers());
		for(River r:rivers){
			r.setFlows(this.getFlowsByRiver(r));
		}
	}

	public List<River> getRivers() {
		return rivers;
	}
	
	public List<Flow> getFlowsByRiver(River r){
		
		List<Flow> flows=new ArrayList<Flow>();
		flows.addAll(dao.getFlowsByRiver(r));
		Collections.sort(flows);
		r.setFlows(flows);
		return flows;
	}
	
	public List<Evento> getEventiByRiver(River r){//,double k){
		List<Evento> eventi=new ArrayList<>();
		for(Flow f:r.getFlows()){
			eventi.add(new Evento(f.getDay(),Evento.TipoEvento.ADD_FLOW,f.getFlow()));//,new Bacino(r,k)));
		}
		return eventi;
	}
	
}
