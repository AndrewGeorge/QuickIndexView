package cn.ifavor.quickindexsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import cn.ifavor.quickindexview.QuickIndexViewActivity;
import cn.ifavor.quickindexview.constants.Contants;

public class MainActivity extends Activity {

	private static final int REQUEST_CODE = 0;
	private TextView mTvResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mTvResult = (TextView) findViewById(R.id.tv_result);
	}

	public void click(View view) {
		// 示例人名
		String[] namesStrings = { "AAA", "何世威", "刘铁", "吕册", "王彦苏", "刘化峰", "沙丽珊",
				"王志会", "姜波", "王昕", "彭莉", "米云龙", "秦勤", "许华", "佟冬蕾", "兰庆伟", "曹宽",
				"孙成伟", "张大勇", "刘贤宇", "李月", "姚佳媛", "益长虹" };
		Intent intent = new Intent(this, QuickIndexViewActivity.class);
		// 设置数据
		intent.putExtra(Contants.CONTACTS_NAMES, namesStrings);
		// 开启Activity
		startActivityForResult(intent, REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE
				&& resultCode == Contants.CONTACTS_RESULT_CODE) {

			// 获取联系人姓名
			String name = data.getStringExtra(Contants.INTENT_CONTAC_NAME);
			System.out.println(name);
			
			// 获取按姓名排序后的索引
			int sortedIndex = data.getIntExtra(Contants.INTENT_CONTAC_INDEX_SORTED, -1);
			System.out.println(sortedIndex);
			
			// 获取原始排序的原始索引
			int index = data.getIntExtra(Contants.INTENT_CONTAC_INDEX, -1);
			System.out.println(index);
			
			// 设置结果值
			mTvResult.setText("结果："+name);
		}
	}

}
