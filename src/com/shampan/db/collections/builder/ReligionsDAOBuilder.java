package com.shampan.db.collections.builder;
import com.shampan.db.collections.ReligionsDAO;

/**
 *
 * @author nazmul hasan
 */
public class ReligionsDAOBuilder {

    private ReligionsDAO religion;

    public ReligionsDAOBuilder() {
        religion = new ReligionsDAO();
    }
    private String _id;
    private String religionId;
    private String title;
    private String order;

    public ReligionsDAOBuilder setId(String _id) {
        this._id = _id;
        return this;
    }

    public ReligionsDAOBuilder setReligionId(String religionId) {
        this.religionId = religionId;
        return this;
    }
    
    public ReligionsDAOBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ReligionsDAOBuilder setOrder(String order) {
        this.order = order;
        return this;
    }

    public ReligionsDAO build() {
        religion.set_id(_id);
        religion.setReligionId(religionId);
        religion.setTitle(title);
        religion.setOrder(order);
        return religion;
    }

}
