package com.rovermore.twinsapp.babyweek

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rovermore.twinsapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val DUTY_ARG = "duty_arg"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentWeek.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FragmentWeek.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FragmentWeek : Fragment() {
    // TODO: Rename and change types of parameters
    private var position: Int? = null
    private var duty: String? = null

    //private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_PARAM1)
            duty = it.getString(DUTY_ARG)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_baby_week, container, false)
        var numberTextView = view.findViewById<TextView>(R.id.tv_number)
        var dutyTextView = view.findViewById<TextView>(R.id.tv_duty)
        position = position?.minus(1)
        numberTextView.text = position.toString()
        dutyTextView.text = duty
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentWeek.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, duty: String) =
                FragmentWeek().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_PARAM1, param1)
                        putString(DUTY_ARG, duty)
                    }
                }

    }
}
