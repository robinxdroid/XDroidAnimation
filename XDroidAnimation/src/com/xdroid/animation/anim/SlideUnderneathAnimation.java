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
import android.widget.FrameLayout;

/**
 * Slide Underneath animation,this animation causes the view to slide in underneath from its own borders.
 * 
 * @author Robin
 * @since 2015-07-31 11:39:08
 * 
 */

public class SlideUnderneathAnimation extends AnimationBase<SlideUnderneathAnimation>{

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

	public SlideUnderneathAnimation(View targetView) {
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
		ViewHelper.setClipChildren(targetView, true);

		final ViewGroup parentView = (ViewGroup) targetView.getParent();
		final FrameLayout slideFrame = new FrameLayout(targetView.getContext());
		final int positionView = parentView.indexOfChild(targetView);
		slideFrame.setLayoutParams(targetView.getLayoutParams());
		slideFrame.setClipChildren(true);
		parentView.removeView(targetView);
		slideFrame.addView(targetView);
		parentView.addView(slideFrame, positionView);

		switch (slideMode) {
		case SlideMode.IN:

			switch (direction) {
			case Direction.DIRECTION_LEFT:
				targetView.setTranslationX(-targetView.getWidth());
				slideAnim = ObjectAnimator.ofFloat(targetView, View.TRANSLATION_X, slideFrame.getX());
				break;
			case Direction.DIRECTION_RIGHT:
				targetView.setTranslationX(targetView.getWidth());
				slideAnim = ObjectAnimator.ofFloat(targetView, View.TRANSLATION_X, slideFrame.getX());
				break;
			case Direction.DIRECTION_UP:
				targetView.setTranslationY(-targetView.getHeight());
				slideAnim = ObjectAnimator.ofFloat(targetView, View.TRANSLATION_Y, slideFrame.getY());
				break;
			case Direction.DIRECTION_DOWN:
				targetView.setTranslationY(targetView.getHeight());
				slideAnim = ObjectAnimator.ofFloat(targetView, View.TRANSLATION_Y, slideFrame.getY());
				break;
			default:
				break;
			}
			break;

		case SlideMode.OUT:

			switch (direction) {
			case Direction.DIRECTION_LEFT:
				slideAnim = ObjectAnimator.ofFloat(targetView, View.TRANSLATION_X,
						targetView.getTranslationX() - targetView.getWidth());
				break;
			case Direction.DIRECTION_RIGHT:
				slideAnim = ObjectAnimator.ofFloat(targetView, View.TRANSLATION_X,
						targetView.getTranslationX() + targetView.getWidth());
				break;
			case Direction.DIRECTION_UP:
				slideAnim = ObjectAnimator.ofFloat(targetView, View.TRANSLATION_Y,
						targetView.getTranslationY() - targetView.getHeight());
				break;
			case Direction.DIRECTION_DOWN:
				slideAnim = ObjectAnimator.ofFloat(targetView, View.TRANSLATION_Y,
						targetView.getTranslationY() + targetView.getHeight());
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
		slideAnim.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationStart(Animator animation) {
				switch (slideMode) {
				case SlideMode.IN:
					targetView.setVisibility(View.VISIBLE);
					break;

				case SlideMode.OUT:
					break;
				}

				if (listener != null) {
					listener.onAnimationStart(animation);
				}
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				switch (slideMode) {
				case SlideMode.IN:
					slideFrame.removeAllViews();
					parentView.removeView(slideFrame);
					targetView.setLayoutParams(slideFrame.getLayoutParams());
					parentView.addView(targetView, positionView);
					break;

				case SlideMode.OUT:
					targetView.setVisibility(View.INVISIBLE);
					//slideAnim.reverse();
					slideFrame.removeAllViews();
					parentView.removeView(slideFrame);
					targetView.setLayoutParams(slideFrame.getLayoutParams());
					parentView.addView(targetView, positionView);
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

	public SlideUnderneathAnimation setDirection(int direction) {
		this.direction = direction;
		return this;
	}

	public int getSlideMode() {
		return slideMode;
	}

	public SlideUnderneathAnimation setSlideMode(int slideMode) {
		this.slideMode = slideMode;

		return this;
	}

}
