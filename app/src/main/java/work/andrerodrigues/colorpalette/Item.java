package work.andrerodrigues.colorpalette;

/**
 * Created by Andre on 26/02/2017.
 */

public class Item {

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
}
