package com.geowarin.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.base.Objects;

@Entity
@Table(name = "groups")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Group extends BaseEntity {
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@OneToMany
    @JoinColumn(name="group_id")
    private Set<Role> roles;
    
    public Group() {
	}
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@Override
    public String toString() {
    	return Objects.toStringHelper(this)
    			.add("name", name)
    			.add("roles", roles)
    			.toString();
    }
}
