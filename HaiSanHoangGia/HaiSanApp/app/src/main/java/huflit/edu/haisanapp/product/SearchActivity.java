package huflit.edu.haisanapp.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import huflit.edu.haisanapp.R;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.SearchCallback{

    RecyclerView rvSearch;
    SearchAdapter searchAdapter;

    private SearchView searchViewSp;

    ArrayList<Product> lstPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rvSearch = findViewById(R.id.rvListSearch);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvSearch.setLayoutManager(linearLayoutManager);

        lstPro = ProductDataQuery.getAll(this);
        searchAdapter = new SearchAdapter(lstPro);
        searchAdapter.setShowCallback(this);
        rvSearch.setAdapter(searchAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rvSearch.addItemDecoration(itemDecoration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchViewSp = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchViewSp.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchViewSp.setMaxWidth(Integer.MAX_VALUE);

        searchViewSp.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public void onBackPressed() {
        if(!searchViewSp.isIconified()){
            searchViewSp.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onItemClick(Product product, int position) {
        Intent i = new Intent(this, ShowDetailActivity.class);
        i.putExtra("proId",product.getId());
        i.putExtra("proName",product.getName());
        i.putExtra("imagePro",product.getImagepro());
        i.putExtra("proPrice",product.getGia());
        i.putExtra("proMota",product.getMota());
        startActivity(i);
    }
}