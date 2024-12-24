package com.app.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyModel {

	private WardModel wModel = new WardModel();
	private int propId;
	private String propName;
	private String propAddress;
	private int sqFeetArea;
	private double propPrice;
	private List<AminityModel> list;
	
}
