package com.skilldistillery.myutils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMenu implements Menu {

	// useful char finals
	protected static final Character AIRPLANE_CHAR = '\u2708';
	protected static final Character PACKAGE_CHAR = '\u2709';
	protected static final Character SWORDS_CHAR = '\u2694';

	// fields
	private List<String> headers;
	private List<String> options;
	private char lining;

	public AbstractMenu() {
		headers = new ArrayList<>();
		options = new ArrayList<>();
		lining = '*';
	}

	public AbstractMenu(char lining, List<String> headers, List<String> options) {
		this.headers = headers;
		this.lining = lining;
		this.options = options;
	}

	// setters and getters
	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

	public char getLining() {
		return lining;
	}

	public void setLining(char lining) {
		this.lining = lining;
	}

	
	// manipulate headers and options
	public void addOption(String option) {
		options.add(option);
	}

	public void removeOption(int index) {
		options.remove(index);

	}

	public void removeOption(String option) {
		options.remove(option);
	}

	public void replaceOption(String removedOption, String newOption) {

		int replaceIndex = options.indexOf(removedOption);
		options.set(replaceIndex, newOption);

	}
	public void addHeader(String header) {
		headers.add(header);
	}
	
	public void removeHeader(int index) {
		headers.remove(index);
		
	}
	
	public void removeHeader(String header) {
		headers.remove(header);
	}
	
	public void replaceHeader(String removedHeader, String newHeader) {
		
		int replaceIndex = headers.indexOf(removedHeader);
		headers.set(replaceIndex, newHeader);
		
	}
	
	// other methods
	public String buildMenu() {
		return buildCustomMenu(lining, headers, options);
	}

}
