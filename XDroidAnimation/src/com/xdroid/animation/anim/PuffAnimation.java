package com.xdroid.animation.anim;

import com.xdroid.animation.base.AnimationBase;
import com.xdroid.animation.interfaces.Duration;
import com.xdroid.animation.utils.ViewHelper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Puff Animation, zoom and gradient at the same time
 * 
 * @author Robin
 * @since 2015-08-06 17:27:41
 * 
 */
public class PuffAnimation extends AnimationBase<PuffAnimation> {
	
	float originalScaleX = 0f , originalScaleY = 0f , originalAlpha = 0f;
	int puffMode = PuffMode.IN;

	public interface PuffMode {
		public static final int IN = 0x01, OUT = 0x02;
	}

	/*
	 * ==================================================================
	 * Constructor
	 * ==================================================================
	 */
	
	public PuffAnimation(View targetView) {
		this.targetView = targetView;
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

		float scaleX = 0f, scaleY = 0f, alpha = 0f;
		
		switch (puffMode) {
		case PuffMode.IN:
			targetView.setScaleX(4f);
			targetView.setScaleY(4f);
			targetView.setAlpha(0f);

			scaleX = 1f;
			scaleY = 1f;
			alpha = 1f;
			break;

		case PuffMode.OUT:
			originalScaleX = targetView.getScaleX();
			originalScaleY = targetView.getScaleY();
			originalAlpha = targetView.getAlpha();

			scaleX = 4f;
			scaleY = 4f;
			alpha = 0f;
			break;
		}

		targetView.animate().scaleX(scaleX).scaleY(scaleY).alpha(alpha).setInterpolator(interpolator)
				.setDuration(duration).setListener(new AnimatorListenerAdapter() {

					@Override
					public void onAnimationStart(Animator animation) {
						switch (puffMode) {
						case PuffMode.IN:
							targetView.setVisibility(View.VISIBLE);
							break;

						case PuffMode.OUT:
							break;
						}
						
						if (listener != null) {
							listener.onAnimationStart(animation);
						}
			
					}

					@Override
					public void onAnimationEnd(Animator animation) {
						switch (puffMode) {
						case PuffMode.IN:
							break;

						case PuffMode.OUT:
							targetView.setVisibility(View.INVISIBLE);
							targetView.setScaleX(originalScaleX);
							targetView.setScaleY(originalScaleY);
							targetView.setAlpha(originalAlpha);
							break;
						}
						
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

	public int getPuffMode() {
		return puffMode;
	}

	public PuffAnimation setPuffMode(int puffMode) {
		this.puffMode = puffMode;
		
		return this;
	}

}
