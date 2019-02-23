package br.edu.utfpr.jsf.bean;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

@ManagedBean
public class HomeBean {
	
	private ScheduleModel eventModel = new DefaultScheduleModel();

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

}






