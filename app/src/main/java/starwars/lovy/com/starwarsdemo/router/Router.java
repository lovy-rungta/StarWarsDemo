package starwars.lovy.com.starwarsdemo.router;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;

import starwars.lovy.com.starwarsdemo.components.CharactersActivity;
import starwars.lovy.com.starwarsdemo.components.DetailsActivity;

/**
 * Created by Lovy on 02-07-2018.
 */

public class Router {

    public void getCharacters(Context context) {
        Intent intent = new Intent(context, CharactersActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

}