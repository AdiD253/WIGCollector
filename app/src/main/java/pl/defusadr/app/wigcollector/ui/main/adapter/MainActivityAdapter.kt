package pl.defusadr.app.wigcollector.ui.main.adapter

import android.animation.ArgbEvaluator
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*
import pl.defusadr.app.wigcollector.R
import pl.defusadr.app.wigcollector.model.WIGModel
import android.animation.ObjectAnimator
import android.graphics.Color


class MainActivityAdapter(var wigValues: MutableList<WIGModel>) :
        RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(wigValues[position])
    }

    override fun getItemCount(): Int {
        return wigValues.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(wigModel: WIGModel) = with(itemView) {

            if (itemView.tvDate.text != (wigModel.date + " " + wigModel.time))
                setBackgroundAnimation(itemView)

            itemView.tvSymbol.text = wigModel.symbol
            itemView.tvDate.text = String.format(
                    resources.getString(R.string.recycler_item_date_time),
                    wigModel.date,
                    wigModel.time
            )
            itemView.tvValue.text = wigModel.lowest
        }

        private fun setBackgroundAnimation(view: View) {
            view.setBackgroundColor(Color.YELLOW)

            ObjectAnimator.ofObject(
                    view,
                    "backgroundColor",
                    ArgbEvaluator(),
                    Color.YELLOW,
                    Color.WHITE
            )
                    .setDuration(2000L)
                    .start()
        }
    }
}