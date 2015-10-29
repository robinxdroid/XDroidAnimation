package com.xdroid.animation.utils;

import android.view.View;
import android.view.ViewGroup;

/**
 * The View helper classes
 * @author Robin
 * @since 2015-07-24 17:52:31
 *
 */
public  class ViewHelper {
	private ViewHelper() {
	}

	public static float getAlpha(View view) {
		return view.getAlpha();
	}

	public static void setAlpha(View view, float alpha) {
		view.setAlpha(alpha);
	}

	public static float getPivotX(View view) {
		return view.getPivotX();
	}

	public static void setPivotX(View view, float pivotX) {
		view.setPivotX(pivotX);
	}

	public static float getPivotY(View view) {
		return view.getPivotY();
	}

	public static void setPivotY(View view, float pivotY) {
		view.setPivotY(pivotY);
	}

	public static float getRotation(View view) {
		return view.getRotation();
	}

	public static void setRotation(View view, float rotation) {
		view.setRotation(rotation);
	}

	public static float getRotationX(View view) {
		return view.getRotationX();
	}

	public static void setRotationX(View view, float rotationX) {
		view.setRotationX(rotationX);
	}

	public static float getRotationY(View view) {
		return view.getRotationY();
	}

	public static void setRotationY(View view, float rotationY) {
		view.setRotationY(rotationY);
	}

	public static float getScaleX(View view) {
		return view.getScaleX();
	}

	public static void setScaleX(View view, float scaleX) {
		view.setScaleX(scaleX);
	}

	public static float getScaleY(View view) {
		return view.getScaleY();
	}

	public static void setScaleY(View view, float scaleY) {
		view.setScaleY(scaleY);
	}

	public static float getScrollX(View view) {
		return view.getScrollX();
	}

	public static void setScrollX(View view, int scrollX) {
		view.setScrollX(scrollX);
	}

	public static float getScrollY(View view) {
		return view.getScrollY();
	}

	public static void setScrollY(View view, int scrollY) {
		view.setScrollY(scrollY);
	}

	public static float getTranslationX(View view) {
		return view.getTranslationX();
	}

	public static void setTranslationX(View view, float translationX) {
		view.setTranslationX(translationX);
	}

	public static float getTranslationY(View view) {
		return view.getTranslationY();
	}

	public static void setTranslationY(View view, float translationY) {
		view.setTranslationY(translationY);
	}

	public static float getX(View view) {
		return view.getX();
	}

	public static void setX(View view, float x) {
		view.setX(x);
	}

	public static float getY(View view) {
		return view.getY();
	}

	public static void setY(View view, float y) {
		view.setY(y);
	}
	
	public static void setClipChildren(View targetView,boolean isClip){
		ViewGroup parentView = (ViewGroup) targetView.getParent(), rootView = (ViewGroup) targetView.getRootView();
		while (parentView != rootView) {
			parentView.setClipChildren(isClip);
			parentView = (ViewGroup) parentView.getParent();
		}
		rootView.setClipChildren(isClip);
	}
}
