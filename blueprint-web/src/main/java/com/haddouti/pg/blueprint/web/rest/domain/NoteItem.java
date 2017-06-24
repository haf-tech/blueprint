package com.haddouti.pg.blueprint.web.rest.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NoteItem", propOrder = {})
public class NoteItem {
	private String title;
	private String content;
	private String userId;

	@Override
	public String toString() {
		return "NoteItem [title=" + title + ", content=" + content + ", userId=" + userId + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}