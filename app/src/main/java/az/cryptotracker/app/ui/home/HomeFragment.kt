package az.cryptotracker.app.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import az.cryptotracker.app.R
import az.cryptotracker.app.ui.home.adapter.CoinAdapter
import az.cryptotracker.app.ui.home.adapter.CoinModel
import az.cryptotracker.app.ui.RvItemClickListener
import az.cryptotracker.app.ui.dialog.ProgressDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), RvItemClickListener<CoinModel> {

    private val mainViewModel by viewModels<HomeViewModel>()

    private lateinit var recycleView: RecyclerView
    private lateinit var coinAdapter: CoinAdapter

    private val progressDialog: ProgressDialog by lazy { ProgressDialog(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv(view)

        mainViewModel.coinModelList.observe(viewLifecycleOwner) {
            progressDialog.dismiss()
            if (it.size > 0) {
                coinAdapter.setCoinList(it)
            }
        }

        progressDialog.startLoading()
        mainViewModel.fetchData()
    }

    private fun initRv(view: View) {
        recycleView = view.findViewById(R.id.recycleView)
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        coinAdapter = CoinAdapter(this)
        recycleView.adapter = coinAdapter
    }

    override fun onItemClick(coinModel: CoinModel) {
        val bundle = bundleOf("coin" to coinModel)
        findNavController().navigate(R.id.action_homeFragment_to_alertFragment, bundle)
    }

}