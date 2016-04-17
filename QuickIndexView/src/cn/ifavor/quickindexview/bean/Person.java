package cn.ifavor.quickindexview.bean;

import cn.ifavor.quickindexview.tools.PinyinUtils;

public class Person implements Comparable<Person>{
	private String name;
	private String pinyin;
	
	public Person(String name) {
		this.name = name;
		this.pinyin = PinyinUtils.getPinyin(name);
	}
	public String getName() {
		return name;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	@Override
	public int compareTo(Person another) {
		return pinyin.compareTo(another.pinyin);
	}
}
