package com.egroupai.engine.util;

import java.util.UUID;

public class UUIDGenerator {

	/**
	 * 獲得一個UUID
	 * 
	 * @return String UUID
	 */
	public String getUUID() {
		final UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
}
