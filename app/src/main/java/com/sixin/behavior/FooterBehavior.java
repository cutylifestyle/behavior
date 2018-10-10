package com.sixin.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class FooterBehavior extends CoordinatorLayout.Behavior {

    private static final String TAG = "FooterBehavior";

    public FooterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //在实例化布局参数的过程中获取到了behavior实例之后的回调
    @Override
    public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams params) {
        super.onAttachedToLayoutParams(params);
        Log.d(TAG, "onAttachedLayoutParams");
    }

    //确定依赖关系
    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        Log.d(TAG, "child:" + child.getClass().getName() + " dependency:" + dependency.getClass().getName());
        return super.layoutDependsOn(parent, child, dependency);
    }

    //1:onChildViewsChanged   dispatchDependentViewsChanged  offsetChildToAnchor
    //TODO 暂且不去研究
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }

    //在按下的时候会触发该方法
    //TODO 这个方法的返回值的作用是什么
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        Log.d(TAG, "child:" + child.getClass().getName()+"\n"
                + "directTargetChild:" + directTargetChild.getClass().getName()+"\n"
                + "target:"+target.getClass().getName()+"\n"
                +"axes:"+axes+"\n"
                +"type:"+type);
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    //TODO 在CoordinatorLayout中与之相关方法的调用的作用是什么
    @Override
    public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type);
    }
}
