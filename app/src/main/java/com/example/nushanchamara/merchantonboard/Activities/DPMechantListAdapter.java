package com.example.nushanchamara.merchantonboard.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nushanchamara.merchantonboard.Models.DPMerchantImageUploads;
import com.example.nushanchamara.merchantonboard.R;

import java.util.ArrayList;

/**
 * Created by user1 on 3/2/2018.
 */

public class DPMechantListAdapter extends RecyclerView.Adapter<DPMechantListAdapter.MyHolder> {
//    private ImageLoader imageLoader;
    private Context context;
    ArrayList<DPMerchantImageUploads> imageUploads;

    int num=1;
    public DPMechantListAdapter(ArrayList<DPMerchantImageUploads> imageUploads, Context context) {
        //Getting all the superheroes
        this.imageUploads = imageUploads;
        this.context = context;
    }
    @Override
    public int getItemCount() {
        Log.d("item_count",imageUploads.size()+" ");
            return imageUploads.size();


    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dp_merchant_list_view, null);
        MyHolder viewHolder = new MyHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final DPMerchantImageUploads imageUpload = imageUploads.get(position);
//        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
//        imageLoader.get(parkedVehicle.getImageUrl(),ImageLoader.getImageListener(holder.img_listVehicle,R.mipmap.ic_launcher_round, android.R.drawable.ic_menu_camera));

//        holder.img_listVehicle.setImageUrl(parkedVehicle.getImageUrl(),imageLoader);
        holder.tv_companyName.setText(imageUpload.getCompanyName());
        holder.tv_businessReg.setText(imageUpload.getBrNumber());

//        holder.btn_timerval.setText(parkedVehicle.getTimerVal());
        holder.btn_submit.setText("Edit");
        holder.btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(context,DPCompanyDetailsActivity.class);
                i.putExtra("company_name",imageUpload.getCompanyName());
                i.putExtra("br_number",imageUpload.getBrNumber());
                i.putExtra("br_image",imageUpload.getImgBr());
                i.putExtra("nic_frontimage",imageUpload.getNicFrotimg());
                i.putExtra("nic_backimage",imageUpload.getNicBackimg());
                i.putExtra("outlet_image",imageUpload.getImgOutlet());
                i.putExtra("owner_image",imageUpload.getImgOwner());
                context.startActivity(i);
            }
        });
    }

    class MyHolder extends RecyclerView.ViewHolder {
//        public CirculerNetworkImageView img_listVehicle;
        public TextView tv_companyName,tv_businessReg;
        public Button btn_submit;
        ItemClickListener itemClickListener;

        public MyHolder(View itemView) {
            super(itemView);
//            img_listVehicle = (CirculerNetworkImageView)itemView.findViewById(R.id.img_listVehicle);
            tv_companyName = (TextView)itemView.findViewById(R.id.tv_companyName);
            tv_businessReg=(TextView) itemView.findViewById(R.id.tv_businessReg);
            btn_submit = (Button)itemView.findViewById(R.id.btn_submit);


        }

    }

}
