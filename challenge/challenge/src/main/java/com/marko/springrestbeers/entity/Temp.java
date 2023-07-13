package com.marko.springrestbeers.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "temp")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Temp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "value")
	private int value;
	@Column(name = "unit")
    private String unit;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mash_temp_id")
	private MashTemp mash_temp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public MashTemp getMash_temp() {
		return mash_temp;
	}

	public void setMash_temp(MashTemp mash_temp) {
		this.mash_temp = mash_temp;
	}

	@Override
	public String toString() {
		return "Temp [id=" + id + ", value=" + value + ", unit=" + unit + ", mash_temp=" + mash_temp + "]";
	}
	
	
	
    
}
