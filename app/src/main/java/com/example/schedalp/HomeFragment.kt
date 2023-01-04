package com.example.schedalp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedalp.adapter.ScheduleAdapter
import com.example.schedalp.view.AddScheduleActivity
import com.example.schedalp.viewmodel.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var adapter: ScheduleAdapter
    private  lateinit var viewModel: ScheduleViewModel
    private lateinit var recycler: RecyclerView
    private lateinit var createbutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[ScheduleViewModel::class.java]
        viewModel.getAllScheduleData()

        createbutton = view.findViewById(R.id.create)
        createbutton.setOnClickListener {
            val intent = Intent(context, AddScheduleActivity::class.java)
            startActivity(intent)
        }

        viewModel.dataschedule.observe(viewLifecycleOwner, { response ->
            val layoutmanager = LinearLayoutManager(context)
            recycler = view.findViewById(R.id.mainrv)
            recycler.layoutManager = layoutmanager
            adapter = ScheduleAdapter(response.data as ArrayList)
            recycler.adapter = adapter
        })
        super.onViewCreated(view, savedInstanceState)
    }





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}