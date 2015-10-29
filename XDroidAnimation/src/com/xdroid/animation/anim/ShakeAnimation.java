package com.xdroid.animation.anim;

import com.xdroid.animation.base.AnimationBase;
import com.xdroid.animation.interfaces.Duration;
import com.xdroid.animation.interfaces.Orientation;
import com.xdroid.animation.utils.ViewHelper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Shake animation,This animation causes the view to shake from left to right
 * for a customizable number of times before returning to its original position.
 * 
 * @author Robin
 * @since 2015-07-31 13:59:53
 * 
 */

public class ShakeAnimation extends AnimationBase<ShakeAnimation>{

	float shakeDistance;
	int numOfShakes, shakeCount = 0;
	
	private int orientation = Orientation.HORIZONTAL;
	
	/*
	 * ==================================================================
	 * Constructor
	 * ==================================================================
	 */

	public ShakeAnimation(View targetView) {
		this.targetView = targetView;
		shakeDistance = 20;
		numOfShakes = 2;
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
		
		long singleShakeDuration = duration / numOfShakes / 2;
		if (singleShakeDuration == 0)
			singleShakeDuration = 1;
		final AnimatorSet shakeAnim = new AnimatorSet();
		switch (orientation) {
		case Orientation.HORIZONTAL:
			shakeAnim.playSequentially(ObjectAnimator.ofFloat(targetView, View.TRANSLATION_X, shakeDistance),
					ObjectAnimator.ofFloat(targetView, View.TRANSLATION_X, -shakeDistance),
//					ObjectAnimator.ofFloat(targetView, View.TRANSLATION_X, shakeDistance),
					ObjectAnimator.ofFloat(targetView, View.TRANSLATION_X, 0));
			break;

		case Orientation.VERTICAL:
			
			shakeAnim.playSequentially(ObjectAnimator.ofFloat(targetView,View.TRANSLATION_Y, shakeDistance),
					ObjectAnimator.ofFloat(targetView, View.TRANSLATION_Y, -shakeDistance),
//					ObjectAnimator.ofFloat(targetView, View.TRANSLATION_Y, shakeDistance),
					ObjectAnimator.ofFloat(targetView, View.TRANSLATION_Y, 0));
			
			break;
		}
		
		shakeAnim.setInterpolator(interpolator);
		shakeAnim.setDuration(singleShakeDuration);
		shakeAnim.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationStart(Animator animation) {
				if (listener != null) {
					listener.onAnimationStart(animation);
				}
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				shakeCount++;
				if (shakeCount == numOfShakes) {
					if (listener != null) {
						listener.onAnimationEnd(animation);
					}
				} else {
					shakeAnim.start();
				}
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				if (listener != null) {
					listener.onAnimationCancel(animation);
				}
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				if (listener != null) {
					listener.onAnimationRepeat(animation);
				}
			}
		});
		return shakeAnim;
	}


	/*
	 * ================================================================== 
	 * Getter And Setter
	 * ==================================================================
	 */
	

	public int getOrientation() {
		return orientation;
	}

	public ShakeAnimation setOrientation(int orientation) {
		this.orientation = orientation;
		return this;
	}

	/**
	 * @return The maximum shake distance.
	 */
	public float getShakeDistance() {
		return shakeDistance;
	}

	/**
	 * @param shakeDistance
	 *            The maximum shake distance to set.
	 * @return ShakeAnimation
	 */
	public ShakeAnimation setShakeDistance(float shakeDistance) {
		this.shakeDistance = shakeDistance;
		return this;
	}

	/**
	 * @return The number of shakes.
	 */
	public int getNumOfShakes() {
		return numOfShakes;
	}

	/**
	 * @param numOfShakes
	 *            The number of shakes to set.
	 * @return ShakeAnimation
	 */
	public ShakeAnimation setNumOfShakes(int numOfShakes) {
		this.numOfShakes = numOfShakes;
		return this;
	}

}
