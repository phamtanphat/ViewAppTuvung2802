package khoapham.ptp.phamtanphat.apphoctienganh2802;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import khoapham.ptp.phamtanphat.apphoctienganh2802.API.APICallback;
import khoapham.ptp.phamtanphat.apphoctienganh2802.API.Responsedata;
import khoapham.ptp.phamtanphat.apphoctienganh2802.API.model.Tuvung;
import khoapham.ptp.phamtanphat.apphoctienganh2802.API.model.Word;
import khoapham.ptp.phamtanphat.apphoctienganh2802.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ListenData {

    ListView lvWord;
    ArrayList<Word> wordsfilter = new ArrayList<>();
    WordAdapter wordAdapter,wordAdapterFilter;
    Button btnForm;
    Button btnAddWord,btnCancel;
    RelativeLayout relativeFormtrue,relativeFormfalse;
    Spinner spinner;
    String[] mangoption = {"Show_All","Show_Forgot","Show_Memorized"};
    ArrayAdapter spinnerAdapter;
    ListenData listenData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvWord = findViewById(R.id.listviewWord);
        btnForm = findViewById(R.id.buttonForm);
        btnAddWord = findViewById(R.id.buttonAddword);
        btnCancel = findViewById(R.id.buttonCancel);
        relativeFormfalse = findViewById(R.id.relativeFormfalse);
        relativeFormtrue = findViewById(R.id.relativeFormtrue);
        spinner = findViewById(R.id.spinner);
        listenData = this;

        spinnerAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,mangoption);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        btnForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeFormtrue.setVisibility(View.VISIBLE);
                relativeFormfalse.setVisibility(View.GONE);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeFormtrue.setVisibility(View.GONE);
                relativeFormfalse.setVisibility(View.VISIBLE);
            }
        });

        getWord();




    }
    private void getWord(){
        APICallback dataapi = Responsedata.getData();
        Call<Tuvung> callbackTuvung = dataapi.getTuvung();
        callbackTuvung.enqueue(new Callback<Tuvung>() {
            @Override
            public void onResponse(Call<Tuvung> call, Response<Tuvung> response) {
                Tuvung tuvung = response.body();
                wordAdapter = new WordAdapter(MainActivity.this,android.R.layout.simple_list_item_1,tuvung.getWords());
                lvWord.setAdapter(wordAdapter);
                listenData.onSuccess(tuvung.getWords());
            }

            @Override
            public void onFailure(Call<Tuvung> call, Throwable t) {
                listenData.onFailt(t.getMessage());
            }
        });
    }
    private void toggleMemorized(){
        APICallback dataapi = Responsedata.getData();
        Call<String> callbackTuvung = dataapi.toggleMemorized("5","false");
        callbackTuvung.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String ketqua = response.body();
                if (ketqua == null){
                    Toast.makeText(MainActivity.this, "Khong tồn tại từ khóa", Toast.LENGTH_SHORT).show();
                }else{
                    if (ketqua.equals("true")){
                        Toast.makeText(MainActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
    private void deleteWord(){
        APICallback dataapi = Responsedata.getData();
        Call<String> callbackTuvung = dataapi.delete("1");
        callbackTuvung.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String ketqua = response.body();
                if (ketqua == null){
                    Toast.makeText(MainActivity.this, "Khong tồn tại từ khóa", Toast.LENGTH_SHORT).show();
                }else{
                    if (ketqua.equals("true")){
                        Toast.makeText(MainActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    @Override
    public void onSuccess(final List<Word> words) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wordsfilter.clear();
                wordAdapterFilter = new WordAdapter(MainActivity.this,android.R.layout.simple_list_item_1,wordsfilter);
                lvWord.setAdapter(wordAdapterFilter);
                switch (mangoption[position]){
                    case "Show_All" :
                        wordsfilter.addAll(words);
                        wordAdapterFilter.notifyDataSetChanged();
                        break;
                    case "Show_Forgot" :
                        for (int i = 0 ; i < words.size() ; i++){
                            if (words.get(i).getIsMemorized()){
                                wordsfilter.add(words.get(i));
                            }
                        }
                        wordAdapterFilter.notifyDataSetChanged();
                        break;
                    case "Show_Memorized" :
                        for (int i = 0 ; i < words.size() ; i++){
                            if (!words.get(i).getIsMemorized()){
                                wordsfilter.add(words.get(i));
                            }
                        }
                        wordAdapterFilter.notifyDataSetChanged();
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onFailt(String e) {

    }
}
