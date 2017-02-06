package org.lacitysan.landfill.server.service.model;

/**
 * @author Alvin Quach
 */
public class OrdinalRange {
	private int min;
	private int max;
	public OrdinalRange(int min, int max) {
		this.min = min;
		this.max = max;
	}
	public int getMin() {
		return min;
	}
	public int getMax() {
		return max;
	}
}
