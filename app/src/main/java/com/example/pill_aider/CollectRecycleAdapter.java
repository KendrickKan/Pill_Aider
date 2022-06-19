package com.example.pill_aider;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pill_aider.Entity.Reminder;

import java.util.ArrayList;

public class CollectRecycleAdapter extends RecyclerView.Adapter<CollectRecycleAdapter.myViewHodler> {
    private Context context;
    private ArrayList<Reminder> goodsEntityList;

    //创建构造函数
    public CollectRecycleAdapter(Context context, ArrayList<Reminder> goodsEntityList) {
        //将传递过来的数据，赋值给本地变量
        this.context = context;//上下文
        this.goodsEntityList = goodsEntityList;//实体类数据ArrayList
    }

    /**
     * 创建viewhodler，相当于listview中getview中的创建view和viewhodler
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public myViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建自定义布局
        View itemView = View.inflate(context, R.layout.item_list, null);
        return new myViewHodler(itemView);
    }

    /**
     * 绑定数据，数据与view绑定
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(myViewHodler holder, int position) {
        //根据点击位置绑定数据
        Reminder data = goodsEntityList.get(position);
//        holder.mItemGoodsImg;
        holder.item_name.setText(data.getItem_name());
        holder.num_day.setText(String.valueOf(data.getNum_day()));
        holder.dasage_per_time.setText(String.valueOf(data.getDasage_per_time()));
        if(data.getItem_type()==1){
            holder.item_type.setText("片");
        }
        else if(data.getItem_type()==2){
            holder.item_type.setText("粒");
        }
        else{
            holder.item_type.setText("毫升");
        }
        if(data.getItem_time()==1){
            holder.item_time.setText("饭前服用");
        }
        else if(data.getItem_time()==2){
            holder.item_time.setText("饭中服用");
        }
        else{
            holder.item_time.setText("饭后服用");
        }
        if(data.getItem_rem()==1){
            holder.item_rem.setText("响铃");
        }
        else if(data.getItem_rem()==2){
            holder.item_rem.setText("振动");
        }
        else{
            holder.item_rem.setText("响铃并振动");
        }
        holder.notice.setText(data.getNotice());
    }

    /**
     * 得到总条数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return goodsEntityList.size();
    }

    //自定义viewhodler
    class myViewHodler extends RecyclerView.ViewHolder {
//        private ImageView mItemGoodsImg;
        private TextView item_name, num_day, dasage_per_time, item_type, item_time, item_rem, notice;
//        private TextView mItemGoodsName;
//        private TextView mItemGoodsPrice;

        public myViewHodler(View itemView) {
            super(itemView);
            item_name = (TextView) itemView.findViewById(R.id.textView);
            num_day = (TextView) itemView.findViewById(R.id.textView37);
            dasage_per_time = (TextView) itemView.findViewById(R.id.textView39);
            item_type = (TextView) itemView.findViewById(R.id.textView40);
            item_time = (TextView) itemView.findViewById(R.id.textView35);
            item_rem = (TextView) itemView.findViewById(R.id.textView36);
            notice = (TextView) itemView.findViewById(R.id.textView41);
//            mItemGoodsImg = (ImageView) itemView.findViewById(R.id.item_goods_img);
//            mItemGoodsName = (TextView) itemView.findViewById(R.id.textView);
//            mItemGoodsPrice = (TextView) itemView.findViewById(R.id.textView2);
            //点击事件放在adapter中使用，也可以写个接口在activity中调用
            //方法一：在adapter中设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //可以选择直接在本位置直接写业务处理
                    //Toast.makeText(context,"点击了xxx",Toast.LENGTH_SHORT).show();
                    //此处回传点击监听事件
                    if(onItemClickListener!=null){
                        onItemClickListener.OnItemClick(v, goodsEntityList.get(getLayoutPosition()));
                    }
                }
            });
        }
    }

    /**
     * 设置item的监听事件的接口
     */
    public interface OnItemClickListener {
        /**
         * 接口中的点击每一项的实现方法，参数自己定义
         *
         * @param view 点击的item的视图
         * @param data 点击的item的数据
         */
        public void OnItemClick(View view, Reminder data);
    }

    //需要外部访问，所以需要设置set方法，方便调用
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}