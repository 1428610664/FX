package com.weisj.fx.utils;

public enum CropperImageScale {
	/**
	 * 正方形
	 */
	square("正方形",1),
	/**
	 * 长方形
	 */
	rect("长方形",2);
	private int index;
	private CropperImageScale(String name, int ordinal) {
		this.index = ordinal;
	}
	 public int getIndex() {
         return index;
     }
	
}
