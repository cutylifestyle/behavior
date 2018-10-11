package com.sixin.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
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
        return false;
    }

    //在这个组合中，调用联合滑动的方法onNestedPreScroll方法，对于AppBar而言
    //,通过了isNestedScrollAccepted的判断，调用了它的behavior的方法onNestedPreScroll
    //实现AppBar滑动，对于recyclerView,没有通过isNestedScrollAccepted的判断，
    //它的behavior的onNestedPreScroll方法没有被调用。但是他却实现了联合滑动，
    //为什么呢？那是因为代码走到了CoordinateLayout的1360行，走到这行代码的前提
    //条件需要view之间有依赖关系，recyclerView的Behavior实现了该方法，实现联动
    //效果的,但是重写这个方法不推荐
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
//        Log.d(TAG, "onDependentViewChanged------:"+"child:"+child.getClass().getName()+"\n"+"dependency:"+dependency.getClass().getName());
//        int y = dependency.getTop();
//        Log.d(TAG, "y-------------:" + y);
//        child.setTranslationY(-y);
        return false;
    }

    //在按下的时候会触发该方法，这个方法的作用其实是确定在一次事件流中该组件是否参与嵌套滑动
    //这个方法的返回值的作用是什么:对于与它同级的AppBarLayout而言，按下时候的返回值始终是true,
    // 会最终导致NestedScrollingChildHelper中的判断始终是true,也就是说CoordinateLayout中的
    //onNestedScrollAccepted该方法会始终被调用。但是注意了对于behavior的onStartNestedScroll
    //的放回值直接作用的对象是LayoutParams中的方法setNestedScrollAccepted。该方法的设置结果
    //会影响到LayoutParams中isNestedScrollAccepted方法的结果，而一个Behavior中的与滑动相关的
    //方法的调用之前都会去验证这个方法是否返回true，如果不是，那么后续方法是得不到调用的，也就是
    //无法实现嵌套滑动
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        Log.d(TAG, "child:" + child.getClass().getName()+"\n"
                + "directTargetChild:" + directTargetChild.getClass().getName()+"\n"
                + "target:"+target.getClass().getName()+"\n"
                +"axes:"+axes+"\n"
                +"type:"+type);
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL)!=0;
    }

    //在CoordinatorLayout中与之相关方法的调用的作用是什么?
    //该方法被回调的前提是onStartNestedScroll放回值为true.能够到达这个方法说明该View参与本次滑动事件流
    //在AppBarLayout中的Behavior中并没有实现下列方法。其实这个方法的作用就是在本次滑动事件开始前做一些
    //准备工作可以实现这个方法
    //复写recyclerView的Behavior，在初始的事件流中下列方法没有被回调
    //TODO 对于recyclerView的Behavior,后续的与滑动相关的方法有没有被回调
    @Override
    public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        Log.d(TAG, "onNestedScrollAccepted");
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        Log.d(TAG, "onNestedPreScroll-----------"+"\n"
                +"child:"+child.getClass().getName()+"\n"
                +"target:"+target.getClass().getName()+"\n"
                +"dy:"+dy+"\n"
                +"dx:"+dx+"\n"
                +"type:"+type);
    }
}
