package com.lm25ttd.androidsearch.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.lm25ttd.androidsearch.R;
import com.lm25ttd.androidsearch.adapters.WordsListAdapter;
import com.lm25ttd.androidsearch.repository.FakeRepository;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private SearchView searchView;
    private MenuItem searchMenuItem;
    private ListView wordsListView;
    private WordsListAdapter wordsListAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWordsList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        searchMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        wordsListAdapter.getFilter().filter(newText);

        return true;
    }

    private void initWordsList() {
        List<String> words = new FakeRepository().getWordsList();

        wordsListView = (ListView) findViewById(R.id.list_view);
        wordsListAdapter = new WordsListAdapter (this, words);

        wordsListView.setAdapter(wordsListAdapter);
        wordsListView.setTextFilterEnabled(false);
    }
}
