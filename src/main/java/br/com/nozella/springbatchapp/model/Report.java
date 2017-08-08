package br.com.nozella.springbatchapp.model;

import java.io.Serializable;

/**
 * Created by Marcos Nozella
 */
public class Report implements Serializable {

	private int id;
	private String string;

	@SuppressWarnings("unused")
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@SuppressWarnings("unused")
	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", string=" + string + "]";
	}

}