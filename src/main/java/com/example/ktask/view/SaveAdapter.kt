package com.example.ktask.view

import android.arch.lifecycle.LiveData
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.ktask.R
import com.example.ktask.model.ModelPlaces
import com.example.ktask.model.SharedPrefManager


class SaveAdapter(
    private val context: Context,
    private var allData: List<ModelPlaces>,var map: HashMap<String,ModelPlaces>, var task2: TaskHandler2

) : RecyclerView.Adapter<SaveAdapter.ViewHolderSavePlace>() {


    interface TaskHandler2 {
        fun itemClick(pos: Int, modelPlaces: ModelPlaces, status: Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSavePlace {
        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.save_places_item_layout, parent, false)

        return ViewHolderSavePlace(view)
    }

    override fun getItemCount(): Int {
        Log.e("list size", "" + allData.size)
        return allData.size
    }

    override fun onBindViewHolder(holder1: ViewHolderSavePlace, position: Int) {

        var modelPlaces = ModelPlaces()

        var place_id = SharedPrefManager.getmInstance(context)?.getID()
        var place_name = SharedPrefManager.getmInstance(context)?.getName()
        var state = SharedPrefManager.getmInstance(context)?.getState()
        var position1 = SharedPrefManager.getmInstance(context)?.getPosition()

        map.get(modelPlaces.placeId)
        Log.e("key--",""+map.get(modelPlaces.placeId))


        if((place_id==modelPlaces.placeId)  && (state == true)){
            holder1.image.id
            holder1.image.setImageResource(R.color.colorAccent)
        }
        else
           // holder1.image.setImageResource(R.color.primary_dark_material_dark)

        task2 as TaskHandler2
        modelPlaces = allData.get(position)
        holder1.textPlaceID.setText(modelPlaces.placeId)
        holder1.textPlaceName.setText(modelPlaces.placeName)




        holder1.image.setOnClickListener {
            modelPlaces = allData.get(position)
            task2.itemClick(position, modelPlaces, true)
            holder1.image.setImageResource(R.color.primary_dark_material_dark)
        }
    }

    fun setDataList(list: List<ModelPlaces>) {
        this.allData = list
        notifyDataSetChanged()

    }

    class ViewHolderSavePlace(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View?) {
            image.setOnClickListener(this)

        }

        var textPlaceID = itemView.findViewById<TextView>(R.id.tv_id)
        var textPlaceName = itemView.findViewById<TextView>(R.id.tv_name)
        var image = itemView.findViewById<ImageView>(R.id.img2)
    }
}