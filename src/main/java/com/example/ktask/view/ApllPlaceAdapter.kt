package com.example.ktask.view

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
import java.util.*

class ApllPlaceAdapter(private var context: Context, var list:List<ModelPlaces>,var task1:TaskHandler1) :
    RecyclerView.Adapter<ViewHolderPlace>() {

    var modelPlaces1 = ModelPlaces()
    var listSave = ArrayList<ModelPlaces>()
    var pos: Int = 0
    interface TaskHandler1{fun itemClick(pos:Int,modelPlaces: ModelPlaces,status:Boolean)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPlace {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.all_places_item_layout, parent, false) as View

        return ViewHolderPlace(view)
    }

    override fun getItemCount(): Int {
        Log.e("list count", "" + list.size)
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolderPlace, position: Int) {

        var modelPlaces = list.get(position)
        var place_id = SharedPrefManager.getmInstance(context)?.getID()
        var place_name = SharedPrefManager.getmInstance(context)?.getName()
        var state = SharedPrefManager.getmInstance(context)?.getState()
        var position1 = SharedPrefManager.getmInstance(context)?.getPosition()
        if((place_id==modelPlaces.placeId) && (state == true)){
            holder.imageSave.id
            holder.imageSave.setImageResource(R.color.colorAccent)
        }
        else
            holder.imageSave.setImageResource(R.color.primary_dark_material_dark)


        task1  as TaskHandler1
        this.task1=task1
        holder.placeIdText.setText(modelPlaces.placeId)
        holder.placeNameText.setText(modelPlaces.placeName)

        holder.imageSave.setOnClickListener {
            holder.imageSave.setImageResource(R.color.colorAccent)

            var sortedList = list.sortedBy { place_id }
            modelPlaces1 = sortedList.get(position)

            Log.e("", "save test  " + position + modelPlaces1)
            task1.itemClick(position,modelPlaces1,true)

        }
    }

    fun active(v : ImageView){

        v.setImageResource(R.color.colorAccent)

    }
    fun inActive(v : ImageView){
        v.setImageResource(R.color.primary_dark_material_dark)

    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

//    fun setPLaceData(pos: Int, modelPlaces: ModelPlaces): ModelPlaces {
//
//        this.modelPlaces1 = modelPlaces
//
//        notifyDataSetChanged()
//        return modelPlaces1
//
//    }
//
//    fun getPlaceData(): ModelPlaces {
//
//        setPLaceData(pos, modelPlaces1)
//        return modelPlaces1
//    }
}
    class ViewHolderPlace(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        override fun onClick(v: View?) {

            var pos: Int = adapterPosition

            imageSave.setOnClickListener(this)

        }

        var placeIdText = itemView?.findViewById<TextView>(R.id.tv_placeId)
        var placeNameText = itemView?.findViewById<TextView>(R.id.tv_placeName)
        var imageSave = itemView?.findViewById<ImageView>(R.id.img1)
    }
