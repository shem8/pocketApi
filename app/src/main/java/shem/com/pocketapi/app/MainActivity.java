package shem.com.pocketapi.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.shem.pocketapi.PocketApi;
import com.shem.pocketapi.PocketService;
import com.shem.pocketapi.data.Article;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView username;
    private View login;
    private View logout;
    private View fetch;
    private RecyclerView articlesList;
    private ArticlesAdapted adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        logout = findViewById(R.id.logout);
        fetch = findViewById(R.id.fetch);
        username = (TextView) findViewById(R.id.username);
        articlesList = (RecyclerView) findViewById(R.id.articles);

        articlesList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ArticlesAdapted();
        articlesList.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        handleLoginStatus(PocketApi.isLoggedIn(this));
    }

    public void login(View v) {
        PocketApi.login(this);
    }

    public void logout(View v) {
        PocketApi.logout(this);
        handleLoginStatus(false);
    }

    public void fetch(View v) {
        PocketApi.getArticles(this, new Callback<PocketService.ArticlesMap>() {
            @Override
            public void onResponse(Call<PocketService.ArticlesMap> call, Response<PocketService.ArticlesMap> response) {
                adapter.setItems(response.body().list.values());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PocketService.ArticlesMap> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error while getting articles: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PocketApi.LOGIN_REQUEST_CODE) {
            handleLoginStatus(PocketApi.isLoggedIn(this));
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleLoginStatus(boolean loggedIn) {
        login.setEnabled(!loggedIn);
        logout.setEnabled(loggedIn);
        fetch.setEnabled(loggedIn);

        if (loggedIn) {
            username.setText(PocketApi.getUsername(this));
        } else {
            username.setText("");
            adapter.clear();
        }
    }

    public class ArticlesAdapted extends RecyclerView.Adapter<ArticleViewHolder> {

        private ArrayList<Article> items = new ArrayList<>();

        @Override
        public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ArticleViewHolder(new TextView(parent.getContext()));
        }

        @Override
        public void onBindViewHolder(ArticleViewHolder holder, int position) {
            holder.textView.setText(items.get(position).resolved_title);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public void setItems(Collection<Article> items) {
            this.items = new ArrayList<>(items);
        }

        public void clear() {
            items.clear();
            notifyDataSetChanged();
        }
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}
