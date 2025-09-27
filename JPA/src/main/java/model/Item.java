package model;

import jakarta.persistence.*;

@Entity
@Table(name = "[Item]")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") // PK auto-increment
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	// URL ảnh (hoặc data:image/...)
	@Lob
	@Column(name = "image", columnDefinition = "NVARCHAR(MAX)")
	private String image;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@Column(name = "owner")
	private String owner;

	// ===== getters/setters =====
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
}