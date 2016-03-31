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
		String[] namesStrings = { "AA","������","����", "����", "������", "������", "ɳ��ɺ", "��־��", "����",
				"���", "����", "������", "����", "��", "١����", "����ΰ", "�ܿ�", "���ΰ",
				"�Ŵ���", "������", "����", "Ҧ����", "�泤��" };
		Intent intent = new Intent(this, QuickIndexViewActivity.class);
		intent.putExtra(Contants.CONTACTS_NAMES, namesStrings);
		startActivity(intent);
	}
}
