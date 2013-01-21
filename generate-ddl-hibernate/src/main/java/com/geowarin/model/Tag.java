package com.geowarin.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

/**
 * An entity which models a tag.
 * 
 * @author gwarin
 */
@Entity
@Table(name = "tags")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tag extends BaseEntity {
	
	@Column(name = "name", nullable = false)
    private String name;

	@ManyToMany(mappedBy = "tags")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Article> articles;
	
	public Tag() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	
	@Override
    public String toString() {
//		return LazyToStringHelper.toStringHelper(this)
//				.add("name", name)
//				.add("articles", articles)
//				.toString();
		ToStringHelper helper = Objects.toStringHelper(this)
    			.add("name", name);
	    if (Hibernate.isInitialized(articles)) {
	    	helper.add("articles", articles);
	    }
    	return helper.toString();
    }
}
