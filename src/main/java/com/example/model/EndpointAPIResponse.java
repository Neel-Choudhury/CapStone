package com.example.model;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class EndpointAPIResponse implements Serializable{
	private static final long serialVersionUID = 1313062532550970460L; 
	private Long id;
	private String title;
	private double price;
	private String description;
}
