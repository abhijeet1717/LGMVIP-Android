package com.codewithabhijeet.lgmcovidapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StateRVAdapter (private val  stateList:List<StateModal>) :
    RecyclerView.Adapter<StateRVAdapter.StatRVViewHolder>() {
    class StatRVViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val  stateNameTV: TextView = itemView.findViewById(R.id.idstate)
        val  casesTV: TextView = itemView.findViewById(R.id.id1cases)
        val  deathTV: TextView = itemView.findViewById(R.id.id1death)
        val  recvTV: TextView = itemView.findViewById(R.id.id1recv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatRVViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent,false)
        return StatRVViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StatRVViewHolder, position: Int) {
        val stateData = stateList[position]
        holder.casesTV.text = stateData.cases.toString()
        holder.stateNameTV.text = stateData.state
        holder.deathTV.text = stateData.deaths.toString()
        holder.recvTV.text = stateData.recv.toString()
    }

    override fun getItemCount(): Int {
        return stateList.size
    }


}