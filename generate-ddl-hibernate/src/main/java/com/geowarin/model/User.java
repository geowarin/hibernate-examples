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

/**
 * An entity class which contains the information of a single person.
 * 
 * @author Geoffroy Warin (https://github.com/geowarin)
 *
 */
@Entity
@Table(name = "users")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends BaseEntity {
    
    @Column(name = "user_name", nullable = false, unique=true, length=50)
    private String userName;

    @Column(name = "password", nullable = false, length=80)
    private String password;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @OneToMany
    @JoinColumn(name="user_id")
    private Set<Group> groups;
    
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

    @Override
    public String toString() {
    	return Objects.toStringHelper(this)
    			.add("userName", userName)
    			.add("email", email)
    			.add("groups", groups)
    			.toString();
    }
}
