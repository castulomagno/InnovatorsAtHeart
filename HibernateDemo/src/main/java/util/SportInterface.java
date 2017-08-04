package util;

import java.util.List;

import model.Torneo;

public interface SportInterface<T> {

	public void createElement(int id, String nombre);
	public List<T> getElement();
}
