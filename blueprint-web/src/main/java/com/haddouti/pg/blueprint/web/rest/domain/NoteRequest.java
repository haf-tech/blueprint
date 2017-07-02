package com.haddouti.pg.blueprint.web.rest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * JAXB class for the note request
 *
 */
@XmlRootElement(name = "NoteRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NoteRequest", propOrder = {})
public class NoteRequest {

	@XmlElementWrapper(name = "items")
	@XmlElement(name = "item")
	private List<NoteItem> items = new ArrayList<>();

	@Override
	public String toString() {
		return "NoteRequest [items=" + items + "]";
	}

	public List<NoteItem> getItems() {
		return items;
	}

	public void setItems(List<NoteItem> items) {
		this.items = items;
	}

}
