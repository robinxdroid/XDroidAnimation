package com.xdroid.animation.anim;

import com.xdroid.animation.base.AnimationBase;
import com.xdroid.animation.interfaces.Duration;
import com.xdroid.animation.utils.ViewHelper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * This animation transfers the view to another view provided by the user
 * through scaling and translation. The view is scaled to the same size and is
 * translated to the same position as the destination view.
 * 
 * @author Robin
 * @since 2015-07-31 17:59:58
 * 
 */
public class TransferAnimation extends AnimationBase<TransferAnimation>{

	View destinationView;
	int transX, transY;
	float scaleX, scaleY;
	ViewGroup parentView;

	/*
	 * ==================================================================
	 * Constructor
	 * ==================================================================
	 */

	public TransferAnimation(View targetView) {
		this.targetView = targetView;
		destinationView = null;
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
		ViewHelper.setClipChildren(targetView, false);

		parentView = (ViewGroup) targetView.getParent();

		targetView.animate().scaleX(scaleX).scaleY(scaleY).translationX(transX).translationY(transY)
				.setInterpolator(interpolator).setDuration(duration).setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationStart(Animator animation) {
						if (listener != null) {
							listener.onAnimationStart(animation);
						}
					}

					@Override
					public void onAnimationEnd(Animator animation) {
						if (listener != null) {
							listener.onAnimationEnd(animation);
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
	}

	@Override
	public AnimatorSet createAnimatorSet() {
		return null;
	}


	/*
	 * ================================================================== 
	 * Getter And Setter
	 * ==================================================================
	 */

	/**
	 * @return The destination view to transfer the original view to.
	 */
	public View getDestinationView() {
		return destinationView;
	}

	/**
	 * @param The
	 *            destination view to set to transfer the original view to.
	 * @return TransferAnimation
	 */
	public TransferAnimation setDestinationView(View destinationView) {
		this.destinationView = destinationView;

		scaleX = (float) destinationView.getWidth() / ((float) targetView.getWidth());
		scaleY = (float) destinationView.getHeight() / ((float) targetView.getHeight());
		
		int[] locationDest = new int[2], locationView = new int[2];
		targetView.getLocationOnScreen(locationView);
		destinationView.getLocationOnScreen(locationDest);
		transX = locationDest[0] - locationView[0];
		transY = locationDest[1] - locationView[1];
		transX = transX - targetView.getWidth() / 2 + destinationView.getWidth() / 2;
		transY = transY - targetView.getHeight() / 2 + destinationView.getHeight() / 2;

		return this;
	}

	public int getTransX() {
		return transX;
	}

	public void setTransX(int transX) {
		this.transX = transX;
	}

	public int getTransY() {
		return transY;
	}

	public void setTransY(int transY) {
		this.transY = transY;
	}

	public float getScaleX() {
		return scaleX;
	}

	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}

	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}
	
	

}
