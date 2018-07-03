package starwars.lovy.com.starwarsdemo.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import starwars.lovy.com.starwarsdemo.R;
import starwars.lovy.com.starwarsdemo.components.CharactersPresenter;
import starwars.lovy.com.starwarsdemo.contract.CharactersContract;
import starwars.lovy.com.starwarsdemo.databinding.CellCharacterBinding;
import starwars.lovy.com.starwarsdemo.model.Results;

/**
 * Created by Lovy on 02-07-2018.
 */

public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Results> resultsList;
    CharactersContract.Presenter mPresenter;

    public RecyclerViewAdapter(CharactersContract.Presenter presenter){
        mPresenter = presenter;
    }

    public void setData(List<Results> results) {
        resultsList = results;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        CellCharacterBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.cell_character, parent, false);

        ViewHolder viewHolder = new ViewHolder(binding);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Results results = resultsList.get(position);
        holder.bind(results);
        holder.bind(mPresenter);

    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CellCharacterBinding cellItemBinding;

        public ViewHolder(CellCharacterBinding cellCharacterBinding) {
            super(cellCharacterBinding.getRoot());
            cellItemBinding = cellCharacterBinding;
        }

        public void bind(Results obj) {
            cellItemBinding.setCharacter(obj);
            cellItemBinding.executePendingBindings();
        }

        public void bind(CharactersContract.Presenter presenter) {
            cellItemBinding.setPresenter((CharactersPresenter) presenter);
        }

    }

}