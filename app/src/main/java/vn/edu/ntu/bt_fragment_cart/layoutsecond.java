package vn.edu.ntu.bt_fragment_cart;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.edu.ntu.bt_fragment_cart.controller.ICartController;
import vn.edu.ntu.bt_fragment_cart.model.Product;

public class layoutsecond extends Fragment implements View.OnClickListener {

    TextView txtShoppingCart;
    Button btnRemove, btnCancel, btnBuy;
    int number;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layoutsecond, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addViews();
        view.findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(layoutsecond.this)
                        .navigate(R.id.action_layoutsecond_to_layoutfirst);
            }
        });
    }

    private void addViews()
    {
        FragmentActivity activity = getActivity();
        txtShoppingCart = activity.findViewById(R.id.txtShoppingCart );
        btnRemove = activity.findViewById(R.id.remove);
        btnCancel = activity.findViewById(R.id.btnCancel);
        btnBuy = activity.findViewById(R.id.btnBuySomeThing);

        btnBuy.setOnClickListener(this);
        btnRemove.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        showShoppingCart();
    }

    private void showShoppingCart()
    {
        FragmentActivity activity = getActivity();
        ICartController controller = (ICartController) activity.getApplication();
        List<Product> shoppingCart = controller.getShoppingCart();
        StringBuilder builder = new StringBuilder();
        for (Product p:shoppingCart)
        {
            builder.append(p.getName()).append("\t\t\t ")
                    .append(p.getPrice())
                    .append("\n");
        }

        if (builder.length()>0) {
            txtShoppingCart.setText(builder.toString());
            this.number = controller.getShoppingCart().size();
        }
        else
            txtShoppingCart.setText("Không có mặt hàng nào trong giỏ hàng");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.btnCancel:
                    deleteShopingCart();
                    txtShoppingCart.setText("Đã hủy đơn hàng");
                    Toast.makeText(getActivity(), "Đã hủy đơn hàng", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnBuySomeThing:
                if (number > 0)
                {
                    deleteShopingCart();
                    txtShoppingCart.setText("Mua hàng thành công");
                    Toast.makeText(getActivity(), "Mua hàng thành công", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void deleteShopingCart(){
        FragmentActivity activity = getActivity();
        ICartController controller = (ICartController) activity.getApplication();
       controller.clearShoppingCart();
        this.number = 0;
    }
}
