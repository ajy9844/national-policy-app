package org.techtown.policy_briefing_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder>{


    private ArrayList<result_data> arrayList;
    String short_text;
    private ArrayList<Local> list;

    static String data;



    static result_data rd;

    public static ArrayList<result_data> i_like_list;

    public MainAdapter(ArrayList<result_data> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_list,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.CustomViewHolder holder, int position) {

        holder.service_name.setText(arrayList.get(position).getService_name());
        String dsp_text = arrayList.get(position).getService_dsp();


        if(dsp_text.length() >50){
            short_text = dsp_text.substring(0,51)+"....";
        }
        else{
            short_text = dsp_text;
        }
        holder.service_dsp.setText(short_text);
        holder.age_info.setText(arrayList.get(position).getAge_info());

        // 롱클릭시 관심 추가
        holder.itemView.setTag(position);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v){
                //함수 (holder.getAdapterPosition()); //함수는 int position을 가짐
                //
                //"관심목록에 추가하였습니다."

                String name = "i_like";
                String para;
                String txt = arrayList.get(holder.getAdapterPosition()).getService_dsp();
                String txt_temp;

                if(txt.length() >10){
                    txt_temp = txt.substring(0,10);
                    para = txt_temp;
                }
                else{
                    para = txt;
                }

                try{
                    String rst = new Task().execute(name,para).get();
                    Toast toast = Toast.makeText(v.getContext(),"관심목록에 추가하였습니다.",Toast.LENGTH_LONG);
                    toast.show();

                    data = rst;

                }catch (Exception e){
                    e.printStackTrace();
                }


                return true;
            }

        });





    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size():0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView iv_profile;
        protected TextView service_name;
        protected TextView service_dsp;
        protected TextView age_info;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            //this.iv_profile = (ImageView)itemView.findViewById(R.id.iv_profile);
            this.service_name = (TextView)itemView.findViewById(R.id.service_name);
            this.service_dsp = (TextView)itemView.findViewById(R.id.service_dsp);
            this.age_info = (TextView)itemView.findViewById(R.id.age_info);
        }
    }

    public static String get_rst(){
        return data;
    }

}
