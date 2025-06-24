package com.example.trsahonghi.ui.home.homegroup.bottomsheet

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.api.model.Food
import com.example.trsahonghi.base.OrderEnabledLocalBroadcastManager
import com.example.trsahonghi.databinding.BottomSheetCartBinding
import com.example.trsahonghi.ui.home.listfood.adapter.ListFoodAdapter
import com.example.trsahonghi.util.Constants
import com.example.trsahonghi.util.StringUtils
import com.example.trsahonghi.util.SwipeToDeleteCallback
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.example.trsahonghi.ui.basedatabind.BaseDataBindBottomSheet

class CartBottomSheet(
) : BaseDataBindBottomSheet<BottomSheetCartBinding, CartContract.Presenter>(), CartContract.View {
    companion object {
        const val TAG = "CartBottomSheet"
        private const val ARG_LIST_FOOD = "ARG_LIST_FOOD"
        fun newInstance(
            listFood: List<Food>
        ): CartBottomSheet {
            val args = Bundle().apply {
                putParcelableArrayList(ARG_LIST_FOOD, ArrayList(listFood))
            }
            val fragment = CartBottomSheet()
            fragment.arguments = args
            return fragment
        }
    }

    private val adapter: ListFoodAdapter by lazy {
        ListFoodAdapter(onItemClickListener = { item, pos ->

        },
            onAddClickListener = { item, pos ->
                presenter.addAmountFood(item)
                adapter.notifyItemChanged(pos)
            },
            onSubClickListener = { item, pos ->
                presenter.subAmountFood(item)
                adapter.notifyItemChanged(pos)
            }
        )
    }

    override val layoutRes: Int
        get() = R.layout.bottom_sheet_cart

    override fun initView() {
        binding.apply {
            rvCart.adapter = adapter
            SwipeToDeleteCallback.setupSwipeToDelete(rvCart) { position ->
                presenter?.removeFood(position)
            }
            imgClose.setOnClickListener {
                dismiss()
            }
        }
    }


    override fun initData() {
        val listFood =
            arguments?.getParcelableArrayList<Food>(ARG_LIST_FOOD) ?: emptyList()

        presenter = CartPresenter(this, listFood).apply {
            listFood().observe(viewLifecycleOwner) { list ->
                handleListUpdate(list)
            }

            binding.presenter = this
        }
    }

    private fun handleListUpdate(list: List<Food>) {
        adapter.submitList(list)
        sendListFoodBroadcast(list)

        if (list.isEmpty()) {
            dismiss()
        }
    }

    private fun sendListFoodBroadcast(list: List<Food>) {
        val broadcastIntent = Intent(Constants.Actions.NOTIFY_LIST_FOOD).apply {
            putExtra(
                Constants.BundleConstants.LIST_FOOD_CART,
                StringUtils.objectToString(list)
            )
        }
        context?.let { context ->
            OrderEnabledLocalBroadcastManager.getInstance(context)
                .sendBroadcast(broadcastIntent)
        }
    }

    override fun onStart() {
        super.onStart()
        val bottomSheet =
            dialog!!.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        val bottomSheetBehavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet)

        bottomSheet.layoutParams.height = (resources.displayMetrics.heightPixels * 0.95).toInt()
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }


    override fun showLoading() {}
    override fun hideLoading() {}
    override fun showMessage(message: String) {}
}