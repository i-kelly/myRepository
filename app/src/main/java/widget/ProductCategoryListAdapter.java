package widget;

import android.view.View;

import com.example.ProductCategory;
import com.example.R;


public class ProductCategoryListAdapter extends BaseListAdapter<ProductCategory, ProductCategoryItemViewHolder> {

    @Override
    protected int getViewLayoutId() {
        return R.layout.layout_product_category_item;
    }

    @Override
    protected ProductCategoryItemViewHolder createViewHolder(View view) {
        return new ProductCategoryItemViewHolder(view);
    }

    @Override
    protected void showData(ProductCategoryItemViewHolder holder, int position) {
        ProductCategory category = getItem(position);
        holder.bind(category);
    }
}
