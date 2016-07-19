package com.chwings.letgotips.helper;

import android.content.Context;
import android.text.TextUtils;

import com.chwings.letgotips.bean.SearchHistoryBean;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * 搜索记录帮助类<\br>
 *     从realm获取
 *
 */
public class SearchHistoryHelper {

    private Context mContext ;

    private List<String> mQueryResult ;

    public SearchHistoryHelper(Context context){
        mContext = context ;
    }

    /**
     * 获取本地存储的搜索记录
     */
    public List<String> getSearchHistory(){
        if(mQueryResult == null){
            mQueryResult = new ArrayList<>();
        }
        mQueryResult.clear();
        List<SearchHistoryBean> queryResult = querySearchHistory();
        if(queryResult != null && queryResult.size() > 0){
            for(SearchHistoryBean bean : queryResult){
                mQueryResult.add(bean.content);
            }
        }
        return mQueryResult;
    }

    /**
     * 保存搜索记录
     */
    public void saveSearchHistory(String str , long date){
        if(!TextUtils.isEmpty(str) && date > 0){
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            // Create an object
            SearchHistoryBean bean = realm.createObject(SearchHistoryBean.class);
            // Set its fields
            bean.content = str ;
            bean.date = date ;
            realm.commitTransaction();
        }
    }

    /**
     * 查询搜索记录<\br>
     *      从realm
     */
    private List<SearchHistoryBean> querySearchHistory(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<SearchHistoryBean> result =
                realm.where(SearchHistoryBean.class)
                        .findAll();
        result = result.sort("date" , Sort.DESCENDING);
        return result;
    }

    /**
     * 清除搜索记录
     */
    public void cleanSearchHistory(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults results = realm.where(SearchHistoryBean.class).findAll();
        results.deleteAllFromRealm();
    }

}
