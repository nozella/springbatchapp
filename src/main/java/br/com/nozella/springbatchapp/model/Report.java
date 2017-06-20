package br.com.nozella.springbatchapp.model;

import java.io.Serializable;

public class Report implements Serializable {

	private int id;
	private String string;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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