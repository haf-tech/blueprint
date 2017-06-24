package com.haddouti.pg.blueprint.web.rest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NoteResponse", propOrder = {})
public class NoteResponse {

	@XmlElementWrapper(name = "resultStatus")
	@XmlElement(name = "status")
	private List<StatusCode> resultStatus = new ArrayList<>();

	@XmlElementWrapper(name = "items")
	@XmlElement(name = "item")
	private List<NoteItem> items = new ArrayList<>();

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "StatusCode")
	public static class StatusCode {
		@XmlAttribute
		private String code;
		@XmlAttribute
		private String text;

		@Override
		public String toString() {
			return "StatusCode [code=" + code + ", text=" + text + "]";
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

	}

	@Override
	public String toString() {
		return "NoteResponse [items=" + items + "]";
	}

	public List<NoteItem> getItems() {
		return items;
	}

	public void setItems(List<NoteItem> items) {
		this.items = items;
	}

	public List<StatusCode> getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(List<StatusCode> resultStatus) {
		this.resultStatus = resultStatus;
	}

}
