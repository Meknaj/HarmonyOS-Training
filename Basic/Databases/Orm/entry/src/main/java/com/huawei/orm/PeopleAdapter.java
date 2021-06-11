package com.huawei.orm;

import com.huawei.orm.slice.MainAbilitySlice;
import ohos.agp.components.*;
import java.util.ArrayList;
import java.util.List;

public class PeopleAdapter extends BaseItemProvider {
    private static final int PERSON_ID = 99;

    private static final int TEXT_SIZE = 40;

    private MainAbilitySlice mainAbilitySlice;

    private List<User> personList;

    PersonClickListener personClickListener;
    /**
     * PeopleAdapter
     *
     * @param slice PersonAbilitySlice
     *
     */
    public PeopleAdapter(MainAbilitySlice slice, PersonClickListener personClickListener) {
        mainAbilitySlice = slice;
        this.personClickListener = personClickListener;
        personList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public Object getItem(int index) {
        return personList.get(index);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public Component getComponent(int index, Component convertView, ComponentContainer componentContainer) {
        Component component = convertView;
        if (component == null) {
            DirectionalLayout.LayoutConfig config = new DirectionalLayout.LayoutConfig(
                    DirectionalLayout.LayoutConfig.MATCH_PARENT, DirectionalLayout.LayoutConfig.MATCH_CONTENT);
            DirectionalLayout mainLayout = new DirectionalLayout(mainAbilitySlice);
            mainLayout.setLayoutConfig(config);
            mainLayout.setOrientation(Component.VERTICAL);

            Text person = new Text(mainAbilitySlice);
            person.setId(PERSON_ID);
            person.setTextSize(TEXT_SIZE);
            mainLayout.addComponent(person);
            component = mainLayout;
        }
        Text text = (Text) component.findComponentById(PERSON_ID);
        updateItem(index, text);
        component.setClickedListener(component1 -> {
            if (personClickListener != null) {
                personClickListener.onPersonClick(index);
            }
        });
        return component;
    }

    private void updateItem(int position, Text text) {
        User person = personList.get(position);
        text.setText(person.getFirstName() + " " + person.getLastName());
    }

    /**
     * PersonClickListener
     *
     * @since 2020-08-25
     */
    public interface PersonClickListener {
        /**
         * onPersonClick
         *
         * @param position position
         */
        void onPersonClick(int position);
    }

    /**
     * setPeople
     *
     * @param people people
     */
    public void setPeople(List<User> people) {
        personList = people;
        mainAbilitySlice.getUITaskDispatcher().asyncDispatch(this::notifyDataChanged);
    }

    /**
     * gets person
     *
     * @param position position
     * @return person
     */
    public User getPerson(int position) {
        return personList.get(position);
    }
}

