package com.example.ktask.view


import android.arch.lifecycle.*
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ktask.model.ModelPlaces
import com.example.ktask.R
import com.example.ktask.model.SharedPrefManager
import com.example.ktask.viewmodel.IPlaceViewModel
import com.example.ktask.viewmodel.PlaceViewModel

import kotlinx.android.synthetic.main.fragment_all_places.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import java.nio.channels.Selector

class AllPlaces : Fragment() {

    lateinit var vm : PlaceViewModel
    lateinit var adapterPlace: ApllPlaceAdapter
    private var recycler: RecyclerView? = null
    var listPlace = ArrayList<ModelPlaces>()
//    lateinit var vm1 :PlaceViewModel1

    companion object {

        fun newInstance(): AllPlaces {
            return AllPlaces()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = activity.run {  ViewModelProviders.of(this!!).get(PlaceViewModel::class.java) } ?: throw Exception("invalid activity")



//        vm1 = activity.run{ViewModelProviders.of(this!!).get(PlaceViewModel1::class.java)} ?: throw Exception("invalia context")
       // vm = ViewModelProviders.of(requireActivity()).get(PlaceViewModel::class.java) ?: throw Exception("invalid..")

        Toast.makeText(context,"hi kotlin  ", Toast.LENGTH_LONG).show()

    }
//    private fun showList(allData: List<ModelPlaces>?) {
//        Toast.makeText(context,"hi kotlin "+allData, Toast.LENGTH_LONG).show()
//        Log.e("allData",""+allData)
//    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        var view = inflater.inflate(R.layout.fragment_all_places, container, false)

        var qu = Volley.newRequestQueue(context)
        var modelPlaces: ModelPlaces = ModelPlaces()

        var url: String =
            "https://api.foursquare.com/v2/venues/search?ll=40.7484,-73.9857&oauth_token=NPKYZ3WZ1VYMNAZ2FLX1WLECAWSMUVOQZOIDBN53F3LVZBPQ&v=20180616"

        val strReq = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
            var resp = response.toString()
            val jsonObj: JSONObject = JSONObject(resp)
            val jsonobj2: JSONObject = jsonObj.getJSONObject("response")
            val jsonArray: JSONArray = jsonobj2.getJSONArray("venues")
            var result1: String = ""
            var result2: String = ""
            for (i in 0 until jsonArray.length()) {
                var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                result1 = jsonInner.getString("id")
                result2 = jsonInner.getString("name")

                modelPlaces = ModelPlaces(result1, result2)
                listPlace.add(modelPlaces)
                Log.e("id=" + result1, "name =" + listPlace)

            }

//             var sortedList = listPlace.sortedBy { modelPlaces.placeId }

            dataCheck(modelPlaces)

            var layoutManager: LinearLayoutManager? = null

            recycler = view?.findViewById<RecyclerView>(R.id.recycler)

            recycler?.apply {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                recycler.layoutManager = layoutManager
                adapterPlace = ApllPlaceAdapter(context, listPlace,object :ApllPlaceAdapter.TaskHandler1{

                    override fun itemClick(pos: Int, modelPlaces: ModelPlaces,status:Boolean) {
                     // var preferences:SharedPreferences = activity.getSharedPreferences()
                        try {
//                            for (i in 0 until list.size)
//                                modelPlaces=list.get(pos)

                                vm.insert(modelPlaces)
                            Toast.makeText(activity,""+pos + modelPlaces,Toast.LENGTH_LONG).show()
                            SharedPrefManager.getmInstance(requireContext())?.saveID(modelPlaces.placeId)
                            SharedPrefManager.getmInstance(requireContext())?.saveName(modelPlaces.placeName)
                            SharedPrefManager.getmInstance(requireContext())?.saveState(status)
                           // SharedPrefManager.getmInstance(requireContext())?.savePosition(pos)

                        }catch (e:Exception){
                            e.printStackTrace()
                        }
                    }
                })
                recycler.adapter = adapterPlace
                adapterPlace.notifyDataSetChanged()

            }
//            recycler!!.setOnClickListener(View.OnClickListener { v->
//                vm.insert(adapterPlace.getPlaceData())
//
//            })


        }, Response.ErrorListener { Log.e("error volley", "") })
        qu.add(strReq)

        return view

    }

    fun dataCheck(modelPlaces: ModelPlaces){

        var modelPlacesFromDb = ModelPlaces()

        vm.allData.observe(this,Observer<ModelPlaces>(){
            it->modelPlacesFromDb.placeId

        })

    }

}

