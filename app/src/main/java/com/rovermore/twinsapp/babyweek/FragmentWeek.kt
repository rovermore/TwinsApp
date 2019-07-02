package com.rovermore.twinsapp.babyweek

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.github.chrisbanes.photoview.PhotoView
import com.rovermore.twinsapp.R
import com.squareup.picasso.Picasso


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

    private var position: Int? = null
    private var duty: String? = null
    val url = "https://firebasestorage.googleapis.com/v0/b/twinsapp-25d55.appspot.com/o/674609.jpg?alt=media&token=62b2c073-99e3-481b-9958-fcb471af812f"


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
        var imageView = view.findViewById<ImageView>(R.id.iv_baby_week)
        numberTextView.text = position.toString()
        dutyTextView.text = duty

        val builder = Picasso.Builder(context)
        builder.listener(Picasso.Listener { picasso, uri, exception -> exception.printStackTrace();  })
        builder.build().load(url).into(imageView)

        val displayMetrics = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(displayMetrics)

        var width = displayMetrics.widthPixels - 15
        var height = displayMetrics.heightPixels - 65

        imageView.setOnClickListener(View.OnClickListener {
            val mBuilder = AlertDialog.Builder(this!!.context!!)
            val mView = layoutInflater.inflate(R.layout.dialog_alert_image_layout, null)
            val photoView = mView.findViewById<PhotoView>(R.id.iv_touchable)
            setImageWithPicasso(photoView)
            mBuilder.setView(mView)
            val mDialog = mBuilder.create()
            //mDialog.setTitle("HBO El Pionero")
            mDialog.show()
            var imageWidth = imageView.drawable.intrinsicWidth
            var imageHeight = imageView.drawable.intrinsicHeight
            if(imageHeight<=height && imageWidth<=width) {
                mDialog.window.setLayout(imageWidth, imageHeight)
            } else {
                mDialog.window.setLayout(width, height)
            }
        })

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

    fun setImageWithPicasso(view: PhotoView){
        val builder = Picasso.Builder(context)
        builder.listener(Picasso.Listener { picasso, uri, exception -> exception.printStackTrace();  })
        builder.build().load(url).into(view)
    }
}
