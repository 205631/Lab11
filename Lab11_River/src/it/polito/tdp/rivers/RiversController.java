package it.polito.tdp.rivers;


import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.Simula;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RiversController {

	private Model model;
	
	public void setModel(Model model){
		this.model=model;
		boxRiver.getItems().addAll(model.getRivers());
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<River> boxRiver;

    @FXML
    private TextField txtStartDate;

    @FXML
    private TextField txtEndDate;

    @FXML
    private TextField txtNumMeasurements;

    @FXML
    private TextField txtFMed;

    @FXML
    private TextField txtK;

    @FXML
    private Button btnSimula;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCompila(ActionEvent event) {
    	River r=boxRiver.getValue();
    	txtStartDate.setText(r.getFlows().get(0).getDay().format(DateTimeFormatter.ISO_DATE));
    	txtEndDate.setText(r.getFlows().get(r.getFlows().size()-1).getDay().format(DateTimeFormatter.ISO_DATE));
    	txtFMed.setText(String.format("%.5f",r.mediaFlow()));
    	txtNumMeasurements.setText(r.getFlows().size()+"");
    }

    @FXML
    void doSimula(ActionEvent event) {
    	if(txtK.getText().compareTo("")!=0){
    		double K=Double.parseDouble(txtK.getText());
    		if(K>0){
    			//TODO simulazione
    			Simula sim=new Simula(model,boxRiver.getValue(),Double.parseDouble(txtK.getText()));
    			sim.run();
    			txtResult.setText("RISULTATO SIMULAZIONE PER IL RIVER: "+boxRiver.getValue().getName()+"\n"
    					+ "Giornate perse: "+sim.getGiornatePerse()+"\nCapienza media: "+String.format("%.5f",sim.getMedia())
    					+"\nTracimazioni: "+sim.getTracimazioni());
    			
    		}else{
    			txtResult.setText("ERRORE: inserire un valore di K maggiore di 0!");
    		}
    	}else{
    		txtResult.setText("ERRORE: inserire un valore di k!");
    	}
    	
    }

    @FXML
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Rivers.fxml'.";

    }
}
