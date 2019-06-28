package k.lhl.test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import android.widget.Toast
import k.lhl.adapter.Adapter
import k.lhl.adapter.MultiAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_test.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val data = mutableListOf<String>()
//        for (i in 0..10) {
//            data.add(i.toString())
//        }
//
//        lvList.adapter = Adapter(this, data, R.layout.item_test) { v, item, position ->
//            v.tvNum.text = item
//            v.btn.setOnClickListener { Toast.makeText(this, "点击" + position, Toast.LENGTH_SHORT).show() }
//        }

        val data = mutableListOf<Pair<Int, String>>()
        for (i in 0..20) {
            if (i < 4)
                data.add(Pair(0, i.toString()))
            else
                data.add(Pair(1, i.toString()))
        }


        lvList.adapter = MultiAdapter(this, data, SparseArray<Int>().apply { put(0, R.layout.item_test);put(1, R.layout.item_test_2) }) { view, item, type, position ->
            when (type) {
                0 -> {
                    view.tvNum.text = "$item  类型0"
                    view.btn.setOnClickListener { Toast.makeText(this, "点击$position   类型0", Toast.LENGTH_SHORT).show() }
                }
                1 -> {
                    view.tvNum.text = "$item  类型1"
                    view.btn.setOnClickListener { Toast.makeText(this, "点击$position   类型1", Toast.LENGTH_SHORT).show() }
                }
            }
        }


    }
}
