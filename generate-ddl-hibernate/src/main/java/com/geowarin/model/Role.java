package com.geowarin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.base.Objects;

@Entity
@Table(name = "roles")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Role extends BaseEntity {

	@Column(name = "name", nullable=false, length=50)
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
    public String toString() {
    	return Objects.toStringHelper(this)
    			.add("name", name)
    			.toString();
    }
}
