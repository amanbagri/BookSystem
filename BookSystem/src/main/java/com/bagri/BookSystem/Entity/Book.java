package com.bagri.BookSystem.Entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Book implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ;
	private String code;
	@NotBlank
	private String name ;
	private String author;
	@JsonIgnore
	private String description;
	private  int quantity ;
	@Column(name="is_active")
	private boolean active; 
	@JsonIgnore
	@Column(name="category_id")
	private  int categoryId;
	@JsonIgnore
	private  int library_id;
	private  int issued ;
	private int veiw;
	
	public Book() {
		
		this.code = "BKC" + UUID.randomUUID().toString().substring(26).toUpperCase();
		
	}
	
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}



	@Transient
	private MultipartFile file;
			
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getLibrary_id() {
		return library_id;
	}
	public void setLibrary_id(int library_id) {
		this.library_id = library_id;
	}
	public int getIssued() {
		return issued;
	}
	public void setIssued(int issued) {
		this.issued = issued;
	}
	public int getVeiw() {
		return veiw;
	}
	public void setVeiw(int veiw) {
		this.veiw = veiw;
	}



	@Override
	public String toString() {
		return "Book [id=" + id + ", code=" + code + ", name=" + name + ", author=" + author + ", description="
				+ description + ", quantity=" + quantity + ", active=" + active + ", categoryId=" + categoryId
				+ ", library_id=" + library_id + ", issued=" + issued + ", veiw=" + veiw + ", file=" + file
				+ ", getCode()=" + getCode() + ", getFile()=" + getFile() + ", getId()=" + getId() + ", getName()="
				+ getName() + ", getAuthor()=" + getAuthor() + ", getDescription()=" + getDescription()
				+ ", getQuantity()=" + getQuantity() + ", isActive()=" + isActive() + ", getCategoryId()="
				+ getCategoryId() + ", getLibrary_id()=" + getLibrary_id() + ", getIssued()=" + getIssued()
				+ ", getVeiw()=" + getVeiw() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	

}
