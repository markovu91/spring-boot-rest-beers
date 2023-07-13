package com.marko.springrestbeers.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="method")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Method {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "twist")
	private String twist;

	@OneToOne(mappedBy = "method")
	@JsonManagedReference
	private Beer beers;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="method_id")
	private List<MashTemp>mash_temp;
	
	
	public String getTwist() {
		return twist;
	}

	public void setTwist(String twist) {
		this.twist = twist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Beer getBeers() {
		return beers;
	}

	public void setBeers(Beer beers) {
		this.beers = beers;
	}

	public List<MashTemp> getMash_temp() {
		return mash_temp;
	}

	public void setMash_temp(List<MashTemp> mash_temp) {
		this.mash_temp = mash_temp;
	}

	@Override
	public String toString() {
		return "Method [id=" + id + ", twist=" + twist + ", beers=" + beers + ", mash_temp=" + mash_temp + "]";
	}
	
	
	
}
