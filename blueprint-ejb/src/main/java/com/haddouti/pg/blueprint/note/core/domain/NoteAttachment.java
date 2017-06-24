package com.haddouti.pg.blueprint.note.core.domain;

import java.util.Date;

/**
 * Domain NoteAttachment
 *
 */
public class NoteAttachment {

	private Long id;
	private String name;
	private String content;
	private Date createdAt;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NoteAttachment [id=").append(id).append(", name=").append(name).append(", content=")
				.append(content).append(", createdAt=").append(createdAt).append("]");
		return builder.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
