package com.example.tianwenjie.recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tianwenjie on 6/7/16.
 */
public class MultipleItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static enum ITEM_TYPE {
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private String[] mTitles;
    private String[] string = {"http://ww4.sinaimg.cn/large/7a8aed7bgw1etdsksgctqj20hs0qowgy.jpg",
                               "http://ww1.sinaimg.cn/large/610dc034jw1f4kron1wqaj20ia0rf436.jpg",
                                "http://ww2.sinaimg.cn/large/610dc034gw1f4hvgpjjapj20ia0ur0vr.jpg",
                                 "http://ac-OLWHHM4o.clouddn.com/4063qegYjlC8nx6uEqxV0kT3hn6hdqJqVWPKpdrS",
                                 "http://ww3.sinaimg.cn/large/610dc034gw1f4fkmatcvdj20hs0qo78s.jpg" ,
                                  "http://ac-OLWHHM4o.clouddn.com/DPCY44vIYPjVPKNzfHjMdXd9bk27q0i1X2nIaO8Z" ,
                                  "http://ww1.sinaimg.cn/large/610dc034jw1f4d4iji38kj20sg0izdl1.jpg",
                                  "http://ww4.sinaimg.cn/large/610dc034jw1f49s6i5pg7j20go0p043b.jpg",
                                  "http://ww3.sinaimg.cn/large/610dc034jw1f48mxqcvkvj20lt0pyaed.jpg" ,
                                   "http://ww4.sinaimg.cn/large/610dc034jw1f47gspphiyj20ia0rf76w.jpg"   } ;


    public  MultipleItemAdapter(Context context){
       /// mTitles=context.getResources().getStringArray(R.array.titles);
        mContext=context;
        mLayoutInflater=LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()){
            return  new ImageViewHolder(mLayoutInflater.inflate(R.layout.item_image, parent ,false));
        }else {
            return new TextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
        }


    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof TextViewHolder){
            ((TextViewHolder) holder).mTextView.setText(mTitles[position]);
        } else if (holder instanceof ImageViewHolder) {

            URL url = null;
            try {
                url = new URL(string[position]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


            Utils.onLoadImage(url, new Utils.OnLoadImageListener() {

                @Override
                public void OnLoadImage(Bitmap bitmap, String bitmapPath) {
                    if(bitmap!=null){
                        ImageView imageview= ((ImageViewHolder) holder).mImageView;
                        imageview.setImageBitmap(bitmap);
                    }
                }
            });


        }
        }

  /* @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal() : ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
    }*/

    @Override
    public int getItemCount() {
            return string == null ? 0 : string.length;
    }


    public static  class TextViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_view)
        TextView mTextView;

        TextViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.cv_item)
        void onItemClick() {
            Log.d("TextViewHolder", "onClick--> position = " + getPosition());
        }
    }




    public static  class ImageViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_view)
        TextView mTextView;
        @Bind(R.id.image_view)
        ImageView mImageView;

        ImageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.cv_item)
        void onItemClick() {
            Log.d("ImageViewHolder", "onClick--> position = " + getPosition());
        }

    }
}
