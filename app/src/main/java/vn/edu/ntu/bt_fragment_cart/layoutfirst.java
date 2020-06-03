package vn.edu.ntu.bt_fragment_cart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.edu.ntu.bt_fragment_cart.controller.ICartController;
import vn.edu.ntu.bt_fragment_cart.model.Product;


public class layoutfirst extends Fragment{
    RecyclerView rvListProduct;
    ProductAdapter adapter;
    List<Product> listProducts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layoutfirst, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);
        addView();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.mnu_mymennu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.mnuGiohang:
                NavController navController = NavHostFragment.findNavController(layoutfirst.this);
                navController.navigate(R.id.action_layoutfirst_to_layoutsecond);
                break;
            case R.id.mnuClose:
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private  void addView(){
        FragmentActivity activity = getActivity();
        rvListProduct = activity.findViewById(R.id.rvmathang);
        rvListProduct.setLayoutManager(new LinearLayoutManager(activity));
        ICartController controller =(ICartController) activity.getApplication();
        listProducts = controller.getAProducts();
        adapter = new ProductAdapter(listProducts);
        rvListProduct.setAdapter(adapter);
    }

    //khoi tao cua recycleview
    private class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView txtName, txtPrice, txtDesc;
        ImageView imageAddToCard;
        Product p;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName        = this.itemView.findViewById(R.id.txtName);
            txtPrice       = this.itemView.findViewById(R.id.txtPrice);
            txtDesc        = this.itemView.findViewById(R.id.txtDesc);
            imageAddToCard =  this.itemView.findViewById(R.id.imvAddToCart);

            imageAddToCard.setOnClickListener(this);
        }

        public void bind(Product p)
        {
            this.p = p;
            txtName.setText(p.getName());
            txtPrice.setText(new Integer(p.getPrice()).toString());
            txtDesc.setText(p.getDesc());
        }

        @Override
        public void onClick(View v)
        {
            ICartController controller = (ICartController) getActivity().getApplication();
            if (controller.addToCart(p))
                Toast.makeText(getActivity(),
                        "Đã thêm: "+ p.getName() + " vào giỏ hàng",
                        Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getActivity(),
                        p.getName() + " đã có trong giỏ hàng",
                        Toast.LENGTH_LONG).show();
        }
    }
    //VH: viewholder
    private class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>
    {
        List<Product> listProducts;

        public ProductAdapter(List<Product> listProducts) {
            this.listProducts = listProducts;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //LayoutInflater do flater cung cap
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.product, parent,false);

            //view: Layout product da thiet ke
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            holder.bind((listProducts).get(position));
        }

        //position; vi tri cua Adapter


        @Override
        public int getItemCount() {
            return listProducts.size();
        }
    }
}
