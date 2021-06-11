package com.huawei.orm;

import ohos.data.orm.OrmContext;
import ohos.data.orm.OrmPredicates;
import ohos.hiviewdfx.HiLogLabel;

import java.util.ArrayList;
import java.util.List;

public class DbUtils {

    public DbUtils() {
    }

    public static void insert(OrmContext ormContext, String name , String lastName) {
        User user = new User();
        user.setFirstName(name);
        user.setLastName(lastName);
        boolean isSuccess = ormContext.insert(user);
        isSuccess = ormContext.flush();
    }

    public static List<User> query(OrmContext ormContext) {
        List<User> queryResult = new ArrayList<User>();
        OrmPredicates query = ormContext.where(User.class).isNotNull("firstName");
        List<User> user = ormContext.query(query);
        queryResult.addAll(user);
        return queryResult;
    }

    public static void update(OrmContext context, String name, String lastName) {
        OrmPredicates predicates = context.where(User.class);
        predicates.equalTo("firstName", name);
        List<User> users = context.query(predicates);
        for(User user: users) {
            user.setLastName(lastName);
            context.update(user);
            context.flush();
        }
    }

    public static void delete(OrmContext context, User user) {
        context.delete(user);
        context.flush();
    }
}
