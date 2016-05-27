package it.polito.tdp.rivers.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bacino {

	private River river;
	private double Q;
	private double C;
	private double fOutMin;
	private List<Double> Ctot;
	private double media;
	private int giorniPersi;
	private int tracimazioni=0;
	
	public Bacino(River r,double K) {
		super();
		this.river = r;
		Q=r.mediaFlow()*86400*30*K;
		C=Q/2;
		fOutMin=0.8*r.mediaFlow()*86400;
		giorniPersi=0;
		Ctot=new ArrayList<Double>();
	}
	
	public void aggiunta(double flow){
		float uscita=(float)this.flussoUscita();
		float diff=(float)flow-uscita;
		System.out.println("DIFF: "+diff);
		if(diff>=0){
			if((C+diff)<=Q){
				C+=flow;
				Ctot.add(C);
				System.out.println("C quando ci sta:" + C);
				return;
			}
			if(C==Q){
				//nonn aggiungi nulla
				tracimazioni++;
				Ctot.add(C);
				System.out.println("C quando non ci sta:" + C);
				
			}else{
				//aggiungi quanto ci sta
				tracimazioni++;
				C+=(Q-C);
				Ctot.add(C);
				System.out.println("C quando ci sta in parte:" + C);
			}
		}else{
			diff=diff*(-1);
			if((C-diff)>=0){
				C=C-diff;
				Ctot.add(C);
				System.out.println("C negativo ok:" + C);
				return;
			}else{
				C=0;
				//uscita non riuscita totalmente o in parte
				giorniPersi++;
				Ctot.add(C);
				System.out.println("C negativo altrimenti:" + C);
			}
		}
		/*if((C+flow)<=Q){
			C+=flow;
			return;
		}
		if(C==Q){
			//nonn aggiungi nulla
		}else{
			//aggiungi quanto ci sta
			C+=(Q-C);
		}*/
	}
	public void uscita(){
		if((C-fOutMin-(0.05*10*fOutMin))>=0){
			C-=fOutMin-(0.05*10*fOutMin);
			return;
		}else{
			C=0;
			//uscita non riuscita totalmente o in parte
			giorniPersi++;
		}
	}
	
	public void aggiornaMedia(){
		Ctot.add(C);
	}

	public River getRiver() {
		return river;
	}

	public int getGiorniPersi() {
		return giorniPersi;
	}

	public int getTracimazioni(){
		return tracimazioni;
	}
	
	public double getMedia() {
		media=0;
		for(Double d:Ctot){
			media+=d;
		}
		return media/=Ctot.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((river == null) ? 0 : river.hashCode());
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
		Bacino other = (Bacino) obj;
		if (river == null) {
			if (other.river != null)
				return false;
		} else if (!river.equals(other.river))
			return false;
		return true;
	}
	public double flussoUscita(){
		double f=0.8*fOutMin;
		Random random=new Random();
		if((random.nextInt(101))>=95)
			f=f*10;
		return f;
	}
	
	
}
