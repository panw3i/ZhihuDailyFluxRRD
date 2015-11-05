package lsxiao.com.zhihudailyrrd.view.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import lsxiao.com.zhihudailyrrd.R;
import lsxiao.com.zhihudailyrrd.base.BaseActivity;
import lsxiao.com.zhihudailyrrd.model.Story;
import lsxiao.com.zhihudailyrrd.view.news.NewsFragment;
import lsxiao.com.zhihudailyrrd.view.news.NewsListFragment;

/**
 * @author lsxiao
 * @date 2015-11-03 22:28
 */
public class HomeActivity extends BaseActivity implements NewsListFragment.OnNewsClickListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        setSupportActionBar(mToolbar);
        Fragment fragment = NewsListFragment.newInstance();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_container, fragment, NewsListFragment.TAG);
        ft.commit();
    }

    private void showNewsFragment(long newsId) {
        Fragment fragment = NewsFragment.newInstance(newsId);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl_container, fragment, NewsFragment.TAG);
        ft.hide(fm.findFragmentByTag(NewsListFragment.TAG));
        ft.show(fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void click(Story story) {
        showNewsFragment(story.getStoryId());
    }
}
