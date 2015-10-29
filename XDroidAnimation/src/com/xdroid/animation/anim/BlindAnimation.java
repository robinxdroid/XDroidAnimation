package com.xdroid.animation.anim;

import com.xdroid.animation.base.AnimationBase;
import com.xdroid.animation.interfaces.Direction;
import com.xdroid.animation.interfaces.Duration;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

/**
 * Blind Animation ，Similar to the shutter
 * 
 * @author Robin
 * @since 2015-08-05 19:28:58
 * 
 */
public class BlindAnimation extends AnimationBase<BlindAnimation>{
	
	private int direction = Direction.DIRECTION_UP;
	int blindMode = BlindMode.IN;
	
	public interface BlindMode {
		public static final int IN = 0x01, OUT = 0x02;
	}

	/*
	 * ==================================================================
	 * Constructor
	 * ==================================================================
	 */

	public BlindAnimation(View targetView) {
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

		createAnimatorSet().start();

	}

	@Override
	public AnimatorSet createAnimatorSet() {
		final ViewGroup parent = (ViewGroup) targetView.getParent(),
				animationLayout = new FrameLayout(targetView.getContext());
		final int positionView = parent.indexOfChild(targetView);
		animationLayout.setLayoutParams(targetView.getLayoutParams());
		parent.removeView(targetView);
		animationLayout.addView(targetView);
		parent.addView(animationLayout, positionView);
		
		final float originalScaleY = targetView.getScaleY();
		ObjectAnimator scaleY  = null , scaleY_child= null;
		
		switch (blindMode) {
		case BlindMode.IN:
			switch (direction) {
			case Direction.DIRECTION_UP:
				
				scaleY = ObjectAnimator.ofFloat(animationLayout, View.SCALE_Y, 0f , 1.0f);
				scaleY_child = ObjectAnimator.ofFloat(targetView, View.SCALE_Y, 2.5f , 1.0f);

				animationLayout.setPivotX(0);
				animationLayout.setPivotY(0);
				targetView.setPivotX(0);
				targetView.setPivotY(0);
				
				break;

			case Direction.DIRECTION_DOWN:
				
				scaleY = ObjectAnimator.ofFloat(animationLayout, View.SCALE_Y, 0f , 1.0f);
				scaleY_child = ObjectAnimator.ofFloat(targetView, View.SCALE_Y, 2.5f , 1.0f);

				animationLayout.setPivotX(0);
				animationLayout.setPivotY(targetView.getHeight());
				targetView.setPivotX(0);
				targetView.setPivotY(targetView.getHeight());
				break;
			case Direction.DIRECTION_LEFT:
				
				scaleY = ObjectAnimator.ofFloat(animationLayout, View.SCALE_X, 0f , 1.0f);
				scaleY_child = ObjectAnimator.ofFloat(targetView, View.SCALE_X, 2.5f , 1.0f);

				animationLayout.setPivotX(0);
				animationLayout.setPivotY(targetView.getHeight());
				targetView.setPivotX(0);
				targetView.setPivotY(targetView.getHeight());
				break;
			case Direction.DIRECTION_RIGHT:
				
				scaleY = ObjectAnimator.ofFloat(animationLayout, View.SCALE_X, 0f , 1.0f);
				scaleY_child = ObjectAnimator.ofFloat(targetView, View.SCALE_X, 2.5f , 1.0f);

				animationLayout.setPivotX(targetView.getWidth());
				animationLayout.setPivotY(0);
				targetView.setPivotX(targetView.getWidth());
				targetView.setPivotY(0);
				break;
			}
			break;

		case BlindMode.OUT:
			switch (direction) {
			case Direction.DIRECTION_UP:
				
				scaleY = ObjectAnimator.ofFloat(animationLayout, View.SCALE_Y, 0f);
				scaleY_child = ObjectAnimator.ofFloat(targetView, View.SCALE_Y, 2.5f);

				animationLayout.setPivotX(0);
				animationLayout.setPivotY(targetView.getHeight());
				targetView.setPivotX(0);
				targetView.setPivotY(targetView.getHeight());
				break;

			case Direction.DIRECTION_DOWN:
				
				scaleY = ObjectAnimator.ofFloat(animationLayout, View.SCALE_Y, 0f);
				scaleY_child = ObjectAnimator.ofFloat(targetView, View.SCALE_Y, 2.5f);

				animationLayout.setPivotX(0);
				animationLayout.setPivotY(0);
				targetView.setPivotX(0);
				targetView.setPivotY(0);
				break;
			case Direction.DIRECTION_LEFT:
				
				scaleY = ObjectAnimator.ofFloat(animationLayout, View.SCALE_X, 0f);
				scaleY_child = ObjectAnimator.ofFloat(targetView, View.SCALE_X, 2.5f);

				animationLayout.setPivotX(targetView.getWidth());
				animationLayout.setPivotY(0);
				targetView.setPivotX(targetView.getWidth());
				targetView.setPivotY(0);
			
				break;
			case Direction.DIRECTION_RIGHT:
				
				scaleY = ObjectAnimator.ofFloat(animationLayout, View.SCALE_X, 0f);
				scaleY_child = ObjectAnimator.ofFloat(targetView, View.SCALE_X, 2.5f);

				animationLayout.setPivotX(0);
				animationLayout.setPivotY(targetView.getHeight());
				targetView.setPivotX(0);
				targetView.setPivotY(targetView.getHeight());
				break;
			}
			break;
		}

		AnimatorSet blindAnimationSet = new AnimatorSet();
		blindAnimationSet.playTogether(scaleY, scaleY_child);
		blindAnimationSet.setInterpolator(interpolator);
		blindAnimationSet.setDuration(duration / 2);
		blindAnimationSet.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationStart(Animator animation) {
				switch (blindMode) {
				case BlindMode.IN:
					targetView.setVisibility(View.VISIBLE);
					break;

				case BlindMode.OUT:
					break;
				}
				if (listener != null) {
					listener.onAnimationStart(animation);
				}
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				switch (blindMode) {
				case BlindMode.IN:
					
					break;

				case BlindMode.OUT:
					targetView.setVisibility(View.INVISIBLE);
					break;
				}
				targetView.setScaleY(originalScaleY);
				animationLayout.removeAllViews();
				parent.removeView(animationLayout);
				parent.addView(targetView, positionView);
				
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
		return blindAnimationSet;
	}


	
	/*
	 * ==================================================================
	 * Getter And Setter
	 * ==================================================================
	 */
	
	public int getDirection() {
		return direction;
	}

	public BlindAnimation setDirection(int direction) {
		this.direction = direction;
		
		return this;
	}

	public int getBlindMode() {
		return blindMode;
	}

	public BlindAnimation setBlindMode(int blindMode) {
		this.blindMode = blindMode;
		
		return this;
	}

}
