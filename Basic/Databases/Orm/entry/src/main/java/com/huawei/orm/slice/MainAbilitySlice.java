package com.huawei.orm.slice;

import com.huawei.orm.*;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;

public class MainAbilitySlice extends AbilitySlice {
    OrmContext ormContext;
    Button addPersonButton, updatePersonButton;
    TextField mName, mLastName;
    PeopleAdapter peopleAdapter;
    private PeopleAdapter.PersonClickListener personClickListener = new PeopleAdapter.PersonClickListener() {
        @Override
        public void onPersonClick(int position) {
            User person = peopleAdapter.getPerson(position);
            DbUtils.delete(ormContext, person);
            peopleAdapter.setPeople(DbUtils.query(ormContext));
        }
    };
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        initView();
        setUpViews();
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        ormContext = dbHelper.getOrmContext("StoreDb", "storeDb.db", StoreDb.class);

    }

    private void initView() {
        ComponentContainer rootLayout = (ComponentContainer) LayoutScatter.getInstance(this)
                .parse(ResourceTable.Layout_ability_main, null, false);
        addPersonButton = (Button) rootLayout.findComponentById(ResourceTable.Id_add_button);
        updatePersonButton = (Button) rootLayout.findComponentById(ResourceTable.Id_update_button);
        mName = (TextField) rootLayout.findComponentById(ResourceTable.Id_name_text);
        mLastName = (TextField) rootLayout.findComponentById(ResourceTable.Id_lastName_text);
        ListContainer listContainer = (ListContainer) rootLayout.findComponentById(ResourceTable.Id_people_list);
        peopleAdapter = new PeopleAdapter(this, personClickListener);
        listContainer.setItemProvider(peopleAdapter);
        super.setUIContent(rootLayout);
    }

    public void setUpViews() {
        addPersonButton.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                DbUtils.insert(ormContext, mName.getText(), mLastName.getText());
                peopleAdapter.setPeople(DbUtils.query(ormContext));
            }
        });
        updatePersonButton.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                DbUtils.update(ormContext, mName.getText(), mLastName.getText());
                peopleAdapter.setPeople(DbUtils.query(ormContext));
            }
        });
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
