package br.com.ottimizza.depara.domain.exceptions;

public class DeParaNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DeParaNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}