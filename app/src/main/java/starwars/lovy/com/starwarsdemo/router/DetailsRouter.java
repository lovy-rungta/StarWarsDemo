package starwars.lovy.com.starwarsdemo.router;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import starwars.lovy.com.starwarsdemo.base.Navigator;
import starwars.lovy.com.starwarsdemo.components.DetailsActivity;
import starwars.lovy.com.starwarsdemo.contract.DetailsContract;

/**
 * Created by Lovy on 02-07-2018.
 */

public class DetailsRouter extends Navigator implements DetailsContract.Route {
    @Override
    public void displayError(String message) {
        if (!(message == "" || message == null)) {
            Toast.makeText(activity.get(), message, Toast.LENGTH_LONG).show();
        }
    }
}
