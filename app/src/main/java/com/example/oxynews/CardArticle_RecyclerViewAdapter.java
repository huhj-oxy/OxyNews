package com.example.oxynews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardArticle_RecyclerViewAdapter extends RecyclerView.Adapter<CardArticle_RecyclerViewAdapter.MyViewHolder> {


    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<ArticleCardModel> articleCardModels;
    public CardArticle_RecyclerViewAdapter(Context context, ArrayList<ArticleCardModel> articleCardModels,
                                           RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.articleCardModels = articleCardModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public CardArticle_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //INFLATES LAYOUT AND GIVES LOOK TO EACH ROW/ITEM

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_card, parent, false);

        return new CardArticle_RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CardArticle_RecyclerViewAdapter.MyViewHolder holder, int position) {
        //ASSIGNING VALUES TO EACH ROW/ITEM AS IT COMES BACK ONTO THE SCREEN
        holder.cardArticleTitle.setText(articleCardModels.get(position).getCardTitle());
        holder.cardArticleAuthor.setText(articleCardModels.get(position).getCardAuthor());
        holder.cardArticleDate.setText(articleCardModels.get(position).getCardDate());
        holder.imageView.setImageResource(articleCardModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        //WANTS TO KNOW THE NUMBER OF ITEMS THAT YOU WANT TO BE DISPLAYED
        return articleCardModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //GRABBING VIEWS FROM 'recycler_view_card.xml' AND ASSIGNING THEM VARIABLES
        //kinda like onCreate method

        float titleSize;
        float dateAuthorSize;

        ImageView imageView;
        TextView cardArticleTitle, cardArticleAuthor, cardArticleDate;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            cardArticleTitle = itemView.findViewById(R.id.cardTitle);
            cardArticleAuthor = itemView.findViewById(R.id.cardAuthor);
            cardArticleDate = itemView.findViewById(R.id.cardDate);


            //font size stuff for cards
            // Get the current text size from the TextSizeSingleton
            float currentTextSize = TextSizeSingleton.getInstance().getCurrentTextSize();


            if(currentTextSize > 30f){titleSize = 30f;}
            else {titleSize = currentTextSize;}
            cardArticleTitle.setTextSize(titleSize);

            dateAuthorSize = titleSize - 10;

            cardArticleAuthor.setTextSize(dateAuthorSize);
            cardArticleDate.setTextSize(dateAuthorSize);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }

    }

}
