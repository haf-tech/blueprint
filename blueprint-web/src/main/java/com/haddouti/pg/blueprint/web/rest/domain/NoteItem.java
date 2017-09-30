package com.haddouti.pg.blueprint.web.rest.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NoteItem", propOrder = {})
public class NoteItem {
	private Long id;
	private String title;
	private String content;
	private String userId;
	private Date reminderAt;

	@Override
	public String toString() {
		return "NoteItem [id=" + id + ", title=" + title + ", content=" + content + ", userId=" + userId
				+ ", reminderAt=" + reminderAt + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Date getReminderAt() {
		return reminderAt;
	}

	public void setReminderAt(Date reminderAt) {
		this.reminderAt = reminderAt;
	}

}