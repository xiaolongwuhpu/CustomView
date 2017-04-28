package com.longwu.learncustomview;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactActivity extends AppCompatActivity {
    String TAG = "test";
    EditText et1, et2;
    private ListView tv_main_testView;
    private ContentResolver cr;
    private List<Map<String, Object>> lm = new ArrayList<>();//
    MultiAutoCompleteTextView multiAutoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        multiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);

        cr = getContentResolver();

    }

    public void ll_1(View view) {
        Uri uri = Uri.parse("content://contacts/people");
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        startActivityForResult(intent, 0);
    }

    public void ll_2(View view) {
        Uri uri = Uri.parse("content://contacts/people");
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        startActivityForResult(intent, 1);
    }

    //获取联系人的方法
    public void getUser(View view) {
        int count = 0;
        //用户表的uri
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        //用户数据
        Cursor c = cr.query(uri, null, null, null, null);
        StringBuilder sb = new StringBuilder();

        while (c.moveToNext()) {
            //用户ID
            int id = c.getInt(c.getColumnIndex("_id"));
            //用户名
            String name = c.getString(c.getColumnIndex("display_name"));
            Log.i("test", id + " " + c.getString(c.getColumnIndex("display_name")));
            //根据用户ID些查看详细信息
            Uri uridata = Uri.parse("content://com.android.contacts/raw_contacts/" + id + "/data");
            //用户信息信息数据
            Cursor cs = cr.query(uridata, null, null, null, null);
            Map<String, Object> map = null;

            while (cs.moveToNext()) {
                //数据类型
                String type = cs.getString(cs.getColumnIndex("mimetype"));
                Log.i("test", "电话号码：" + cs.getString(cs.getColumnIndex("data1")) + " 类型=" + cs.getString(cs.getColumnIndex("mimetype")));
                //用户数据map集合
                map = new HashMap<>();
                if (type.equals("vnd.android.cursor.item/name")) {
                    //当type是vnd.android.cursor.item/name时 添加用户名
                    map.put("namedata", cs.getString(cs.getColumnIndex("data1")));
                } else if (type.equals("vnd.android.cursor.item/phone_v2")) {
                    //当type是vnd.android.cursor.item/phone_v2时 添加手机号码
                    map.put("phonedata", cs.getString(cs.getColumnIndex("data1")));
                    count = count+1;
                    sb.append("电话号"+ count +"   :   "+cs.getString(cs.getColumnIndex("data1"))+"\n");
                }
                lm.add(map);
            }
//            if(map !=null && map.size()>0){
//                sb.append(map.get("namedata")+":"+map.get("phonedata")+"\n");
//            }

//            //设置适配器
//            SimpleAdapter simple=new SimpleAdapter(MainActivity.this,lm,R.layout.item_phone,new String[]{"namedata","phonedata"},new int[]{R.id.tv_item_phone_name,R.id.tv_item_phone_phone});
//            tv_main_testView.setAdapter(simple);
        }
        multiAutoCompleteTextView.setText(sb);
    }

    /*
     * 跳转联系人列表的回调函数
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (data == null) {
                    return;
                }
                //处理返回的data,获取选择的联系人信息
                Uri uri = data.getData();
                String[] contacts = getPhoneContacts(uri);

                et1.setText(contacts[1]);
                break;
            case 1:
                if (data == null) {
                    return;
                }
                //处理返回的data,获取选择的联系人信息
                Uri uri2 = data.getData();
                String[] contacts2 = getPhoneContacts(uri2);
                et2.setText(contacts2[1]);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String[] getPhoneContacts(Uri uri) {
        String[] contact = new String[2];
        //得到ContentResolver对象
        ContentResolver cr = getContentResolver();
        //取得电话本中开始一项的光标
        Cursor cursor = cr.query(uri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            //取得联系人姓名
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            contact[0] = cursor.getString(nameFieldColumnIndex);
            //取得电话号码
            String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
            if (phone != null) {
                phone.moveToFirst();
                contact[1] = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            phone.close();
            cursor.close();
        } else {
            return null;
        }
        return contact;
    }

    //获得手机的宽度和高度像素单位为px
// 通过WindowManager获取
    public void getScreenDensity_ByWindowManager() {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();//屏幕分辨率容器
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        int width = mDisplayMetrics.widthPixels;
        int height = mDisplayMetrics.heightPixels;
        float density = mDisplayMetrics.density;
        int densityDpi = mDisplayMetrics.densityDpi;
        Log.d(TAG, "Screen Ratio: [" + width + "x" + height + "],density=" + density + ",densityDpi=" + densityDpi);
        Log.d(TAG, "Screen mDisplayMetrics: " + mDisplayMetrics);
    }

    // 通过Resources获取
    public void getScreenDensity_ByResources() {
        DisplayMetrics mDisplayMetrics = getResources().getDisplayMetrics();
        int width = mDisplayMetrics.widthPixels;
        int height = mDisplayMetrics.heightPixels;
        float density = mDisplayMetrics.density;
        int densityDpi = mDisplayMetrics.densityDpi;
        Log.d(TAG, "Screen Ratio: [" + width + "x" + height + "],density=" + density + ",densityDpi=" + densityDpi);
        Log.d(TAG, "Screen mDisplayMetrics: " + mDisplayMetrics);

    }

    // 获取屏幕的默认分辨率
    public void getDefaultScreenDensity() {
        Display mDisplay = getWindowManager().getDefaultDisplay();
        int width = mDisplay.getWidth();
        int height = mDisplay.getHeight();
        Log.d(TAG, "Screen Default Ratio: [" + width + "x" + height + "]");
        Log.d(TAG, "Screen mDisplay: " + mDisplay);
    }
}
