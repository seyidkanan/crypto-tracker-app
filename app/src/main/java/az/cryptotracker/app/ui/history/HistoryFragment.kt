package az.cryptotracker.app.ui.history

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import az.cryptotracker.app.R
import az.cryptotracker.app.ui.dialog.ProgressDialog
import az.cryptotracker.app.ui.history.adapter.HistoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private val historyViewModel: HistoryViewModel by viewModels()
    private lateinit var historyAdapter: HistoryAdapter

    private val coinId: String by lazy {
        arguments?.getString(
            "coinId", ""
        ) ?: ""
    }

    private val progressDialog: ProgressDialog by lazy { ProgressDialog(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv(view)
        historyViewModel.coinList.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
            when (it) {
                is CoinListState.CoinListData -> {
                    historyAdapter.setCoinList(it.coinList)
                }
                else -> {
                    showMessageDialog("No data")
                }
            }
        }

        if (coinId.isNotEmpty()) {
            progressDialog.startLoading()
            historyViewModel.loadCoinPriceListByCoinId(coinId)
        }

    }

    private fun initRv(view: View) {
        val recycleView = view.findViewById<RecyclerView>(R.id.recycleView)
        recycleView.layoutManager = LinearLayoutManager(requireContext())

        historyAdapter = HistoryAdapter()
        recycleView.adapter = historyAdapter
    }

    fun showMessageDialog(str: String?) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setMessage(str)
        builder.setCancelable(false)
        builder.setNeutralButton("ok") { dialog, which -> dialog.dismiss() }
        val alert: AlertDialog = builder.create()
        alert.show()
    }

}