package com.example.ktask.view

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.ktask.R
import com.example.ktask.model.ModelPlaces
import com.example.ktask.model.SharedPrefManager
import com.example.ktask.viewmodel.PlaceViewModel
import kotlinx.android.synthetic.main.fragment_save_places.view.*
import java.lang.Exception

class SavedPlaces : Fragment()  {

    lateinit var vm : PlaceViewModel
    var list  = ArrayList<ModelPlaces>()
    private var recycler1: RecyclerView? = null
    lateinit var adapterSave: SaveAdapter
    var modelPlaces = ModelPlaces()
    var map =HashMap<String,ModelPlaces>()
//    lateinit var vm1 : PlaceViewModel1

    companion object {
        fun newInstance(): SavedPlaces {
            return SavedPlaces()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = activity.run{ViewModelProviders.of(this!!).get(PlaceViewModel::class.java)} ?: throw Exception("invalid model")
        getAllPlaces()
//         vm.select().observe(this,Observer<List<ModelPlaces>>(){
//
//
//
//         })

           // Toast.makeText(context,"save place screen",Toast.LENGTH_LONG).show()

    }
        private fun showList(allData: List<ModelPlaces>?) {
        Toast.makeText(context,"hi kotlin "+allData, Toast.LENGTH_LONG).show()
        Log.e("allData",""+allData)
    }
    override fun onAttach(context: Context?) {
        super.onAttach(context)


    }
    override fun onDetach() {
        super.onDetach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_save_places, container, false)
        var layoutManager: LinearLayoutManager? = null

        recycler1 = view.findViewById(R.id.recycler1)

//        vm.select().observe(this,Observer<List<ModelPlaces>>(){
//
//
//            var allData:List<ModelPlaces>?=null
//              vm.select()
//            recycler1?.apply {
//                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//                recycler1.layoutManager = layoutManager
//                adapterSave = SaveAdapter(context, allData!!)
//                recycler1.adapter = adapterSave
//                adapterSave.notifyDataSetChanged()
//            }
//        })
     //   modelPlaces = vm.select1()
     //   list = listOf(modelPlaces)



          //recycler1!!.adapter = adapterSave




        recycler1?.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            recycler1.layoutManager = layoutManager
            recycler1.addItemDecoration(DividerItemDecoration(context,LinearLayoutManager.VERTICAL))
            adapterSave = SaveAdapter(context,list,map, object : SaveAdapter.TaskHandler2{
                override fun itemClick(pos: Int, modelPlaces: ModelPlaces,status:Boolean) {

                    var success : Int = 0
                          success = vm.delete(modelPlaces)
                    if(success>-1) {
                        var boolean = false
                        SharedPrefManager.getmInstance(requireContext())?.saveID(modelPlaces.placeId)
                        SharedPrefManager.getmInstance(requireContext())?.saveName(modelPlaces.placeName)
                        SharedPrefManager.getmInstance(requireContext())?.saveState(boolean)
                    }
                }
            })
            recycler1.adapter = adapterSave
            adapterSave.notifyDataSetChanged()
        }
        return view
    }

    fun getAllPlaces() {


        vm.allData.observe(this,Observer<ModelPlaces>(){

          allData->
            if (allData != null) {

                list.add(allData)
                list.iterator().hasNext()
                list.iterator().next().placeId
                list.iterator().next().placeName


                map.put(allData.placeId,allData)
                map.iterator().next()
                Log.e("","map=="+map)

            }


        })

    }

//    fun updateView(modelPlaces: ModelPlaces){
//        var newList = ArrayList<ModelPlaces>()
//        newList.add(modelPlaces)
//        adapterSave = SaveAdapter(activity!!,newList, object : SaveAdapter.TaskHandler2{
//            override fun itemClick(pos: Int, modelPlaces: ModelPlaces,Status:Boolean) {
//
//
//            }
//        })
//        recycler1?.apply {
//          layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//            recycler1.layoutManager = layoutManager
//            recycler1?.adapter = adapterSave
//        }
//    }
}

