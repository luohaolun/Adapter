package k.lhl.adapter

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ActivityUtils
import lhl.kotlinextends.click
import lhl.kotlinextends.longClick

/**
 * Created by luohaolun.
 * Date: 2019/7/2
 */


class MultiRecyclerAdapter<T>(private val data: List<Pair<Int, T>>, private val layoutIds: SparseArray<Int>, private val bindView: View.(T) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var clickListener: (View.(T) -> Unit)? = null
    private var longClickListener: (View.(T) -> Unit)? = null
    private var clickIntervalTime = 10
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setTag(R.id.adapterPosition, position)
        holder.itemView.setTag(R.id.adapterType, getItemViewType(position))
        holder.itemView.run { bindView(data[position].second) }
        clickListener?.let { listener -> holder.itemView.click(clickIntervalTime) { listener.invoke(it!!, data[position].second) } }
        longClickListener?.let { listener -> holder.itemView.longClick { listener.invoke(it!!, data[position].second);true } }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutId = layoutIds[viewType] ?: error("缺少类型布局 type = $viewType")
        return MyHolder(LayoutInflater.from(ActivityUtils.getTopActivity()).inflate(layoutId, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int = data[position].first

    fun setOnItemClickListener(interval: Int = 10, listener: View.(T) -> Unit): MultiRecyclerAdapter<T> {
        clickIntervalTime = interval
        this.clickListener = listener
        return this
    }

    fun setOnItemLongClickListener(listener: View.(T) -> Unit): MultiRecyclerAdapter<T> {
        this.longClickListener = listener
        return this
    }

    inner class MyHolder(view: View) : RecyclerView.ViewHolder(view)

}


