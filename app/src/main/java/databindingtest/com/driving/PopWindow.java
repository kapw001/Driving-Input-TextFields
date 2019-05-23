package databindingtest.com.driving;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class PopWindow extends PopupWindow {


    private Context context;

    private String[] list;

    private ItemSelectedListener itemSelectedListener;

    public PopWindow(Context context, String[] list, ItemSelectedListener itemSelectedListener) {
        this.context = context;
        this.list = list;
        this.itemSelectedListener = itemSelectedListener;

        init(this.context);
    }

    private void init(Context context) {

        // the drop down list is a list view
        ListView listViewDogs = new ListView(context);

        // set our adapter and pass our pop up window contents
        listViewDogs.setAdapter(dogsAdapter(context, list));

        // set the item click listener
        listViewDogs.setOnItemClickListener(new DogsDropdownOnItemClickListener(this, itemSelectedListener));

        // some other visual settings
        setFocusable(true);

        setWidth(250);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        listViewDogs.setBackgroundColor(Color.BLACK);
        // set the list view as pop up window content
        setContentView(listViewDogs);

    }


    /*
     * adapter where the list values will be set
     */
    private ArrayAdapter<String> dogsAdapter(final Context context, String dogsArray[]) {

        return new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, dogsArray) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                // setting the ID and text for every items in the list
                String item = getItem(position);
//                String[] itemArr = item.split("::");
//                String text = itemArr[0];
//                String id = itemArr[1];

                // visual settings for the list item
                TextView listItem = new TextView(context);

                listItem.setText(item);

                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                listItem.setLayoutParams(params);
//                listItem.setTag(id);
                listItem.setTextSize(22);
                listItem.setPadding(10, 10, 10, 10);
                listItem.setTextColor(Color.WHITE);

                return listItem;
            }
        };

    }


    public class DogsDropdownOnItemClickListener implements AdapterView.OnItemClickListener {

        String TAG = "DogsDropdownOnItemClickListener.java";

        private PopupWindow popupWindow;
        private ItemSelectedListener itemSelectedListener;

        public DogsDropdownOnItemClickListener(PopupWindow popupWindow, ItemSelectedListener itemSelectedListener) {
            this.popupWindow = popupWindow;
            this.itemSelectedListener = itemSelectedListener;
        }

        @Override
        public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {

            // get the context and main activity to access variables
//            Context mContext = v.getContext();


            // add some animation when a list item was clicked
            Animation fadeInAnimation = AnimationUtils.loadAnimation(v.getContext(), android.R.anim.fade_in);
            fadeInAnimation.setDuration(10);
            v.startAnimation(fadeInAnimation);

            // dismiss the pop up
            popupWindow.dismiss();

            // get the text and set it as the button text
            String selectedItemText = ((TextView) v).getText().toString();

            if (itemSelectedListener != null) {

                itemSelectedListener.onItemSelected(selectedItemText);
            }

//            mainActivity.buttonShowDropDown.setText(selectedItemText);

            // get the id
//            String selectedItemTag = ((TextView) v).getTag().toString();
//            Toast.makeText(mContext, "Dog ID is: " + selectedItemTag, Toast.LENGTH_SHORT).show();

        }

    }


    public interface ItemSelectedListener {


        void onItemSelected(String s);

    }
}
