package org.chzz.demo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import org.chzz.demo.R;
import org.chzz.demo.ui.widget.BottomBar;
import org.chzz.demo.ui.widget.BottomBarTab;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by copy on 2017/8/6.
 * Description:
 * User: copy
 * Date: 2017-08-06
 * Time: 下午1:08
 */
public class MainFragment extends BaseFragment {
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    private SupportFragment[] mFragments = new SupportFragment[3];
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void bindView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.fragment_main,false);
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mBottomBar
                .addItem(new BottomBarTab(_mActivity, R.mipmap.ic_message_white_24dp, "消息"))
                .addItem(new BottomBarTab(_mActivity, R.mipmap.ic_discover_white_24dp, "联系人"))
                .addItem(new BottomBarTab(_mActivity, R.mipmap.ic_account_circle_white_24dp, "我的"));
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
//
//                BottomBarTab tab = mBottomBar.getItem(FIRST);
//                if (position == FIRST) {
//                    tab.setUnreadCount(0);
//                } else {
//                    tab.setUnreadCount(tab.getUnreadCount() + 1);
//                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                // 在FirstPagerFragment,FirstHomeFragment中接收, 因为是嵌套的Fragment
                // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportFragment firstFragment = findChildFragment(IndexFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = IndexFragment.newInstance();
            mFragments[SECOND] = ContactFragment.newInstance();
            mFragments[THIRD] = MyFragment.newInstance();
            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用,也可以通过getChildFragmentManager.findFragmentByTag自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findChildFragment(IndexFragment.class);
            mFragments[THIRD] = findChildFragment(IndexFragment.class);
        }
    }


}
