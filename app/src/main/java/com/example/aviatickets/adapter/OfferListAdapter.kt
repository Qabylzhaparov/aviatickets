import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aviatickets.R
import com.example.aviatickets.databinding.ItemOfferBinding
import com.example.aviatickets.model.entity.Offer
import com.example.aviatickets.model.service.FakeService.offerList

class OfferListAdapter : ListAdapter<Offer, OfferListAdapter.ViewHolder>(OfferDiffCallback()) {

    inner class ViewHolder(private val binding: ItemOfferBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(offer: Offer) {
            val flight = offer.flight

            with(binding) {
                departureTime.text = flight.departureTimeInfo
                arrivalTime.text = flight.arrivalTimeInfo
                route.text = root.context.getString(R.string.route_fmt, flight.departureLocation.code, flight.arrivalLocation.code)
                duration.text = root.context.getString(R.string.time_fmt, getTimeFormat(flight.duration).first.toString(), getTimeFormat(flight.duration).second.toString())
                direct.text = root.context.getString(R.string.direct)
                price.text = root.context.getString(R.string.price_fmt, offer.price.toString())
            }
        }

        private fun getTimeFormat(minutes: Int): Pair<Int, Int> = Pair(minutes / 60, minutes % 60)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun sortByPrice() {
        val currentList = currentList.toMutableList()
        currentList.sortBy { it.price }
        submitList(currentList)
    }

    fun sortByDuration() {
        val currentList = currentList.toMutableList()
        currentList.sortBy { it.flight.duration }
        submitList(currentList)
    }

    // Класс для сравнения списков предложений
    private class OfferDiffCallback : DiffUtil.ItemCallback<Offer>() {
        override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem == newItem
        }
    }
}
