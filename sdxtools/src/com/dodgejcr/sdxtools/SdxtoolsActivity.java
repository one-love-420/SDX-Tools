package com.dodgejcr.sdxtools;

import java.io.DataOutputStream;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
<<<<<<< HEAD
=======
import android.content.Context;
>>>>>>> ddd8ab48ff998af21bdee1732173ecc25c6993f1
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
<<<<<<< HEAD
import android.util.Log;
=======
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
>>>>>>> ddd8ab48ff998af21bdee1732173ecc25c6993f1

public class SdxtoolsActivity extends Activity {

    private boolean rootAccess = false;
    static final int DIALOG_NO_ROOT_ACCESS = 0;

    private static final String LOG_TAG = "SDXTools";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
<<<<<<< HEAD
        rootAccess = canSU();
        if (!rootAccess) showDialog(DIALOG_NO_ROOT_ACCESS);
    }
=======

        MyPagerAdapter adapter = new MyPagerAdapter();
        ViewPager myPager = (ViewPager) findViewById(R.id.toolspager);
        myPager.setAdapter(adapter);
        myPager.setCurrentItem(0);
        rootAccess = canSU();
        if (!rootAccess) showDialog(DIALOG_NO_ROOT_ACCESS);
    }

    private class MyPagerAdapter extends PagerAdapter {
    
        public int getCount() {
            return 9;
        }
    
        public Object instantiateItem(View collection, int position) {
    
            LayoutInflater inflater = (LayoutInflater) collection.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    
            int resId = 0;
            switch (position) {
                case 0:
                    resId = R.layout.menu;
                    break;
                case 1:
                    resId = R.layout.phoneinfo;
                    break;
                case 2:
                    resId = R.layout.appremoval;
                    break;
                case 3:
                    resId = R.layout.filemanager;
                    break;
                case 4:
                    resId = R.layout.scriptmanager;
                    break;
                case 5:
                    resId = R.layout.logcat;
                    break;
                case 6:
                    resId = R.layout.phonedump;
                    break;
                //support removed by dodgejcr
                //case 7:
                    //resId = R.layout.root;
                    //break;
                case 7:
                    resId = R.layout.kernel;
                    break;
            }
    
            View view = inflater.inflate(resId, null);
            ((ViewPager) collection).addView(view, 0);
            return view;
        }
    
        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }
    
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == ((View) arg1);
        }
    
        @Override
        public Parcelable saveState() {
            return null;
        }
    
        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub
        }
    
        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub
        }
    
        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub
        }
    }

>>>>>>> ddd8ab48ff998af21bdee1732173ecc25c6993f1
    /** http://www.monkeycancode.com/android-show-a-legal-agreement-dialog-before-program-launches */
    @Override
    public Dialog onCreateDialog(int id){
        Dialog dialog;
        switch(id) {
        case DIALOG_NO_ROOT_ACCESS:
            Dialog dialog1 = new AlertDialog.Builder(this)
            .setMessage(R.string.noRootMessage)
            .setCancelable(false)
            .setTitle(R.string.dialogTitleNoRoot)
            .setPositiveButton(R.string.allAboutRoot, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    String url = getString(R.string.allAboutRootMarketLink);
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                    finish();
                }
            })
            .setNegativeButton(R.string.limitedMode, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //contine on our merry way
                }
            })
            .create();
            return dialog1;
        default:
            dialog = null;
        }
        return dialog;
    }

    //source - https://github.com/joeykrim/WimaxKeys/blob/master/src/com/joeykrim/wimaxkeys/WimaxKeys.java#L259
    private boolean canSU() {
        Process process = null;
        int exitValue = -1;
        try {
            process = Runtime.getRuntime().exec("su");
            DataOutputStream toProcess = new DataOutputStream(process.getOutputStream());
            toProcess.writeBytes("exec id\n");
            toProcess.flush();
            exitValue = process.waitFor();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error determining root access: " + e);
            exitValue = -1;
        }
        return exitValue == 0;
    }
}