package com.example.model;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
class Rating implements Serializable{
	private static final long serialVersionUID = 1313062532550970460L;
	private Double rate;
	private long count;
	@Override
	public String toString() {
		return "Rating [rate=" + rate + ", count=" + count + "]";
	}
	
}

@Getter
@Setter 
@Data
public class FakeStoreProductDownstream implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1313062532550970460L;
	private Long id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;
	@Override
	public String toString() {
		return "FakeStoreProductDownstream [id=" + id + ", title=" + title + ", price=" + price + ", description="
				+ description + ", category=" + category + ", image=" + image + ", rating=" + rating + "]";
	}
	
}

