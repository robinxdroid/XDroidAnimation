package com.xdroid.animation.anim;

import com.xdroid.animation.base.AnimationBase;
import com.xdroid.animation.interfaces.Direction;
import com.xdroid.animation.interfaces.Duration;
import com.xdroid.animation.utils.ViewHelper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Slide animation,this causes the view to slide in from the borders of the
 * screen.
 * 
 * @author Robin
 * @since 2015-07-31 11:39:08
 * 
 */

public class SlideAnimation extends AnimationBase<SlideAnimation>{

	int direction;
	int slideMode = SlideMode.IN;
	ObjectAnimator slideAnim;

	/**
	 * Slide animation mode, to enter or exit
	 * 
	 * @author Robin
	 *
	 */
	public interface SlideMode {
		public static final int IN = 0x01, OUT = 0x02;
	}

	/*
	 * ==================================================================
	 * Constructor
	 * ==================================================================
	 */

	public SlideAnimation(View targetView) {
		this.targetView = targetView;
		direction = Direction.DIRECTION_LEFT;
		interpolator = new AccelerateDecelerateInterpolator();
		duration = Duration.DURATION_LONG;
		listener = null;
		slideAnim = null;
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
		
		ViewGroup rootView = (ViewGroup) targetView.getRootView();
		
		int[] locationView = new int[2];
		targetView.getLocationOnScreen(locationView);

		switch (slideMode) {
		case SlideMode.IN:

			switch (direction) {
			case Direction.DIRECTION_LEFT:
				slideAnim = ObjectAnimator.ofFloat(targetView, View.X, -locationView[0] - targetView.getWidth(),
						targetView.getX());
				break;
			case Direction.DIRECTION_RIGHT:
				slideAnim = ObjectAnimator.ofFloat(targetView, View.X, rootView.getRight(), targetView.getX());
				break;
			case Direction.DIRECTION_UP:
				slideAnim = ObjectAnimator.ofFloat(targetView, View.Y, -locationView[1] - targetView.getHeight(),
						targetView.getY());
				break;
			case Direction.DIRECTION_DOWN:
				slideAnim = ObjectAnimator.ofFloat(targetView, View.Y, rootView.getBottom(), targetView.getY());
				break;
			default:
				break;
			}

			break;

		case SlideMode.OUT:

			switch (direction) {
			case Direction.DIRECTION_LEFT:
				slideAnim = ObjectAnimator.ofFloat(targetView, View.X, -locationView[0] - targetView.getWidth());
				break;
			case Direction.DIRECTION_RIGHT:
				slideAnim = ObjectAnimator.ofFloat(targetView, View.X, rootView.getRight());
				break;
			case Direction.DIRECTION_UP:
				slideAnim = ObjectAnimator.ofFloat(targetView, View.Y, -locationView[1] - targetView.getHeight());
				break;
			case Direction.DIRECTION_DOWN:
				slideAnim = ObjectAnimator.ofFloat(targetView, View.Y, rootView.getBottom());
				break;
			default:
				break;
			}

			break;
		}

		AnimatorSet slideSet = new AnimatorSet();
		slideSet.play(slideAnim);
		slideSet.setInterpolator(interpolator);
		slideSet.setDuration(duration);
		slideSet.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationStart(Animator animation) {
				switch (slideMode) {
				case SlideMode.IN:
					targetView.setVisibility(View.VISIBLE);
					if (listener != null) {
						listener.onAnimationStart(animation);
					}
					break;

				case SlideMode.OUT:
					break;
				}

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				switch (slideMode) {
				case SlideMode.IN:
					break;

				case SlideMode.OUT:
					targetView.setVisibility(View.INVISIBLE);
					slideAnim.reverse();
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
		return slideSet;
	}

	/*
	 * ================================================================== 
	 * Getter And Setter
	 * ==================================================================
	 */

	public SlideAnimation setDirection(int direction) {
		this.direction = direction;
		return this;
	}

	public int getSlideMode() {
		return slideMode;
	}

	public SlideAnimation setSlideMode(int slideMode) {
		this.slideMode = slideMode;

		return this;
	}

}
