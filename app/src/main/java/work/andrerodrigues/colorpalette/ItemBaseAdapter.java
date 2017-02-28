package work.andrerodrigues.colorpalette;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Andre on 26/02/2017.
 */

public class ItemBaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item> list;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private class VHItem extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView tvHex;
        public TextView tvIndex;

        public VHItem(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.card_view);
            tvHex = (TextView) v.findViewById(R.id.tvHex);
            tvIndex = (TextView) v.findViewById(R.id.tvIndex);
        }
    }
    private class VHHeader extends RecyclerView.ViewHolder {
        public TextView tvGroupName;

        public VHHeader(View v) {
            super(v);
            tvGroupName = (TextView) v.findViewById(R.id.tvGroupName);
        }
    }

    public ItemBaseAdapter(List<Item> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            //inflate your layout and pass it to view holder
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            VHItem vh = new VHItem(v);
            return vh;
        } else if (viewType == TYPE_HEADER) {
            //inflate your layout and pass it to view holder
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_group, parent, false);
            VHHeader vh = new VHHeader(v);
            return vh;
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VHItem) {
            ((VHItem) holder).cardView.setCardBackgroundColor(Color.parseColor(list.get(position).getHex()));
            ((VHItem) holder).tvHex.setText(list.get(position).getHex());
            ((VHItem) holder).tvIndex.setText(list.get(position).getIndex());
            //cast holder to VHItem and set data
        } else if (holder instanceof VHHeader) {
            ((VHHeader) holder).tvGroupName.setText(MainActivity.GROUP_NAME);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return list.get(position) == null;
    }
}