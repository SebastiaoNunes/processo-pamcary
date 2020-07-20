package br.com.pamcary.exception;

import java.util.ArrayList;
import java.util.List;

public class PamcaryException  extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AppMessage> messages;
	
	public PamcaryException(AppMessage menssage) {
		if (messages == null) {
			messages = new ArrayList<AppMessage>();
		}
		
		messages.add(menssage);
	}
	
	public List<AppMessage> getErros() {
		return messages;
	}
}
