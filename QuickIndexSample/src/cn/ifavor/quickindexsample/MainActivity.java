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
		// ʾ������
		String[] namesStrings = { "AAA", "������", "����", "����", "������", "������", "ɳ��ɺ",
				"��־��", "����", "���", "����", "������", "����", "��", "١����", "����ΰ", "�ܿ�",
				"���ΰ", "�Ŵ���", "������", "����", "Ҧ����", "�泤��" };
		Intent intent = new Intent(this, QuickIndexViewActivity.class);
		// ��������
		intent.putExtra(Contants.CONTACTS_NAMES, namesStrings);
		// ����Activity
		startActivityForResult(intent, REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE
				&& resultCode == Contants.CONTACTS_RESULT_CODE) {

			// ��ȡ��ϵ������
			String name = data.getStringExtra(Contants.INTENT_CONTAC_NAME);
			System.out.println(name);
			
			// ��ȡ����������������
			int sortedIndex = data.getIntExtra(Contants.INTENT_CONTAC_INDEX_SORTED, -1);
			System.out.println(sortedIndex);
			
			// ��ȡԭʼ�����ԭʼ����
			int index = data.getIntExtra(Contants.INTENT_CONTAC_INDEX, -1);
			System.out.println(index);
			
			// ���ý��ֵ
			mTvResult.setText("�����"+name);
		}
	}

}
