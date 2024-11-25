package com.example.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PacketInspector {
	@Autowired
	RestTemplate downstreamClient;

	@Value("${downstream.endpoint}")
	String dwnstream;

	public String inspectResponsePacketCandidate() {
		String downstreamDate = null;
		try {
			ResponseEntity<String> responseEntityPacket = downstreamClient.exchange(dwnstream, HttpMethod.GET, null,
					String.class);
			if (null != responseEntityPacket) {
				int responseState = responseEntityPacket.getStatusCode().value();
				String responseBody = responseEntityPacket.getBody();
				if (responseState == 200) {
					if (null != responseBody && !responseBody.isEmpty() && !responseBody.isBlank()) {
						downstreamDate = responseBody;
					} else {
						throw new Exception("Response Body blank or null");
					}

				} else {
					throw new Exception("Response Status not 200 found {" + responseState + "} ");
				}
			} else {
				throw new Exception("Response Entity found null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return downstreamDate;
	}
}
