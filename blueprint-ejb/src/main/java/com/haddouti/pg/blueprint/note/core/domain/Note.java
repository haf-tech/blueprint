package com.haddouti.pg.blueprint.note.core.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Domain Note
 *
 */
public class Note {

	private Long id;
	private String title;
	private String content;
	private Date createdAt;
	private Date updatedAt;
	private String createdByUser;
	private List<String> tags = new ArrayList<>();
	private List<NoteAttachment> attachments = new ArrayList<>();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Note [id=").append(id).append(", title=").append(title).append(", content=").append(content)
				.append(", createdAt=").append(createdAt).append(", updatedAt=").append(updatedAt)
				.append(", createdByUser=").append(createdByUser).append(", tags=").append(tags)
				.append(", attachments=").append(attachments).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdByUser == null) ? 0 : createdByUser.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		if (createdByUser == null) {
			if (other.createdByUser != null)
				return false;
		} else if (!createdByUser.equals(other.createdByUser))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<NoteAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<NoteAttachment> attachments) {
		this.attachments = attachments;
	}

}
