package com.example.note;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class NoteListViewAdapter extends BaseAdapter{ // ��Ʈ ����Ʈ�� �����  (( �ǽð����� �˻������ �ѷ��ֱ�����)

	Context context;
	LayoutInflater inflater;
	private List<Note> noteList=null; //��ƮŬ���� ����Ʈ ����  null�� �ʱ�ȭ
	private ArrayList<Note> arrayList; //�迭����Ʈ ���
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
