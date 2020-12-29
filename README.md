# Student2912
btvn 29/12/2020

use Filter in searchView

//save state of listview after add and delete
public void remove(int position){
        list.remove (position);
        notifyDataSetChanged ();
    }
public void add(StudentItem std){
        list.add(std);
        notifyDataSetChanged();
    }
 // update bị sai do gán sai layout
