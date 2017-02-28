package work.andrerodrigues.colorpalette;

/**
 * Created by Andre on 26/02/2017.
 */

public class Group {

    private int id;
    private String name;
    private String index;
    private String hex;
    private String created;
    private String modified;

    public Group() {

    }

    public Group(int id, String name, String index, String hex, String created, String modified) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
