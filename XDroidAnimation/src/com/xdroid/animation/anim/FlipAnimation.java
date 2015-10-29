package com.xdroid.animation.anim;

import com.xdroid.animation.base.AnimationBase;
import com.xdroid.animation.interfaces.Duration;
import com.xdroid.animation.interfaces.Orientation;
import com.xdroid.animation.utils.ViewHelper;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Flip animation, flip horizontal and vertical flip
 * 
 * @author Robin
 * @since 2015-08-06 14:33:26
 * 
 */
public class FlipAnimation extends AnimationBase<FlipAnimation>{

	public static final int PIVOT_CENTER = 0, PIVOT_LEFT = 1, PIVOT_RIGHT = 2,
			 PIVOT_TOP = 3, PIVOT_BOTTOM = 4;

	float degrees;
	int pivot;
	int orientation = Orientation.HORIZONTAL;
	
	/*
	 * ==================================================================
	 * Constructor
	 * ==================================================================
	 */
	
	public FlipAnimation(View targetView) {
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

		AnimatorSet flipSet = new AnimatorSet();
		switch (orientation) {
		case Orientation.HORIZONTAL:
			flipSet.play(ObjectAnimator.ofFloat(targetView, View.ROTATION_Y,
					targetView.getRotationY() + degrees));
			break;

	    case Orientation.VERTICAL:
	    	flipSet.play(ObjectAnimator.ofFloat(targetView, View.ROTATION_X,
	    			targetView.getRotationX() + degrees));
			break;
		}
		
		flipSet.setInterpolator(interpolator);
		flipSet.setDuration(duration);
		if (listener != null ) {
			flipSet.addListener(listener);
		}
		return flipSet;
	}
	
	/*
	 * ==================================================================
	 * Getter And Setter
	 * ==================================================================
	 */
	
	public float getDegrees() {
		return degrees;
	}

	public FlipAnimation setDegrees(float degrees) {
		this.degrees = degrees;
		return this;
	}

	public int getPivot() {
		return pivot;
	}

	public FlipAnimation setPivot(int pivot) {
		this.pivot = pivot;
		
		float pivotX = 0, pivotY = 0, viewWidth = targetView.getWidth(), viewHeight = targetView
				.getHeight();
		
		switch (orientation) {
		case Orientation.HORIZONTAL:
			switch (pivot) {
			case PIVOT_LEFT:
				pivotX = 0f;
				pivotY = viewHeight / 2;
				break;
			case PIVOT_RIGHT:
				pivotX = viewWidth;
				pivotY = viewHeight / 2;
				break;
			default:
				pivotX = viewWidth / 2;
				pivotY = viewHeight / 2;
				break;
			}
			break;

		case Orientation.VERTICAL:
			switch (pivot) {
			case PIVOT_TOP:
				pivotX = viewWidth / 2;
				pivotY = 0f;
				break;
			case PIVOT_BOTTOM:
				pivotX = viewWidth / 2;
				pivotY = viewHeight;
				break;
			default:
				pivotX = viewWidth / 2;
				pivotY = viewHeight / 2;
				break;
			}
			break;
		}
		
		ViewHelper.setPivotX(targetView, pivotX);
		ViewHelper.setPivotY(targetView, pivotY);
		
		return this;
	}

	public int getOrientation() {
		return orientation;
	}

	public FlipAnimation setOrientation(int orientation) {
		this.orientation = orientation;
		return this;
	}

}
