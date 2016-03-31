package cn.ifavor.quickindexview;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import cn.ifavor.quickindexview.bean.Person;
import cn.ifavor.quickindexview.constants.Contants;
import cn.ifavor.quickindexview.view.QuickIndexBar;


public class QuickIndexViewActivity extends Activity {

    private ArrayList<Person> names;
	private ListView listview;
	private TextView mIndexLayout;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_index);
        
        // 初始化数据
        String[] namesArr = getIntent().getStringArrayExtra(Contants.CONTACTS_NAMES);
        
        if (namesArr != null && namesArr.length > 0){
        	  names = new ArrayList<Person>();
              for (int i = 0; i<namesArr.length; i++){
              	Person person = new Person(namesArr[i]);
              	names.add(person);
              }
              // 排序
              Collections.sort(names);
        }
        
        mIndexLayout = (TextView) findViewById(R.id.tv_index_layout);
        listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(new PersonAdapter());
        
        QuickIndexBar bar = (QuickIndexBar) findViewById(R.id.bar);
        bar.setOnLetterUpdateListener(new QuickIndexBar.OnLetterUpdateListener(){

			@Override
			public void onUpdate(String letter) {
				int selectedPosition = -1;
				for (int i = 0; i < names.size(); i++){
					if (letter.equals(names.get(i).getPinyin().charAt(0) +"")){
						selectedPosition = i;
						break;
					}
				}
				System.out.println("selectedPosition: " + selectedPosition);
				listview.setSelection(selectedPosition);
				
				// 在索引层中显示索引
				showIndexLayout(letter);
			}

        });
    }
	
	private Handler mHandler = new Handler();
	/* 在索引层中显示索引 */
	private void showIndexLayout(String letter) {
		mIndexLayout.setVisibility(View.VISIBLE);
		mIndexLayout.setText(letter);
		mHandler.removeCallbacksAndMessages(null);
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				mIndexLayout.setVisibility(View.INVISIBLE);
			}
		}, 2000);
	}
    
    private class PersonAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return names.size();
		}

		@Override
		public Object getItem(int position) {
			return names.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null){
				convertView = View.inflate(QuickIndexViewActivity.this, R.layout.item_list, null);
				holder = new ViewHolder();
				holder.tv_Index = (TextView) convertView.findViewById(R.id.tv_index);
				holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			boolean isHide = false;

			if (position == 0){
				isHide = false;
			} else {
				isHide = names.get(position).getPinyin().charAt(0) == names.get(position - 1).getPinyin().charAt(0);
			}
			
			holder.tv_Index.setVisibility(isHide ? View.GONE : View.VISIBLE);
			
			Person person =  names.get(position);
			// 这里不能直接使用char，直接使用char和int相等，是获取资源
			holder.tv_Index.setText(person.getPinyin().charAt(0) + "");
			holder.tv_name.setText(person.getName());
			return convertView;
		}
    }
    
    private class ViewHolder{
    	TextView tv_Index;
    	TextView tv_name;
    }
    
}


