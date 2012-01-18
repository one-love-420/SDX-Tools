package com.dodgejcr.sdxtools;

import java.io.DataOutputStream;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class SdxtoolsActivity extends Activity {

    private boolean rootAccess = false;
    static final int DIALOG_NO_ROOT_ACCESS = 0;

    private static final String LOG_TAG = "SDXTools";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        rootAccess = canSU();
        if (!rootAccess) showDialog(DIALOG_NO_ROOT_ACCESS);
    }
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