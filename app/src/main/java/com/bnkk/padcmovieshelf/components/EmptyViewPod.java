package com.bnkk.padcmovieshelf.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bnkk.padcmovieshelf.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by E5-575G on 12/12/2017.
 */

public class EmptyViewPod extends RelativeLayout {

    @BindView(R.id.iv_empty_image)
    ImageView ivEmptyImage;

    @BindView(R.id.tv_no_data)
    TextView tvNoData;

    public EmptyViewPod(Context context) {
        super(context);
    }

    public EmptyViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void setEmptyData(int emptyImageID, String emptyMsg) {
        ivEmptyImage.setImageResource(emptyImageID);
        tvNoData.setText(emptyMsg);
    }

    public void setEmptyData(String emptyMsg) {
        tvNoData.setText(emptyMsg);
    }
}
