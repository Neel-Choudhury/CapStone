package com.example.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.model.EndpointAPIResponse;
import com.example.model.FakeStoreProductDownstream;

@Mapper
public interface DownStreamResponseMapper {
	List<EndpointAPIResponse> maptoUpStreamFromFakeStoreDownStream(FakeStoreProductDownstream[] downstreamApi);
}
