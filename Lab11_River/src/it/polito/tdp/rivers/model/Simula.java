package it.polito.tdp.rivers.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;


public class Simula {
	
	private PriorityQueue<Evento> coda ;
	private Model model;
	private River r;
	private double K;
	private double Q;
	private double C;
	private double fOutMin;
	private List<Double> Ctot=new ArrayList<Double>();
	private double media=0.0;
	private int giornatePerse=0;
	private int tracimazioni=0;

	public Simula(Model model,River r, double k) {
		this.model=model;
		this.coda = new PriorityQueue<Evento>() ;
		coda.addAll(model.getEventiByRiver(r));//, k));
		this.K=k;
		this.r=r;
		Q=K*86400*r.mediaFlow()*30;
		C=Q/2;
		fOutMin=0.8*r.mediaFlow()*86400;
	}
	
	public void run(){
		Evento e;
		while((e=coda.poll())!=null){
			switch(e.getTipo()){
			
			case ADD_FLOW:
				/*e.getBacino().aggiunta(e.getFlow());
				giornatePerse=e.getBacino().getGiorniPersi();
				media=e.getBacino().getMedia();
				tracimazioni=e.getBacino().getTracimazioni();*/
				this.aggiunta(e.getFlow());
				break;
			default:
				throw new RuntimeException("Evento sconosciuto") ;
			}
		}
	}

	public int getGiornatePerse() {
		return giornatePerse;
	}

	public double getMedia() {
		for(Double d:Ctot){
			media+=d;
		}
		media/=Ctot.size();
		return media;
	}
	public int getTracimazioni(){
		return tracimazioni;
	}
	
	public double flussoUscita(){
		double f=fOutMin;
		Random random=new Random();
		if((random.nextInt(101))>=95)
			f=f*10;
		return f;
	}
	
	public void aggiunta(double flow){
		double flusso=flow-this.flussoUscita();
		if(flusso>=0){
			if((C+flusso)<=Q){
				C+=flow;
				Ctot.add(C);
				System.out.println("C quando ci sta:" + C);
				return;
			}
			if(C==Q){
				//non aggiungi nulla
				tracimazioni++;
				Ctot.add(C);
				System.out.println("C quando non ci sta:" + C);
				return;
				
			}else{
				//aggiungi quanto ci sta
				tracimazioni++;
				C+=(Q-C);
				Ctot.add(C);
				System.out.println("C quando ci sta in parte:" + C);
				return;
			}
		}else{
			if((C+flusso)>=0){
				C=C+flusso;
				Ctot.add(C);
				System.out.println("C negativo ok:" + C);
				return;
			}else{
				C=0;
				//uscita non riuscita totalmente o in parte
				giornatePerse++;
				Ctot.add(C);
				System.out.println("C negativo altrimenti:" + C);
				return;
			}
		}
	}
	
}
