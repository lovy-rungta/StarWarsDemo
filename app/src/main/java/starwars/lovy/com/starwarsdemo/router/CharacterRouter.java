package starwars.lovy.com.starwarsdemo.router;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import starwars.lovy.com.starwarsdemo.base.Navigator;
import starwars.lovy.com.starwarsdemo.components.CharactersActivity;
import starwars.lovy.com.starwarsdemo.components.DetailsActivity;
import starwars.lovy.com.starwarsdemo.contract.CharactersContract;
import starwars.lovy.com.starwarsdemo.model.Results;

/**
 * Created by Lovy on 02-07-2018.
 */

public class CharacterRouter extends Navigator implements CharactersContract.Route {

    @Override
    public void displayError(String message) {
        if (!(message == "" || message == null)) {
            Toast.makeText(activity.get(), message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void navigateToDetails(Results result) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("CHARACTER", result.getUrl());
        getActivity().startActivity(intent);
    }
}
