package com.example.trsahonghi.ui.payment

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.Food
import com.example.trsahonghi.api.model.request.OrderRequest
import com.example.trsahonghi.api.repository.account.AccountRepositoryImpl
import com.example.trsahonghi.api.repository.food.FoodRepositoryImpl
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.base.OrderEnabledLocalBroadcastManager
import com.example.trsahonghi.databinding.FragmentPaymentBinding
import com.example.trsahonghi.ui.home.location.map.AddressFragment
import com.example.trsahonghi.ui.payment.adapter.PaymentAdapter
import com.example.trsahonghi.ui.payment.bottomsheet.VoucherBottomSheet
import com.example.trsahonghi.ui.payment.qr.PaymentQrFragment
import com.example.trsahonghi.util.Constants
import com.example.trsahonghi.util.StringUtils
import com.example.trsahonghi.util.SwipeToDeleteCallback
import com.example.trsahonghi.widget.dialog.AlertDialogListener


class PaymentFragment
    : BaseDataBindFragment<FragmentPaymentBinding, PaymentContract.Presenter>(),
    PaymentContract.View {
    companion object {
        private val ARG_LIST_FOOD = "ARG_LIST_FOOD"
        fun newInstance(
            listFood: List<Food>
        ): PaymentFragment {
            val fragment = PaymentFragment()
            val args = Bundle().apply {
                putParcelableArrayList(ARG_LIST_FOOD, ArrayList(listFood))

            }
            fragment.arguments = args
            return fragment

        }
    }

    private val adapter: PaymentAdapter by lazy {
        PaymentAdapter()
    }

    override fun getLayoutId() = R.layout.fragment_payment

    override fun initView() {
        mBinding?.apply {
            rvListFood.adapter = adapter

            toolbar.setOnBackClickListener {
                mPresenter?.listFood()?.value?.let {
                    sendListFoodBroadcast(it)
                }

            }
            llLocation.setOnClickListener {
                openFragmentAddress()
            }
            ivLocation.setOnClickListener {
                openFragmentAddress()
            }
            btnContinue.setOnClickListener {

                if (mPresenter?.isQrSelected()?.value == true) {
                    getBaseActivity().replaceFragment(
                        PaymentQrFragment.newInstance(
                            address = presenter?.address()?.value,
                            discountAmount = presenter?.discountFee()?.value,
                            totalAmount = presenter?.totalAmount()?.value,
                            voucherId = presenter?.voucher()?.value?.voucherId,
                            listFood = presenter?.listFood()?.value
                        ),
                        R.id.flMain
                    )
                } else {
                    mPresenter?.submitOrder()
                }
            }
            llVoucher.setOnClickListener {

                showBottomSheetVoucher()
            }


            SwipeToDeleteCallback.setupSwipeToDelete(rvListFood) { position ->
                presenter?.removeBubbleTea(position)
            }

            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        mPresenter?.listFood()?.value?.let {
                            sendListFoodBroadcast(it)
                        }
                    }
                })
        }
        mBinding?.view = this
    }

    private fun showBottomSheetVoucher() {
        val voucherBottomSheet =
            VoucherBottomSheet.newInstance(mPresenter?.listVoucher()?.value) { id ->
                id?.let { mPresenter?.setVoucher(it) }
            }

        voucherBottomSheet.show(parentFragmentManager, VoucherBottomSheet.TAG)
    }

    override fun initData() {
        val list = arguments?.getParcelableArrayList<Food>(ARG_LIST_FOOD) ?: emptyList()
        mPresenter = PaymentPresenter(
            this,
            list,
            FoodRepositoryImpl()
        ).apply {

            mBinding?.presenter = this
            getListVoucher()

            listFood().observe(viewLifecycleOwner) {
                adapter.submitList(it)
                setListItem(it)
            }
            voucher().observe(viewLifecycleOwner) { voucher ->
                mBinding?.txtVoucher?.text = voucher.description

                totalItemAmount().value?.let { totalAmount ->
                    StringUtils.calculateDiscountAmount(
                        totalAmount,
                        voucher.discountAmount.toString(),
                        voucher.discountPercent.toString()
                    )
                }?.let { discountAmount ->
                    setDiscountFee(discountAmount)
                }
            }

            isQrSelected().observe(viewLifecycleOwner) { isSelected ->
                setPlaceOrder(
                    if (isSelected) getString(R.string.pay_by_qr)
                    else getString(R.string.place_order)
                )
            }
        }
    }

    override fun showDiaLog(message: String) {
        getBaseActivity().showAlertDialogNew(
            icon = null,
            title = getString(R.string.app_notify_title),
            message = message,
            textTopButton = getString(R.string.common_close),
            isCancelable = false,
            listener =
            object : AlertDialogListener {
                override fun onAccept() {
                    sendListFoodBroadcast(emptyList())
                }

                override fun onCancel() {

                }
            }
        )

    }


    override fun getStringRes(id: Int): String {
        return getString(id)
    }

    private fun openFragmentAddress() {
        getBaseActivity().replaceFragment(
            AddressFragment.newInstance(), R.id.flMain
        )

    }

    override fun sendListFoodBroadcast(list: List<Food>) {

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
        getBaseActivity().onBackAllFragments()

    }
}