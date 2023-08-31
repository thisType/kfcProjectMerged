package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="kfcDetails")
public class KFCMenu {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private
	Integer id;
	
	
	private String kfcName;
	private String description;
	private Integer price;
	 private String imageName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKfcName() {
		return kfcName;
	}
	public void setKfcName(String kfcName) {
		this.kfcName = kfcName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	
	
}
