package org.lacitysan.landfill.server.util.model;

/**
 * A range of integers represented by a min and max value.
 * @author Alvin Quach
 */
public class IntegerRange {
	private int min;
	private int max;
	public IntegerRange(int min, int max) {
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
