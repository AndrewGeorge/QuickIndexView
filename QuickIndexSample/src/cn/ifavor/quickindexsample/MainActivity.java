package cn.ifavor.quickindexsample;

import cn.ifavor.quickindexview.QuickIndexViewActivity;
import cn.ifavor.quickindexview.constants.Contants;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View view) {
		String[] namesStrings = { "AA","何世威","刘铁", "吕册", "王彦苏", "刘化峰", "沙丽珊", "王志会", "姜波",
				"王昕", "彭莉", "米云龙", "秦勤", "许华", "佟冬蕾", "兰庆伟", "曹宽", "孙成伟",
				"张大勇", "刘贤宇", "李月", "姚佳媛", "益长虹" };
		Intent intent = new Intent(this, QuickIndexViewActivity.class);
		intent.putExtra(Contants.CONTACTS_NAMES, namesStrings);
		startActivity(intent);
	}
}
