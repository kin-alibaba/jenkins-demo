package com.example.kin.demo;

import java.util.ArrayList;

import javax.validation.Valid;

public class ListViewModel {

	@Valid
	private ArrayList<Item> List = new ArrayList<Item>();
	
	public ListViewModel() {}
	
	public ListViewModel(ArrayList<Item> List) {
		this.List = List;
	}

	public ArrayList<Item> getList() {
		return List;
	}

	public void setList(ArrayList<Item> List) {
		this.List = List;
	}
}