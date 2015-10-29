package com.xdroid.animation.anim;

import com.xdroid.animation.base.AnimationBase;
import com.xdroid.animation.interfaces.Duration;
import com.xdroid.animation.utils.ViewHelper;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * RotationAnimation animation
 * 
 * @author Robin
 * @since 2015-07-31 10:11:18
 * 
 */
public class RotateAnimation extends AnimationBase<RotateAnimation>{

	public static final int PIVOT_CENTER = 0, PIVOT_TOP_LEFT = 1, PIVOT_TOP_RIGHT = 2, PIVOT_BOTTOM_LEFT = 3,
			PIVOT_BOTTOM_RIGHT = 4;

	float degrees;
	int pivot;

	/*
	 * ==================================================================
	 * Constructor
	 * ==================================================================
	 */

	public RotateAnimation(View targetView) {
		this.targetView = targetView;
		degrees = 360;
		pivot = PIVOT_CENTER;
		interpolator = new AccelerateDecelerateInterpolator();
		duration = Duration.DURATION_LONG;
		listener = null;
	}

	/*
	 * ==================================================================
	 * Override CombinableMethod
	 * ==================================================================
	 */

	@Override
	public void animate() {
		createAnimatorSet().start();
	}

	@Override
	public AnimatorSet createAnimatorSet() {
		ViewHelper.setClipChildren(targetView, false);

		AnimatorSet rotationSet = new AnimatorSet();
		rotationSet.play(ObjectAnimator.ofFloat(targetView, View.ROTATION, targetView.getRotation() + degrees));
		rotationSet.setInterpolator(interpolator);
		rotationSet.setDuration(duration);
		if (listener != null) {
			rotationSet.addListener(listener);
		}

		return rotationSet;
	}

	
	/*
	 * ==================================================================
	 * Getter And Setter
	 * ==================================================================
	 */


	public RotateAnimation setPivot(int pivot) {
		this.pivot = pivot;

		float pivotX, pivotY, viewWidth = targetView.getWidth(), viewHeight = targetView.getHeight();
		switch (pivot) {
		case PIVOT_TOP_LEFT:
			pivotX = 1f;
			pivotY = 1f;
			break;
		case PIVOT_TOP_RIGHT:
			pivotX = viewWidth;
			pivotY = 1f;
			break;
		case PIVOT_BOTTOM_LEFT:
			pivotX = 1f;
			pivotY = viewHeight;
			break;
		case PIVOT_BOTTOM_RIGHT:
			pivotX = viewWidth;
			pivotY = viewHeight;
			break;
		default:
			pivotX = viewWidth / 2;
			pivotY = viewHeight / 2;
			break;
		}
		ViewHelper.setPivotX(targetView, pivotX);
		ViewHelper.setPivotY(targetView, pivotY);

		return this;
	}

	public RotateAnimation setDegrees(float degrees) {
		this.degrees = degrees;
		return this;
	}

}
