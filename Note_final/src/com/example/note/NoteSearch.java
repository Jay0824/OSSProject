package com.example.note;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class NoteSearch extends ListActivity {
	EditText searchEt;
	Button searchBt;
	ListView list;
	private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;
	private NotesDbAdapter mDbHelper;
    private static final int DELETE_ID = Menu.FIRST;
	private int mNoteNumber = 1;
	
	 SQLiteDatabase database;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_search);
		mDbHelper = new NotesDbAdapter(this);
	//	list=(ListView)findViewById(R.id.lists);
		mDbHelper.open();
		database = mDbHelper.getReadable();
		//list=(ListView)findViewById(R.id.)
		searchEt=(EditText)findViewById(R.id.searchInput);
		searchBt=(Button)findViewById(R.id.searchBtn);
		registerForContextMenu(getListView());
		Button addnote=(Button)findViewById(R.id.addnotebutton_two);
		addnote.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				createNote();
				}
		});
		searchBt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		fillData(searchEt.getText().toString());
			}
		});
	
	
	}
	private void fillData(String hash) {
        // Get all of the notes from the database and create the item list
       try{
		Cursor cursor = mDbHelper.fetchAllNotes();
    //    database=mDbHelper.getReadable();
      //  database.beginTransaction();
      cursor= database.rawQuery("select * from notes where hash = '"+hash+"' OR hashh = '"+hash+"' OR hashhh= '"+hash+"';", null);
      // cursor=database.rawQuery("select * from notes;", null);
        startManagingCursor(cursor);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),R.layout.notes_row,cursor,new String[]{NotesDbAdapter.KEY_TITLE ,NotesDbAdapter.KEY_DATE},new int[]{R.id.no_title,R.id.date_row});
      setListAdapter(adapter);
      //  database.setTransactionSuccessful();
        }catch(Exception e){
        	 Toast.makeText(getApplicationContext(), e.getMessage(),1).show(); 
        }finally{
     //  database.endTransaction();
        }
        // Now create an array adapter and set it to display using our row
   //    database.close();
      
    }
	private void createNote(){
		Intent i = new Intent(this,NoteEdit.class);
		startActivityForResult(i,ACTIVITY_CREATE);
	}
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent i = new Intent(this, NoteEdit.class);
        i.putExtra(NotesDbAdapter.KEY_ROWID, id);
        startActivityForResult(i, ACTIVITY_EDIT);
    }

public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	 MenuInflater inflater = getMenuInflater();
inflater.inflate(R.menu.notesearch_menu, menu);
// inflater.inflate(R.menu.for_action,menu);
	
	 
	return super.onCreateOptionsMenu(menu);		
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
	
	
	switch (item.getItemId()) {
	
           
           case R.id.action_back:
        	   Intent i = new Intent(getApplicationContext(),NoteList.class);
        	   startActivity(i);
    return true;
        default:
            return super.onOptionsItemSelected(item);
        }
	
    
}
}