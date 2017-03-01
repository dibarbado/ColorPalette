package work.andrerodrigues.colorpalette;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Andre on 26/02/2017.
 */

public class Item implements Parcelable{

    private int id;
    private int group_id;
    private String index;
    private String hex;
    private String created;
    private String modified;

    public Item() {

    }

    public Item(int id, int group_id, String index, String hex, String created, String modified) {
        this.id = id;
        this.group_id = group_id;
        this.index = index;
        this.hex = hex;
        this.created = created;
        this.modified = modified;
    }

    protected Item(Parcel in) {
        id = in.readInt();
        group_id = in.readInt();
        index = in.readString();
        hex = in.readString();
        created = in.readString();
        modified = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(group_id);
        dest.writeString(index);
        dest.writeString(hex);
        dest.writeString(created);
        dest.writeString(modified);
    }
}
