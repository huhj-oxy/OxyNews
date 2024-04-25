package com.example.oxynews;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class HomeView extends AppCompatActivity {

    //Settings Stuff
    int mintextSize = 14;
    int maxTextSize = 36;
    int textSize = 20;
    TextView fontSizeBox;



    ArrayList<ArticleData> articleArr = new ArrayList<ArticleData>();

    ArrayList<ArticleCardModel> articleCardModels = new ArrayList<>();

    int[] articleCardImages = {R.drawable.option};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_view);

        readTsvFile();

        RecyclerView recyclerView = findViewById(R.id.cardRecyclerView);

        setUpArticleCardModels();

        //Create adapter AFTER setting up ArticleCardModels
        CardArticle_RecyclerViewAdapter adapter = new CardArticle_RecyclerViewAdapter(this,
                articleCardModels);

        //Attach adapter to our recyclerView
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void readTsvFile(){
        try(InputStream stream = getResources().openRawResource(R.raw.test)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));

            int lineCounter = 0;
            String line;
            while((line = reader.readLine()) != null) {
                if (lineCounter >= 1) {
                    String[] words = line.split("&&");
                    Log.i("XXXXX", words[0]);
                    Log.i("XXXXX", words[1]);
                    Log.i("XXXXX", words[2]);
                    Log.i("XXXXX", words[3]);
                    Log.i("XXXXX", words[4]);

                    ArticleData article = new ArticleData(words);
                    articleArr.add(article);
                }
                lineCounter++;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setUpArticleCardModels(){
        //REPLACE '10' WITH LENGTH OF 'articleCardTitles' FOR ACTUAL APP
        for(int i = 0; i < articleArr.size(); i++){
            ArticleCardModel cm = new ArticleCardModel(articleArr.get(i).getTitle(), articleArr.get(i).getAuthor(),
                    articleArr.get(i).getDateToString(), articleCardImages[0]);
            articleCardModels.add(cm);
        }

    }


    public void toSettings(View v){setContentView(R.layout.settings_menu);


//        // code for fontsize
//        fontSizeBox = findViewById(R.id.fontSizeBox);
//        fontSizeBox.setText(String.valueOf(textSize));
//        fontSizeBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    // Get the text from the EditText and parse it to an integer
//                    String inputText = fontSizeBox.getText().toString();
//                    try {
//                        int newSize = Integer.parseInt(inputText);
//                        // Update textSize with the new value
//                        textSize = newSize;
//                    } catch (NumberFormatException e) {
//                        // Handle the case where inputText is not a valid integer
//                        Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
//                    }
//                    return true; // Return true to consume the event
//                }
//                return false; // Return false if you don't consume the event
//            }
//        });

    } // end of toSettings

    public void darkOn(View v){AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);}
    public void darkOff(View v){AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);}



}// end of HomeView