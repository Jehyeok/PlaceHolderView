package com.mindorks.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mindorks.butterknifelite.ButterKnifeLite;
import com.mindorks.butterknifelite.annotations.BindView;
import com.mindorks.butterknifelite.annotations.OnClick;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;
import com.mindorks.test.swipe.TinderCard;

public class ActivityTinder extends AppCompatActivity {

    private static final String TAG = "ActivityTinder";

    @BindView(R.id.swipeView)
    private SwipePlaceHolderView mSwipView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinder_swipe);
        ButterKnifeLite.bind(this);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mSwipView.disableTouchSwipe();
        mSwipView.addItemRemoveListener(new ItemRemovedListener() {

            @Override
            public void onItemRemoved(int count) {
                Log.d(TAG, "onItemRemoved: " + count);
                if(count == 0){
                    mSwipView.addView(new TinderCard("hi1"))
                        .addView(new TinderCard("hi2"))
                        .addView(new TinderCard("hi3"))
                        .addView(new TinderCard("hi4"))
                        .addView(new TinderCard("hi5"))
                        .addView(new TinderCard("hi6"))
                        .addView(new TinderCard("hi7"))
                        .addView(new TinderCard("hi8"))
                        .addView(new TinderCard("hi9"))
                        .addView(new TinderCard("hi10"));
                }
            }
        });
        mSwipView.getBuilder()
//                .setSwipeType(SwipePlaceHolderView.SWIPE_TYPE_VERTICAL)
//                .setDisplayReverse(true)
                .setDisplayViewCount(3)
                .setIsUndoEnabled(true)
                .setWidthSwipeDistFactor(15)
                .setHeightSwipeDistFactor(20)
                .setSwipeDecor(new SwipeDecor()
//                        .setMarginTop(300)
//                        .setMarginLeft(100)
//                        .setViewGravity(Gravity.TOP)
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view));

        mSwipView.addView(new TinderCard("hi1"))
                .addView(new TinderCard("hi2"))
                .addView(new TinderCard("hi3"))
                .addView(new TinderCard("hi4"))
                .addView(new TinderCard("hi5"))
                .addView(new TinderCard("hi6"))
                .addView(new TinderCard("hi7"))
                .addView(new TinderCard("hi8"))
                .addView(new TinderCard("hi9"))
                .addView(new TinderCard("hi10"));
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(1000);
                    mSwipView.enableTouchSwipe();
//                    mSwipView.lockViews();
//                    Thread.currentThread().sleep(4000);
//                    mSwipView.unlockViews();
//                    Thread.currentThread().sleep(4000);
//                    mSwipView.lockViews();
//                    Thread.currentThread().sleep(4000);
//                    mSwipView.unlockViews();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @OnClick(R.id.rejectBtn)
    private void onRejectClick(){
        mSwipView.doSwipe(false);
    }

    @OnClick(R.id.acceptBtn)
    private void onAcceptClick(){
        mSwipView.doSwipe(true);
    }

    @OnClick(R.id.undoBtn)
    private void onUndoClick(){
        mSwipView.undoLastSwipe();
    }
}
