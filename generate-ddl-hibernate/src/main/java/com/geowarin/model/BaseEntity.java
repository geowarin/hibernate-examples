package com.geowarin.model;


import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.google.common.base.Objects;


/**
 * @author Geoffroy Warin (https://github.com/geowarin)
 *
 */
@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date createdOn;

	@Temporal(TemporalType.DATE)
	private Date modifiedOn;
	
	@Version
    private long version = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@PrePersist
	public void initTimeStamps() {
		if (createdOn == null) {
			createdOn = new Date();
		}
		modifiedOn = createdOn;
	}
	
	@PreUpdate
	public void updateTimeStamp() {
		modifiedOn = new Date();
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", id).toString();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		BaseEntity other = (BaseEntity) obj;
		return Objects.equal(this.getId(), other.getId());
	}
}
