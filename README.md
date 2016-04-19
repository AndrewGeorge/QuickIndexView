该组件是模仿微信的联系人界面，可直接在项目中使用。

**下载地址**：https://github.com/heshiweij/QuickIndexView

## 特点

1. 对联系人进行排序分组
2. 右侧触摸滑动快速查找
3. 实时在中间显示当前的字母
4. 点击联系人条目，返回结果

## 最终效果

![这里写图片描述](http://img.blog.csdn.net/20160401130728626)

## 简单使用

1. 导入 [QuickIndexView](https://github.com/heshiweij/QuickIndexView) 
2. 调用 startActivityForResult 开启界面
3. 重写 onActivityResult 获取结果

代码如下：


```java
// 联系人的字符串数组

String[] namesStrings = {....}； 
Intent intent = new Intent(this, QuickIndexViewActivity.class);
intent.putExtra(Contants.CONTACTS_NAMES, namesStrings);
// 开启界面
startActivityForResult(intent, REQUEST_CODE);
```

```
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
		
		mTvResult.setText("结果："+name);
	}
}
```


### 常量说明

```
public class Contants {
	/* 输入联系人数组时用的 key */
	public static final String CONTACTS_NAMES = "contacts_names";
	
	/* 输出时所用的返回码 */
	public static final int CONTACTS_RESULT_CODE = 20;
	
	/* 输出联系人姓名时用的 key */
	public static final String INTENT_CONTAC_NAME = "CONTAC_NAME";
	
	/* 输出联系人索引（已排序）时用的 key */
	public static final String INTENT_CONTAC_INDEX_SORTED = "CONTAC_INDEX_SORTED";
	
	/* 输出联系人索引（未排序）时用的 key */
	public static final String INTENT_CONTAC_INDEX = "CONTAC_INDEX";
}
```

## 附录

下载地址：https://github.com/heshiweij/QuickIndexView
