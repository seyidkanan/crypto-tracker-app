package az.cryptotracker.app.ui.alert

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import az.cryptotracker.app.R
import az.cryptotracker.app.ui.dialog.ProgressDialog
import az.cryptotracker.app.ui.home.adapter.CoinModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AlertFragment : Fragment() {

    private lateinit var editTextMinAmount: EditText
    private lateinit var editTextMaxAmount: EditText
    private lateinit var buttonHistory: Button
    private lateinit var textViewCoinName: TextView
    private lateinit var buttonSetMinMaxValue: Button

    private val alertViewModel by viewModels<AlertViewModel>()

    private var coin: CoinModel? = null

    private val progressDialog: ProgressDialog by lazy { ProgressDialog(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alert, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coin = arguments?.getParcelable("coin")
        initViews(view)

        alertViewModel.currentMinMaxValue.observe(viewLifecycleOwner) {
            it?.let { value ->
                progressDialog.dismiss()
                when (value) {
                    is CurrentMinMaxValue.MinMaxValue -> {
                        editTextMinAmount.setText(value.min.toString())
                        editTextMaxAmount.setText(value.max.toString())
                    }
                    else -> {
                        editTextMinAmount.text.clear()
                        editTextMaxAmount.text.clear()
                    }
                }
            }
        }

        coin?.coinId?.let {
            progressDialog.startLoading()
            alertViewModel.getCurrentMinMaxValueIfHas(it)
        }
    }

    private fun initViews(view: View) {
        editTextMinAmount = view.findViewById(R.id.editTextMinAmount)
        editTextMaxAmount = view.findViewById(R.id.editTextMaxAmount)
        buttonHistory = view.findViewById(R.id.buttonHistory)
        textViewCoinName = view.findViewById(R.id.textViewCoinName)
        buttonSetMinMaxValue = view.findViewById(R.id.buttonSetMinMaxValue)

        textViewCoinName.text = coin?.coinName

        initButtonClickListener()
    }

    private fun initButtonClickListener() {
        buttonHistory.setOnClickListener {
            val bundle = bundleOf("coinId" to coin?.coinId)
            findNavController().navigate(
                R.id.action_alertFragment_to_historyFragment,
                bundle
            )
        }

        buttonSetMinMaxValue.setOnClickListener {
            val minValue = editTextMinAmount.text.toString()
            val maxValue = editTextMaxAmount.text.toString()
            if (alertViewModel.checkInputValidation(minValue, maxValue)) {
                if (!coin?.coinId.isNullOrEmpty()) {
                    alertViewModel.insertMinMaxValue(
                        min = minValue.toDouble(),
                        max = maxValue.toDouble(),
                        coinId = coin?.coinId ?: ""
                    )
                }
            } else {
                showMessageDialog("min or max input not correct")
            }
        }

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