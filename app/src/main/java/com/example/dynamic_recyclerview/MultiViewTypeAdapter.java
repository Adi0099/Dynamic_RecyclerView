package com.example.dynamic_recyclerview;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class MultiViewTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Model> dataSet;
    Context mContext;
    int total_types;
    private OnItemClick mCallback;

    public static class TextTypeViewHolder extends RecyclerView.ViewHolder {


        TextView txtType;
        CardView cardView;

        public TextTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.type);

        }

    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {


        TextView txtType;
        ImageView image;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            // get position
//            this.txtType = (TextView) itemView.findViewById(R.id.type);
            this.image = (ImageView) itemView.findViewById(R.id.background);

        }

    }

    public static class DescriptionTypeViewHolder extends RecyclerView.ViewHolder {


        TextView txtType,description;
        ImageView image;

        public DescriptionTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.type);
            this.description = (TextView) itemView.findViewById(R.id.description);
            this.image = (ImageView) itemView.findViewById(R.id.background);

        }

    }

    public MultiViewTypeAdapter(ArrayList<Model> data, Context context,OnItemClick listner) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
        this.mCallback = listner;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case Model.TEXT_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_type, parent, false);
                return new TextTypeViewHolder(view);
            case Model.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_type, parent, false);
                return new ImageTypeViewHolder(view);
            case Model.DESCRIPTIONTYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_type, parent, false);
                return new DescriptionTypeViewHolder(view);
        }
        return null;


    }


    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 0:
                return Model.TEXT_TYPE;
            case 1:
                return Model.IMAGE_TYPE;
            case 2:
                return Model.DESCRIPTIONTYPE;
            default:
                return -1;
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        final Model object = dataSet.get(listPosition);

        if (object != null) {
            switch (object.type) {
                case Model.TEXT_TYPE:
                    ((TextTypeViewHolder) holder).txtType.setText(object.text1);

                    break;
                case Model.IMAGE_TYPE:
//                    ((ImageTypeViewHolder) holder).txtType.setText(object.text1);
                    ((ImageTypeViewHolder) holder).image.setImageBitmap(object.data);

                    ((ImageTypeViewHolder) holder).image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Toast.makeText(mContext, "clicked....."+listPosition, Toast.LENGTH_SHORT).show();
                            mCallback.getPosition(listPosition);
                        }
                    });


                    break;
                case Model.DESCRIPTIONTYPE:

                    ((DescriptionTypeViewHolder) holder).txtType.setText(object.text1);
                    ((DescriptionTypeViewHolder) holder).description.setText(object.text2);
                    ((DescriptionTypeViewHolder) holder).image.setImageBitmap(object.data);

                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void getPosition(int i); //pass any things
    }

}
