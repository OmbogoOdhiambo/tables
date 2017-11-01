/**
 * Created by Odhiambo on 11/1/2017.
 */

public class Students {
    //private variables
    int _id;
    String _name;
    String _class_id;

    //empty constructor

    public Students() {

    }
    // constructor

    public Students(int id, String name, String _class_id){

        this._id = id;
        this._name = name;
        this._class_id = _class_id;
    }

    // constructor
    public Students(String _name, String _class_id){
        this._name = _name;
        this._class_id =_class_id;

    }

    //getting ID

    public int get_id() {
        return this. _id;
    }

    //setting id

    public void set_id(int _id) {
        this._id = _id;
    }

    //getting name

    public String get_name() {
        return this._name;
    }


    public void set_name(String _name) {
        this._name = _name;
    }



    public String get_class_id() {
        return _class_id;
    }

    public void set_class_id(String string) {
    }
}
