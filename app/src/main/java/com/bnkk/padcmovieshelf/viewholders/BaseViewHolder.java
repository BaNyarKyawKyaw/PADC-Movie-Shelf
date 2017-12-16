package com.bnkk.padcmovieshelf.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by E5-575G on 12/12/2017.
 */

public abstract class BaseViewHolder<W> extends RecyclerView.ViewHolder {

    private W mData;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setData(W data);
}
