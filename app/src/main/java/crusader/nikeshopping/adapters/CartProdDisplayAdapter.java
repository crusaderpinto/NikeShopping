package crusader.nikeshopping.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import crusader.nikeshopping.R;
import crusader.nikeshopping.db.models.SaveItem;


public class CartProdDisplayAdapter extends RecyclerView.Adapter<CartProdDisplayAdapter.ViewHolder> {
    private List<SaveItem> mDataset;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_product_name;
        public TextView txt_product_price;
        public ImageView img_prod_snap;

        public ViewHolder(View v) {
            super(v);
            img_prod_snap = (ImageView) v.findViewById(R.id.img_prod_snap);
            txt_product_name = (TextView) v.findViewById(R.id.product_name);
            txt_product_price = (TextView) v.findViewById(R.id.product_price);
        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public CartProdDisplayAdapter(Context context, List<SaveItem> myDataset) {
        this.context = context;
        mDataset = myDataset;
    }

    @Override
    public CartProdDisplayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_preview, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,  final int position) {
        if(mDataset != null) {
            holder.txt_product_name.setText(mDataset.get(position).getUserName());
            holder.txt_product_price.setText("Rs." + mDataset.get(position).getPrice());
            if(!TextUtils.isEmpty(mDataset.get(position).getImgUrl())) {
                Picasso.with(context)
                        .load(mDataset.get(position).getImgUrl())
                        .into(holder.img_prod_snap);
            }else{
                Picasso.with(context)
                        .load(R.drawable.ic_nikelogo)
                        .into(holder.img_prod_snap);
            }
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i = new Intent(context, ProductDetailActivity.class);
//                    i.putExtra(AppConstants.ITEMID, mDataset.get(0).getItem().get(position).getItemId().get(0));
//                    i.putExtra(AppConstants.ITEMTITLE, mDataset.get(0).getItem().get(position).getTitle().get(0));
////                    ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, Pair.create((View) mFabButton, "fab"), Pair.create(appIcon, "appIcon"));
//                    context.startActivity(i);
//                }
//            });
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

//    public void appendItems(List<SaveItem> items) {
//        int count = getItemCount();
//        mDataset.get(0).getItem().addAll(items.get(0).getItem());
//        notifyItemRangeInserted(count, items.size());
//    }

}