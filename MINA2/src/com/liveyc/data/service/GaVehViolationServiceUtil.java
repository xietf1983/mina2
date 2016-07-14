package com.liveyc.data.service;

public class GaVehViolationServiceUtil {
	private static GaVehViolationService _service;

	public static GaVehViolationService getService() {
		if (_service == null) {
			throw new RuntimeException("GaVehViolationService is not set");
		}

		return _service;
	}

	public void setService(GaVehViolationService service) {
		_service = service;
	}

}
