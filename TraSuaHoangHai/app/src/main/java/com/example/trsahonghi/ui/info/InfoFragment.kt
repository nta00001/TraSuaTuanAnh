package com.example.trsahonghi.ui.info

import com.example.trsahonghi.R
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentInfoBinding
import com.example.trsahonghi.ui.history.HistoryOrderFragment

class InfoFragment : BaseDataBindFragment<FragmentInfoBinding, InfoContract.Presenter>(),
    InfoContract.View {

    companion object {
        fun newInstance() = InfoFragment()
    }

    override fun getLayoutId() = R.layout.fragment_info
    override fun initView() {
        mBinding?.apply {

            toolbar.setOnBackClickListener {
                getBaseActivity().onBackFragment()
            }
        }
        mBinding?.view = this
    }

    override fun initData() {
        mPresenter = InfoPresenter(this).apply {
            mBinding?.presenter = this
        }
    }

    override fun openFragmentHistoryOrder() {
        getBaseActivity().replaceFragment(HistoryOrderFragment.newInstance(), R.id.flMain)
    }
}