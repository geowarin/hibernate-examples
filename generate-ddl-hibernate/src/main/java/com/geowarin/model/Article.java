package com.geowarin.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.base.Objects;

@Entity
@Table(name = "articles")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Article extends BaseEntity {
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "text_id", nullable = true)
    private String textId;

    @Column(name = "html_contents", nullable = false)
    @Lob
    private String htmlContents;
    
    @ManyToMany
    @JoinTable(name="articles_tags",
	    joinColumns=
	        @JoinColumn(name="article_id", referencedColumnName="id"),
	    inverseJoinColumns=
	        @JoinColumn(name="tag_id", referencedColumnName="id")
	    )
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    public Set<Tag> tags;
    
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTextId() {
		return textId;
	}

	public void setTextId(String textId) {
		this.textId = textId;
	}

	public String getHtmlContents() {
		return htmlContents;
	}

	public void setHtmlContents(String htmlContents) {
		this.htmlContents = htmlContents;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
    
    @Override
    public String toString() {
    	return Objects.toStringHelper(this)
    			.add("title", title)
    			.add("htmlContents", htmlContents)
    			.add("tags", tags)
    			.add("textId", textId)
    			.toString();
    }
}
