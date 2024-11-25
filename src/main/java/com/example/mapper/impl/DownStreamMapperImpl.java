package com.example.mapper.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mapper.DownStreamResponseMapper;
import com.example.model.EndpointAPIResponse;
import com.example.model.FakeStoreProductDownstream;

@Service
public class DownStreamMapperImpl implements DownStreamResponseMapper {

	@Override
	public List<EndpointAPIResponse> maptoUpStreamFromFakeStoreDownStream(FakeStoreProductDownstream[] downstreamApi) {
		List<EndpointAPIResponse> responseArray = new ArrayList<>(); 
		for (FakeStoreProductDownstream dwnstrmidxObj : Arrays.asList(downstreamApi)) {
			EndpointAPIResponse responseOb = new EndpointAPIResponse();
			responseOb.setDescription(dwnstrmidxObj.getDescription());
			responseOb.setTitle(dwnstrmidxObj.getTitle());
			responseOb.setPrice(dwnstrmidxObj.getPrice());
			responseOb.setId(dwnstrmidxObj.getId());
			responseArray.add(responseOb);
		}
		return responseArray;
	}

	 
	
}
