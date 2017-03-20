package widget;

import android.view.View;
import android.widget.TextView;

import com.example.ProductCategory;
import com.example.R;

import butterknife.BindView;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-02-15 17:29
 * @desc TODO 类作用
 */
public class ProductCategoryItemViewHolder extends BaseViewHolder<ProductCategory>{
    private static final String TAG = "ProductCategoryItemViewHolder";

    @BindView(R.id.txt_name)
    TextView mNameTxt;

//    @Bind(R.id.badge_view)
//    BGABadgeFrameLayout mBadgeView;

    public ProductCategoryItemViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(ProductCategory category) {
        mNameTxt.setText(category.getName());

//        int count = ShoppingCart.getInstance().getQuantityForCategory(category);
//        if (count > 0) {
//            mBadgeView.showTextBadge(String.valueOf(count));
//        } else {
//            mBadgeView.hiddenBadge();
//        }
    }
}
