package com.liveyc.mina.client.service;

public class DKServiceUtil {
	private static DKService _service;

	public static DKService getService() {
		if (_service == null) {
			throw new RuntimeException("DKService is not set");
		}

		return _service;
	}

	public void setService(DKService service) {
		_service = service;
	}

}
