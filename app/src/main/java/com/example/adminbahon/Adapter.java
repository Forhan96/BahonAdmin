package com.example.adminbahon;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {

    private List<ModelClass>modelClassList;

    public Adapter(List<ModelClass> modelClassList) {
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int position) {

        String resource =modelClassList.get(position).getImageResource();
        String title =modelClassList.get(position).getTitle();
        String body =modelClassList.get(position).getBody();

        viewholder.setData(resource,title,body);

    }

    @Override
    public int getItemCount() {
        
        return modelClassList.size();
    }

    static class Viewholder extends RecyclerView.ViewHolder{
         private ImageView imageView;
         private TextView title;
         private TextView body;


         public Viewholder(@NonNull View itemView) {
             super(itemView);

             imageView=itemView.findViewById(R.id.imageView);
             title=itemView.findViewById(R.id.title);
             body=itemView.findViewById(R.id.body);

         }
         void setData(String resource, String titleText, String bodyText){

             Picasso.get().load(resource).into(imageView);
             title.setText(titleText);
             body.setText(bodyText);





             }
         }
     }

