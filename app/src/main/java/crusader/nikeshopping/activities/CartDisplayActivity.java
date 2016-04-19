package crusader.nikeshopping.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import crusader.nikeshopping.R;
import crusader.nikeshopping.adapters.CartProdDisplayAdapter;
import crusader.nikeshopping.db.DBHelper;
import crusader.nikeshopping.db.models.SaveItem;

public class CartDisplayActivity extends BaseActivity {

    private RecyclerView rvCartProd;
    private TextView tvEmptyView;
    CartProdDisplayAdapter cartProdDisplayAdapter;
    List<SaveItem> myDataset;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(this);
        myDataset = new ArrayList<>();
        retrieveCartDbData();
        cartProdDisplayAdapter = new CartProdDisplayAdapter(this, myDataset);
        rvCartProd.setAdapter(cartProdDisplayAdapter);
        if (cartProdDisplayAdapter.getItemCount() == 0) {
            tvEmptyView.setVisibility(View.VISIBLE);
        } else {
            tvEmptyView.setVisibility(View.GONE);
        }
    }

    private void retrieveCartDbData() {
        SaveItem saveItem = new SaveItem();
        boolean checkIfValuesExist = saveItem.numberOfRows(dbHelper.getDb()) > 0;

        if (checkIfValuesExist) {
            ArrayList<SaveItem> allData = saveItem.getAllData(dbHelper.getDb());
            if (allData.size() > 0) {
                //Data exist
                myDataset.addAll(allData);
            }
        }
    }

    @Override
    public void initSetContentView() {
        setContentView(R.layout.activity_cart_display);
    }

    @Override
    public void initComponents() {
        tvEmptyView = (TextView) findViewById(R.id.tv_empty_view);
        rvCartProd = (RecyclerView) findViewById(R.id.rv_cart_detail_list);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvCartProd.setLayoutManager(llm);
    }

    @Override
    public void initListeners() {

    }
}
