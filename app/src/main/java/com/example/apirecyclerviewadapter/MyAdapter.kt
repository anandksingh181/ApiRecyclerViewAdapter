package com.example.apirecyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apirecyclerviewadapter.databinding.EachItemBinding
import com.squareup.picasso.Picasso

class MyAdapter(val productArrayList : List<Product>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(var binding: EachItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(EachItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(productArrayList[position].thumbnail).into(holder.binding.image);
        holder.binding.title.text = productArrayList[position].title

    }
}