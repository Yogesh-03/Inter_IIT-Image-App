package com.example.photox;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerImageAdapter extends RecyclerView.Adapter<RecyclerImageAdapter.ViewHolder> {

    Context context;
    ArrayList<ImageModel> arrImages;
    RecyclerImageAdapter(Context context, ArrayList<ImageModel> arrImages){
        this.context =context;
        this.arrImages = arrImages;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(context).inflate(R.layout.image_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ImageModel model = arrImages.get(position);
       // holder.imageView.setImageBitmap(arrImages.get(position).img);
        holder.imageView.setImageURI(arrImages.get(position).img);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ImageEditActivity.class);
                intent.putExtra("image", model.getImg().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrImages.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView imageView;

        public ViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.importedImage);

        }

    }
}
