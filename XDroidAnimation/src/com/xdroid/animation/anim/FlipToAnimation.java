package com.xdroid.animation.anim;

import com.xdroid.animation.base.AnimationBase;
import com.xdroid.animation.interfaces.Direction;
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
 * Flip to animation, turn from one view to another view
 * 
 * @author Robin
 * @since 2015-08-06 15:45:35
 * 
 */
public class FlipToAnimation extends AnimationBase<FlipToAnimation>{

	public static final int PIVOT_CENTER = 0, PIVOT_LEFT = 1, PIVOT_RIGHT = 2, PIVOT_TOP = 3, PIVOT_BOTTOM = 4;

	View flipToView;
	int pivot, direction;
	float flipAngle;
	float originalRotation;
	int orientation = Orientation.HORIZONTAL;

	/*
	 * ==================================================================
	 * Constructor
	 * ==================================================================
	 */

	public FlipToAnimation(View targetView) {
		this.targetView = targetView;
		flipToView = null;
		pivot = PIVOT_CENTER;
		direction = Direction.DIRECTION_RIGHT;
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

		flipAngle = 270f;
		if (pivot == PIVOT_CENTER) {
			flipAngle = 90f;
		}
		
		originalRotation = 0;

		flipToView.setLayoutParams(targetView.getLayoutParams());
		flipToView.setLeft(targetView.getLeft());
		flipToView.setTop(targetView.getTop());
		flipToView.setVisibility(View.VISIBLE);

		AnimatorSet flipToAnim = new AnimatorSet();
		switch (orientation) {
		case Orientation.HORIZONTAL:
			originalRotation = targetView.getRotationY();

			if (direction == Direction.DIRECTION_RIGHT) {
				flipToView.setRotationY(270f);
				flipToAnim.playSequentially(ObjectAnimator.ofFloat(targetView, View.ROTATION_Y, 0f, flipAngle),
						ObjectAnimator.ofFloat(flipToView, View.ROTATION_Y, 270f, 360f));
			} else {
				flipToView.setRotationY(-270f);
				flipToAnim.playSequentially(ObjectAnimator.ofFloat(targetView, View.ROTATION_Y, 0f, -flipAngle),
						ObjectAnimator.ofFloat(flipToView, View.ROTATION_Y, -270f, -360f));
			}

			break;

		case Orientation.VERTICAL:
			originalRotation = targetView.getRotationX();

			if (direction == Direction.DIRECTION_UP) {
				flipToView.setRotationX(270f);
				flipToAnim.playSequentially(ObjectAnimator.ofFloat(targetView, View.ROTATION_X, 0f, flipAngle),
						ObjectAnimator.ofFloat(flipToView, View.ROTATION_X, 270f, 360f));
			} else {
				flipToView.setRotationX(-270f);
				flipToAnim.playSequentially(ObjectAnimator.ofFloat(targetView, View.ROTATION_X, 0f, -flipAngle),
						ObjectAnimator.ofFloat(flipToView, View.ROTATION_X, -270f, -360f));
			}

			break;
		}

		flipToAnim.setInterpolator(interpolator);
		flipToAnim.setDuration(duration / 2);
		flipToAnim.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationStart(Animator animation) {
				if (listener != null) {
					listener.onAnimationStart(animation);
				}
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				targetView.setVisibility(View.INVISIBLE);
				switch (orientation) {
				case Orientation.HORIZONTAL:
					targetView.setRotationY(originalRotation);
					break;

				case Orientation.VERTICAL:
					targetView.setRotationX(originalRotation);
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
		return flipToAnim;
	}


	@Override
	public FlipToAnimation setPivotX(int pivotX) {
		ViewHelper.setPivotX(targetView, pivotX);
		ViewHelper.setPivotX(flipToView, pivotX);

		return this;
	}

	@Override
	public FlipToAnimation setPivotY(int pivotY) {
		ViewHelper.setPivotY(targetView, pivotY);
		ViewHelper.setPivotY(flipToView, pivotY);

		return this;
	}

	/*
	 * ================================================================== 
	 * Getter And Setter
	 * ==================================================================
	 */

	public View getFlipToView() {
		return flipToView;
	}

	public FlipToAnimation setFlipToView(View flipToView) {
		this.flipToView = flipToView;
		return this;
	}

	public int getPivot() {
		return pivot;
	}

	public FlipToAnimation setPivot(int pivot) {
		this.pivot = pivot;

		flipAngle = 270f;
		
		float pivotX = 0, pivotY = 0, viewWidth = targetView.getWidth(), viewHeight = targetView.getHeight();

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
				flipAngle = 90f;
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
				flipAngle = 90f;
				break;
			}

			break;
		}

		ViewHelper.setPivotX(targetView, pivotX);
		ViewHelper.setPivotY(targetView, pivotY);
		ViewHelper.setPivotX(flipToView, pivotX);
		ViewHelper.setPivotY(flipToView, pivotY);

		return this;
	}

	public int getDirection() {
		return direction;
	}

	public FlipToAnimation setDirection(int direction) {
		this.direction = direction;
		return this;
	}

	public int getOrientation() {
		return orientation;
	}

	public FlipToAnimation setOrientation(int orientation) {
		this.orientation = orientation;
		
		return this;
	}

}
