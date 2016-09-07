package appewtc.masterung.natbmr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by masterUNG on 9/7/2016 AD.
 */
public class MyAlert {

    public void myDialog(Context context,
                         int intIcon,
                         String strTitle,
                         String strMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setIcon(intIcon);
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

}   // Main Class
