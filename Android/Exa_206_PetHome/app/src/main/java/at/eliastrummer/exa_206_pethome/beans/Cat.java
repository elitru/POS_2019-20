package at.eliastrummer.exa_206_pethome.beans;

import android.net.Uri;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cat extends Pet implements Serializable {

    private CatColor color;
    private transient Uri pictureUri;

    private static final long serialVersionUID = 01L;

    public Cat(String name, LocalDate dateOfBirth, Gender gender, CatColor color, Uri pictureUri) {
        super(name, dateOfBirth, gender);
        this.color = color;
        this.pictureUri = pictureUri;
    }

    public Cat(String[] param) {
        super(param[1], LocalDate.parse(param[3], DateTimeFormatter.ofPattern("MM/dd/yyyy")), Gender.valueOf(param[2].toUpperCase()));
        this.color = CatColor.valueOf(param[5].toUpperCase());
        this.pictureUri = Uri.parse(param[6]);
    }

    public CatColor getColor() {
        return color;
    }

    public void setColor(CatColor color) {
        this.color = color;
    }

    public Uri getPictureUri() {
        return pictureUri;
    }

    public void setPictureUri(Uri pictureUri) {
        this.pictureUri = pictureUri;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeUTF(pictureUri.toString());
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        pictureUri = Uri.parse(ois.readUTF());
    }
}
