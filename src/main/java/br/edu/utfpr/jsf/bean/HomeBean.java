package br.edu.utfpr.jsf.bean;

import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.springframework.stereotype.Component;

@Component
public class HomeBean {
	
	private ScheduleModel eventModel = new DefaultScheduleModel();

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

}






