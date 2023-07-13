package com.marko.springrestbeers.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "mash_temp")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MashTemp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(mappedBy="mash_temp",cascade = CascadeType.ALL)
	private Temp temp;
	
	@Column(name = "duration")
	private int duration;
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	public Temp getTemp() {
		return temp;
	}


	public void setTemp(Temp temp) {
		  if (!temp.equals(this.temp)){
	            this.temp=temp;
	            temp.setMash_temp(this);
	        }
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	@Override
	public String toString() {
		return "MashTemp [id=" + id + ", temp=" + temp + ", duration=" + duration + "]";
	}
	
	

      
	
	
	
}
