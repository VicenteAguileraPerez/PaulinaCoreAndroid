package com.github.vicenteaguileraperez.paulinacore;

import androidx.annotation.NonNull;

import com.github.vicenteaguileraperez.paulinacore.enums.Options;
import com.github.vicenteaguileraperez.paulinacore.interfaces.Something;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Map;


/**
 * @author Vicente Aguilera Pérez
 * @version 1.0.0
 * Date December 18th, 2020
 */

public class FiretoreFunctions
{
    private Something something;

    public void setListenerAny(Something something)
    {
        this.something=something;
    }
    public void addData(String collection, String document, Map<String,Object> data)
    {
        FirebaseFirestore.getInstance().collection(collection).document(document)
            .set(data)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid)
                {
                    something.status(Options.ADD,true);
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    something.status(Options.ADD,false);
                }
            });
    }
    public void updateData(String collection, String document, Map<String, Object> data)
    {
        FirebaseFirestore.getInstance().collection(collection).document(document)
                .update(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        something.status(Options.UPDATE,true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        something.status(Options.UPDATE,false);
                    }
                });
    }
    public void deleteData(String collection,String document)
    {
        FirebaseFirestore.getInstance().collection(collection).document(document)
            .delete()
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    something.status(Options.DELETE,true);
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    something.status(Options.DELETE,false);
                }
            });
    }
}
