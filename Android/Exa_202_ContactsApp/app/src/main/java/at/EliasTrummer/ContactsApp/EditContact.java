package at.EliasTrummer.ContactsApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import at.EliasTrummer.ContactsApp.bl.Contact;
import at.EliasTrummer.ContactsApp.bl.ContactAdpater;
import at.EliasTrummer.ContactsApp.utils.Utils;

public class EditContact extends AppCompatActivity {

    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        this.contact = Utils.currentContact;
        init();

        findViewById(R.id.btSave).setOnClickListener(v -> {
            if(((EditText)findViewById(R.id.etFirstname)).getText().toString().isEmpty() ||
                ((EditText)findViewById(R.id.etLastname)).getText().toString().isEmpty() ||
                ((EditText)findViewById(R.id.etLanguage)).getText().toString().isEmpty() ||
                ((EditText)findViewById(R.id.etPhone)).getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "Every field must have a value", Toast.LENGTH_LONG).show();
                return;
            }

            contact.setFirstname(((EditText)findViewById(R.id.etFirstname)).getText().toString());
            contact.setLastname(((EditText)findViewById(R.id.etLastname)).getText().toString());
            contact.setLanguage(((EditText)findViewById(R.id.etLanguage)).getText().toString());
            contact.setPhoneNumber(((EditText)findViewById(R.id.etPhone)).getText().toString());

            if(((RadioButton)findViewById(R.id.rbMale)).isChecked()){
                contact.setGender('m');
            }else{
                contact.setGender('f');
            }

            ContactAdpater.update();

            finish();
        });

        findViewById(R.id.btCancel).setOnClickListener(v -> {
            finish();
        });
    }

    public void init(){
        ((EditText)findViewById(R.id.etFirstname)).setText(contact.getFirstname());
        ((EditText)findViewById(R.id.etLastname)).setText(contact.getLastname());
        ((EditText)findViewById(R.id.etPhone)).setText(contact.getPhoneNumber());
        ((EditText)findViewById(R.id.etLanguage)).setText(contact.getLanguage());

        if(contact.getGender() == 'm'){
            ((RadioButton)findViewById(R.id.rbMale)).setChecked(true);
        }else{
            ((RadioButton)findViewById(R.id.rbFemale)).setChecked(true);
        }
    }
}
